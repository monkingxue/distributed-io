package com.yingying.distributed.hw2.distributedIO.cassandraIO;

import com.datastax.driver.core.Cluster;
import com.yingying.distributed.hw2.Config;

import java.util.stream.IntStream;

public class NormalAction extends CassandraAction {

    @Override
    public void write(IntStream data) {
        try (Cluster cluster = Cluster.builder()
                .addContactPoint(Config.casIP)
                .withPort(Config.casPort)
                .build()) {

            session = cluster.connect("keyspace_user20");
            data.forEach(i -> {
                session.execute(
                        "INSERT INTO number(pk, num) VALUES (" + getAndUpdateUUID() + ",  " + i + ");");
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cassandra initialize failure.");
        }
    }
}
