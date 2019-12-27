package com.example.test.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 * Created by lpfei on 2019/12/5
 */
public class ReentrantLockTest {
    private static Lock lock = new ReentrantLock();//可重入锁
    private Integer tmp = 1;

    //需要参与同步的方法
    private void method(Thread thread) {
        //锁的实例对象
        lock.lock();
        try {
            System.out.println("线程名" + thread.getName() + "获得了锁" + ++tmp);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            System.out.println("线程名" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest();
        ReentrantLockTest lockTest2 = new ReentrantLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockTest.method(Thread.currentThread());
                }
            }, "t1=" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockTest2.method(Thread.currentThread());
                }
            }, "t2=" + i).start();
        }

    }
}
