package com.yingying.distributed.hw2.distributedIO.hadoopIO;

import com.yingying.distributed.hw2.distributedIO.DIOAction;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class BufferedAction extends DIOAction {
    private BufferedWriter bw;

    public BufferedAction(FileSystem hdfs, String path) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(hdfs.create(new Path(path))));
    }

    @Override
    public void close() throws IOException {
        bw.write(new String(buffer, StandardCharsets.UTF_8));
        bw.close();
    }
}
