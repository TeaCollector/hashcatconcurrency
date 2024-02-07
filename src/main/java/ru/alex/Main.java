package ru.alex;

import java.security.NoSuchAlgorithmException;



public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        int coreCPU = Runtime.getRuntime().availableProcessors();
        System.out.println("Avalable core CPU: " + coreCPU);
        long pow = Long.parseLong(args[5]);
        long begin = 0;
        long end;
        long maxNumber = (long) Math.pow(10, pow);
        for (int i = 1; i <= coreCPU; i++) {
            end = maxNumber / 4 * i;
            new Thread(new NumberTaskRunnable(begin, end, args[3], args[1])).start();
            begin = end;
        }
    }
}

