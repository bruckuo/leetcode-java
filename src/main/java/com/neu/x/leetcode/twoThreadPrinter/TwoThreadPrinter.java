package com.neu.x.leetcode.twoThreadPrinter;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-10-08
 * @time: 15:31
 */
public class TwoThreadPrinter {
    private static AtomicInteger countAtomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            // 取得大写还是小写
            while (true) {
                int i = (int)(Math.random() * 26);
                char ch = new Random().nextInt(2) % 2 == 0 ? 'A' : 'a';
                if (countAtomicInteger.get() == 1) {
                    System.out.println((char) (ch + i));
                    countAtomicInteger.set(0);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                int i = new Random().nextInt(5);
                if (countAtomicInteger.get() == 0) {
                    System.out.println(i);
                    countAtomicInteger.incrementAndGet();
                }
            }
        });
        t1.start();
        t2.start();

    }

}
