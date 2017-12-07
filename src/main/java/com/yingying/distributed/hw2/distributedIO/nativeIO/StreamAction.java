package com.yingying.distributed.hw2.distributedIO.nativeIO;

import com.yingying.distributed.hw2.distributedIO.DIOAction;

import java.io.*;

public class StreamAction extends DIOAction {
    private BufferedOutputStream bop;

    public StreamAction(String path) throws FileNotFoundException {
        bop = new BufferedOutputStream(new FileOutputStream(new File(path)));
    }

    public void close() throws IOException {
        bop.write(buffer);
        bop.flush();
        bop.close();
    }
}
