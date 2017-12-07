package com.yingying.distributed.hw2.distributedIO.cassandraIO;

public class NormalAction extends CassandraAction {

    @Override
    public void close() {
        for (byte item : buffer) {
            session.execute("INSERT INTO number(pk, num) VALUES (" + item + ",  1);");
        }
    }
}
