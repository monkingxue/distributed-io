package com.yingying.distributed.hw2.writer;

import com.datastax.driver.core.*;
import com.yingying.distributed.hw2.distributedIO.DIOAction;
import com.yingying.distributed.hw2.distributedIO.cassandraIO.JSONAction;
import com.yingying.distributed.hw2.distributedIO.cassandraIO.NormalAction;

import java.lang.reflect.Constructor;

public class CASWriter extends BaseWriter {
    @Override
    public DIOAction initialize(Class<? extends DIOAction> clazz, String suffix) {
        //声明一个集群以供连接
        try {


            Constructor ctos = clazz.getConstructor();
            return (DIOAction) ctos.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cassandra initialize failure.");
        }
    }

    private void jsonTest() {
        execute(JSONAction.class);
    }

    private void NormalTest() {
        execute(NormalAction.class);
    }

    public static void main(String args[]) {
        CASWriter hdw = new CASWriter();

//        hdw.jsonTest();
        hdw.NormalTest();
    }
}
