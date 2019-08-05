package com.louis.mango.multithread.Atom;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 原子类api测试
 * @Author: created by wangkaishuang on 2019-07-26
 */

public class AtomApiTest {

    public static void main(String args[]){
        AtomicLong atomicLong = new AtomicLong(0);
        atomicLong.getAndIncrement();
    }
}
