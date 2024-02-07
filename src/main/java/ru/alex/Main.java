package ru.alex;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        int coreCPU = Runtime.getRuntime().availableProcessors();
        System.out.println("Avalable core CPU: " + coreCPU);
        ForkJoinPool forkJoinPool = new ForkJoinPool(coreCPU);
        long pow = Long.parseLong(args[5]);
        long begin = 0;
        long end;
        long maxNumber = (long) Math.pow(10, pow);
        for (int i = 1; i <= coreCPU; i++) {
            end = maxNumber / 4 * i;
            NumberTask numberTask = new NumberTask(begin, end, args[3], args[1]);
            forkJoinPool.invoke(numberTask);
            begin = end;
        }
    }
}

