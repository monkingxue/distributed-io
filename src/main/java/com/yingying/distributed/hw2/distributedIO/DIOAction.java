package com.yingying.distributed.hw2.distributedIO;


import java.io.IOException;
import java.util.stream.IntStream;

public abstract class DIOAction {

    public abstract void write(IntStream data);

    public abstract void close() throws IOException;
}