package com.yingying.distributed.hw2.distributedIO.cassandraIO;

import java.util.stream.IntStream;

public class NormalAction extends CassandraAction {
    @Override
    public void write(IntStream data) {
        data.forEach(i -> session.execute(
                "INSERT INTO number(pk, num) VALUES (" + getAndUpdateUUID() + ",  " + i + ");"));
    }
}
