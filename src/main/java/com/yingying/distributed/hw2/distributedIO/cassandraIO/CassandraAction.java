package com.yingying.distributed.hw2.distributedIO.cassandraIO;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.yingying.distributed.hw2.distributedIO.DIOAction;

import java.io.IOException;

public abstract class CassandraAction extends DIOAction {
    protected Session session;
    private int uuid = 1;


    protected int getAndUpdateUUID() {
        return uuid++;
    }

    @Override
    public void close() {
    }
}
