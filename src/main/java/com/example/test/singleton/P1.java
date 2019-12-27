package com.example.test.singleton;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * description:
 * Created by lpfei on 2019/12/26
 */
public class P1 {

    private long b = 0;

    public void set1() {
        b = 0;
    }

    public void set2() {
        b = -1;
    }

    public void check() {
        System.out.println(b);

        if (0 != b && -1 != b) {
            //32位操作系统下会打印Error
            //64位操作系统不会
            System.err.println("Error");
        }
    }

    public static void main(String[] args) {
        P1 p1 = new P1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    p1.set1();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    p1.set2();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    p1.check();
                }
            }
        }).start();



    }



}
