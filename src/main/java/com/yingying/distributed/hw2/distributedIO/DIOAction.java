package com.yingying.distributed.hw2.distributedIO;

import com.yingying.distributed.hw2.Config;

import java.io.IOException;
import java.util.stream.IntStream;

public abstract class DIOAction {
    protected int[] buffer;
    private int waterLine;

    public DIOAction() {
        buffer = new int[getByteSize()];
        waterLine = 0;
    }

    private int getByteSize() {
        return Config.range * Config.repeat;
//        final int times = String.valueOf(Config.range).length() - 1;
//        final int size = IntStream.range(0, times)
//                .map(i -> (int) Math.pow(10, i) * 9 * (i + 1))
//                .sum() + (Config.range - (int) Math.pow(10, times) + 1)
//                * (times + 1);
//
//        return size * Config.repeat;
    }

    public void write(int[] data) {
        for (int item : data) {
            buffer[waterLine] = item;
            waterLine += 1;
        }
    }

    public abstract void close() throws IOException;
}