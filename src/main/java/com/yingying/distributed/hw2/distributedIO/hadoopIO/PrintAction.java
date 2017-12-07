package com.yingying.distributed.hw2.distributedIO.hadoopIO;

import com.yingying.distributed.hw2.distributedIO.DIOAction;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class PrintAction extends DIOAction {
    private PrintWriter pw;

    public PrintAction(FileSystem hdfs, String path) throws IOException {
        pw = new PrintWriter(hdfs.create(new Path(path)));
    }

    @Override
    public void close() {
        pw.print(new String(buffer, StandardCharsets.UTF_8));
        pw.close();
    }
}
