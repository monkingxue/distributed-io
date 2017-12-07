package com.yingying.distributed.hw2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Producer {
    public static Stream<String> getData(int end) {
        return getData(1, end);
    }

    public static Stream<String> getData(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .flatMap(n -> IntStream.generate(() -> n)
                        .parallel().limit(Config.repeat))
                .boxed().map(Object::toString);
    }
}
