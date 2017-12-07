package com.yingying.distributed.hw2.distributedIO.cassandraIO;

public class JSONAction extends CassandraAction {

    @Override
    public void close() {
        for (int item : buffer) {
            session.execute("INSERT INTO number JSON '{\"pk\" : " + item + ", \"num\" : 1}';");
        }
    }
}
