package ru.alex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NumberTaskRunnable implements Runnable {

    private long begin;
    private long end;
    private final String hashSum;
    private final MessageDigest messageDigest;

    public NumberTaskRunnable(long begin, long end, String hashSum, String algorithm) throws NoSuchAlgorithmException {
        this.begin = begin;
        this.end = end;
        this.hashSum = hashSum;
        this.messageDigest = MessageDigest.getInstance(algorithm);
    }

    public void run() {
        long beginTime = System.currentTimeMillis();
        StringBuilder hexString = new StringBuilder();
        while (begin < end) {
            if ((hashSum.contentEquals(hexString))) {
                System.out.println("Number is: " + begin);
                System.out.println("Thread which find number: " + Thread.currentThread().getName());
                System.out.println("Time to execute: " + (System.currentTimeMillis() - beginTime));
            }
            begin = getNumberForSearch(hexString, begin);
        }
    }

    private long getNumberForSearch(StringBuilder hexString, long begin) {
        String numberForSearching;
        begin++;
        hexString.delete(0, hexString.length());
        numberForSearching = Long.toString(begin);
        messageDigest.update(numberForSearching.getBytes());
        byte[] digest = messageDigest.digest();
        searchingHashSum(hexString, digest);
        return begin;
    }

    private void searchingHashSum(StringBuilder hexString, byte[] digest) {
        String characterToAppend;
        for (int i = 0; i < digest.length; i++) {
            characterToAppend = Integer.toHexString(0xff & digest[i]);
            characterToAppend = (characterToAppend.length() == 1) ? "0" + characterToAppend : characterToAppend;
            hexString.append(characterToAppend);
        }
    }
}
