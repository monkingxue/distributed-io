package com.yingying.distributed.hw2.writer;

import com.yingying.distributed.hw2.Config;
import com.yingying.distributed.hw2.distributedIO.DIOAction;
import com.yingying.distributed.hw2.distributedIO.nativeIO.PrintAction;
import com.yingying.distributed.hw2.distributedIO.nativeIO.RAFAction;
import com.yingying.distributed.hw2.distributedIO.nativeIO.StreamAction;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MTWriter extends BaseWriter {
    @Override
    protected DIOAction initialize(Class<? extends DIOAction> clazz, String suffix) {
        try {
            Constructor ctos = clazz.getConstructor(String.class);
            final String path = Config.nativePrefix + suffix;
            final File out = new File(path);
            if (out.exists()) out.delete();
            return (DIOAction) ctos.newInstance(path);

        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Initialize DIOAction failure");
        }
    }

    private void rafTest() {
        execute(RAFAction.class, "raf.txt");
    }

    private void printTest() {
        execute(PrintAction.class, "print.txt");
    }

    private void streamTest() {
        execute(StreamAction.class, "stream.txt");
    }

    public static void main(String args[]) {
        MTWriter mtw = new MTWriter();
//        mtw.printTest();
        mtw.rafTest();
//        mtw.streamTest();
    }
}
