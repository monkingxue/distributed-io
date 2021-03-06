package com.yingying.distributed.hw2.writer;

import com.yingying.distributed.hw2.Config;
import com.yingying.distributed.hw2.distributedIO.DIOAction;
import com.yingying.distributed.hw2.distributedIO.hadoopIO.PrintAction;
import com.yingying.distributed.hw2.distributedIO.hadoopIO.BufferedAction;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;


public class HDWriter extends BaseWriter {

    @Override
    public DIOAction initialize(Class<? extends DIOAction> clazz, String suffix) {
        try (final DistributedFileSystem hdfs = new DistributedFileSystem() {{
            initialize(new URI(Config.hdfsUri), new Configuration());
        }}) {
            Constructor ctos = clazz.getConstructor(FileSystem.class, String.class);
            return (DIOAction) ctos.newInstance(hdfs, Config.hdfsUri + Config.hdfsPrefix + suffix);

        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException
                | URISyntaxException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Initialize DIOAction failure");
        }
    }

    private void printTest() {
        execute(PrintAction.class, "print.txt");
    }

    private void bufferedTest() {
        execute(BufferedAction.class, "buffered.txt");
    }

    public static void main(String args[]) {
        HDWriter hdw = new HDWriter();

        hdw.printTest();
        hdw.bufferedTest();
    }
}
