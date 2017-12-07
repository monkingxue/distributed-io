package com.yingying.distributed.hw2.distributedIO.cassandraIO;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.yingying.distributed.hw2.distributedIO.DIOAction;

public abstract class CassandraAction extends DIOAction {
    protected Session session;

    public CassandraAction() {
    }

    public CassandraAction(Cluster cluster) {
        session = cluster.connect("keyspace_user20");
    }
}
