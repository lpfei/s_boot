package com.example.test.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程同步 synchronized
 * <p>
 * 普通同步方法，锁是当前实例对象
 * 静态同步方法，锁是当前类的class对象
 * 同步方法块，锁是括号里面的对象
 * Created by lpfei on 2019/12/5
 */
public class SynchronizationTest {

    private Integer tmp = 1;

    private static ConcurrentHashMap<String, String> ma = new ConcurrentHashMap<>();

    //同步方法块
    private void method(Thread thread, String i) {     //同步块,锁的()里的对象,作用范围{}
//        synchronized (SynchronizationTest.class){     //作用对象=类的所有实例对象
//        synchronized (this) {                           //作用对象=调用的实例对象
//        synchronized (i) {                            //作用对象i
        if (ma.get("orderNo") == null) {
            System.out.println(ma.get("orderNo"));
            ma.put("orderNo",i);
            try {
                System.out.println("线程名" + thread.getName() + "获得了锁" + ++tmp + "==" + i);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 普通同步方法
     *
     * @param thread
     * @param i
     */
    private synchronized void synMethod(Thread thread, Integer i) {
        try {
            System.out.println("线程名" + thread.getName() + "获得了锁" + tmp++ + "==" + i);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
    }

    public static void main(String[] args) {


        //两个实例对象
        SynchronizationTest lockTest = new SynchronizationTest();
        SynchronizationTest lockTest2 = new SynchronizationTest();
        String m = new String("1").intern();
        String m2 = new String("1").intern();
        //分别为一/两个实例创建多线程执行
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Map<String, String> cm = new HashMap<>();
                    cm.put("orderNo", "1");
                    lockTest.method(Thread.currentThread(), finalI+"");
                }
            }, "t1=" + i).start();
        }

       /* for (int i = 1; i < 10; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Map<String, String> cm2 = new HashMap<>();
                    cm2.put("orderNo2", "1");
                    lockTest2.method(Thread.currentThread(), cm2.get("orderNo2"));
                }
            }, "t2=" + i).start();
        }*/
    }
}
