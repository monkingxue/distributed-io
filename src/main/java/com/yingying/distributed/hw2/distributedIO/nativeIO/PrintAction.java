package com.yingying.distributed.hw2.distributedIO.nativeIO;

import com.yingying.distributed.hw2.distributedIO.DIOAction;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class PrintAction extends DIOAction {
    private PrintWriter pw;

    public PrintAction(String path) throws FileNotFoundException, UnsupportedEncodingException {
        pw = new PrintWriter(path, "UTF-8");
    }

    @Override
    public void close() {
        for (byte item : buffer) {
            pw.print(item);
        }
        pw.close();
    }
}
