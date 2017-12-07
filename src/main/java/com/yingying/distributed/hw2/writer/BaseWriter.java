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

    protected void execute(Class<? extends DIOAction> clazz, String suffix) {
        System.out.println("The time of " + clazz.getSimpleName() + " is: \n");

        try {
            Tool.executionTime(() -> {
                for (int num : threadNumArray) {
                    DIOAction io = initialize(clazz, suffix);

                    Tool.executionTime(() -> writeByMultiThread(num, io));
                    try {
                        io.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Total Time: ");
            });
            System.out.println("");

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private List<int[]> parallelGenerate(int threadNum) {
        final int divide = Math.min(Config.range / threadNum + 1, Config.range);
        return IntStream.rangeClosed(1, threadNum)
                .parallel()
                .mapToObj(n -> {
                    final int start = divide * n - (divide - 1);
                    final int end = start + divide - 1;
                    return Producer.getData(start, Math.min(Config.range, end))
                            .toArray();
                }).collect(Collectors.toList());
    }

    private void writeByMultiThread(int threadNum, DIOAction io) {
        if (threadNum <= 0) {
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);

        final List<int[]> result = parallelGenerate(threadNum);

        CompletableFuture<Void> currentPromise = CompletableFuture
                .runAsync(() -> io.write(result.get(0)), executor);

        for (int i = 1; i < threadNum; i++) {
            final int index = i;
            currentPromise = currentPromise.thenAcceptAsync(n ->
                    io.write(result.get(index)), executor);
        }

        currentPromise.join();
        executor.shutdown();
    }
}
