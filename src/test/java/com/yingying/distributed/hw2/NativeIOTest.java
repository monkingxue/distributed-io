package com.yingying.distributed.hw2;

import com.yingying.distributed.hw2.distributedIO.DIOAction;
import com.yingying.distributed.hw2.distributedIO.nativeIO.PrintAction;
import com.yingying.distributed.hw2.distributedIO.nativeIO.RAFAction;
import com.yingying.distributed.hw2.distributedIO.nativeIO.StreamAction;
import com.yingying.distributed.hw2.writer.MTWriter;

import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NativeIOTest {
    @Test
    public void getNumber() {
        int num = 15;
        int times = String.valueOf(num).length() - 1;
        System.out.println((num - (int) Math.pow(10, times) + 1));

        System.out.println(IntStream.range(0, times)
                .map(i -> (int) Math.pow(10, i) * 9 * (i + 1))
                .sum() + (num - (int) Math.pow(10, times) + 1) * (times + 1));
    }
}
