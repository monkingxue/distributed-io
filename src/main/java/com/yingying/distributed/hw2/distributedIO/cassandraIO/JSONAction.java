package com.yingying.distributed.hw2.distributedIO.cassandraIO;

import java.util.stream.IntStream;

public class JSONAction extends CassandraAction {
    @Override
    public void write(IntStream data) {
        data.forEach(i -> session.execute(
                "INSERT INTO number JSON '{\"pk\" : " + getAndUpdateUUID() + ", \"num\" : " + i + " }';"));
    }
}
