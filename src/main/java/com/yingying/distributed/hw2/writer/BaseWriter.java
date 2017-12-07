package com.yingying.distributed.hw2.writer;

import com.yingying.distributed.hw2.Config;
import com.yingying.distributed.hw2.Producer;
import com.yingying.distributed.hw2.Tool;
import com.yingying.distributed.hw2.distributedIO.DIOAction;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseWriter {
    private static int[] threadNumArray = {1, 2, 4, 8, 16, 32};

    protected abstract DIOAction initialize(Class<? extends DIOAction> clazz, String suffix);

    protected void execute(Class<? extends DIOAction> clazz) {
        execute(clazz, "");
    }

    protected void execute(Class<? extends DIOAction> clazz, String suffix) {
        System.out.println("The time of " + clazz.getSimpleName() + " is: \n");

        try {
            Tool.executionTime(() -> {
                for (int num : threadNumArray) {
                    Tool.executionTime(() -> {

                        DIOAction io = initialize(clazz, suffix);

                        writeByMultiThread(num, io);
                        try {
                            io.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                System.out.println("Total Time: ");
            });
            System.out.println("");

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void writeByMultiThread(int threadNum, DIOAction io) {
        if (threadNum <= 0) {
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        final int divide = Math.min(Config.range / threadNum + 1, Config.range);

        CompletableFuture<Void> currentPromise = CompletableFuture
                .runAsync(() -> io.write(Producer.getData(1, divide)), executor);

        for (int i = 1; i < threadNum; i++) {
            final int start = divide * i + 1;
            final int end = start + divide - 1;
            currentPromise = currentPromise.thenAcceptAsync(n ->
                    io.write(Producer.getData(start, Math.min(Config.range, end))), executor);
        }

        currentPromise.join();
        executor.shutdown();
    }
}
