package com.yingying.distributed.hw2;

public class Tool {
    public static void executionTime(Runnable code) {
        long startTime = System.currentTimeMillis();

        try {
            code.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Method executed: " + (endTime - startTime) + " milliseconds.");
    }
}
