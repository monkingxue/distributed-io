package com.yingying.distributed.hw2.distributedIO.cassandraIO;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.yingying.distributed.hw2.Config;

import java.util.stream.IntStream;

public class JSONAction extends CassandraAction {
    public JSONAction(Session session) {
        this.session = session;
    }

    @Override
    public void write(IntStream data) {
        try (Cluster cluster = Cluster.builder()
                .addContactPoint(Config.casIP)
                .withPort(Config.casPort)
                .build()) {

            session = cluster.connect("keyspace_user20");
            data.forEach(i -> session.execute(
                    "INSERT INTO number JSON '{\"pk\" : " + getAndUpdateUUID() + ", \"num\" : " + i + " }';"));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cassandra initialize failure.");
        }
    }
}
