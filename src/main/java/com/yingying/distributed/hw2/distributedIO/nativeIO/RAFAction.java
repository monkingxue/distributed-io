package com.yingying.distributed.hw2.distributedIO.nativeIO;

import com.yingying.distributed.hw2.distributedIO.DIOAction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RAFAction extends DIOAction {
    private RandomAccessFile raf;

    public RAFAction(String path) throws FileNotFoundException {
        raf = new RandomAccessFile(path, "rw");
    }

    @Override
    public void close() throws IOException {
        raf.write(buffer);
        raf.close();
    }
}
