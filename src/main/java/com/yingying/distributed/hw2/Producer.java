package com.yingying.distributed.hw2;

import java.util.stream.IntStream;

public class Producer {
    public static IntStream getData(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .flatMap(n -> IntStream.generate(() -> n)
                        .parallel().limit(Config.repeat));
    }
}
