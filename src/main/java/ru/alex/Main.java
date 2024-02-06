package ru.alex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int coreCPU = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newWorkStealingPool(coreCPU);
        Future<Integer> result = service.submit(() -> findNumber(args));
        System.out.println("Result is: " + result.get());
        service.close();
    }

    private static int findNumber(String[] args) {
        long begin = System.currentTimeMillis();
        String hashSum = args[3];
        StringBuilder hexString = new StringBuilder();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(args[1]);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        int numberForSearch = -1;
        int stringLength = Integer.parseInt(args[5]);
        while (Integer.toString(numberForSearch).length() <= stringLength && !(hashSum.contentEquals(hexString))) {
            numberForSearch = getNumberForSearch(hexString, messageDigest, numberForSearch);
        }
        System.out.println("Time to execute: " + (System.currentTimeMillis() - begin));
        return numberForSearch;
    }

    private static int getNumberForSearch(StringBuilder hexString, MessageDigest md5, int numberForSearch) {
        String characterToAppend;
        numberForSearch++;
        hexString.delete(0, hexString.length());
        characterToAppend = Integer.toString(numberForSearch);
        md5.update(characterToAppend.getBytes());
        byte[] digest = md5.digest();
        searchingHashSum(hexString, digest);
        return numberForSearch;
    }

    private static void searchingHashSum(StringBuilder hexString, byte[] digest) {
        String characterToAppend;
        for (int i = 0; i < digest.length; i++) {
            characterToAppend = Integer.toHexString(0xff & digest[i]);
            characterToAppend = (characterToAppend.length() == 1) ? "0" + characterToAppend : characterToAppend;
            hexString.append(characterToAppend);
        }
    }
}
