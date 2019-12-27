package com.example.test.singleton;

/**
 * description:双重加锁机制
 * Created by lpfei on 2019/12/26
 */
public class Singleton3 {
    // 指向自己实例的私有静态引用
    // 指令重排有个重要的前提 在单线程环境下，指令重排不能影响最终的结果。
    // volatile 禁止指令重排序
    private volatile static Singleton3 instance;

    // 私有的构造方法
    public Singleton3() {
    }

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton3 getInstance() {
        // 被动创建，在真正需要使用时才去创建
        if (instance == null) {
            synchronized (Singleton3.class) {
                //在同一个时刻加了锁的那部分程序只有一个线程可以进入
                if (instance == null) {
                    //正确情况下
                    //1.给 instance 分配内存
                    //2.调用 Singleton 的构造函数来初始化成员变量
                    //3.将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）

                    //若这里进行了指令重排序
                    //1.给 instance 分配内存
                    //2.将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
                    //3.调用 Singleton 的构造函数来初始化成员变量
                    //没有初始化成员变量就已经指向分配的内存空间
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }

//    使用双重检测同步延迟加载去创建单例的做法是一个非常优秀的做法，其不但保证了单例，而且切实提高了程序运行效率

//    优点：线程安全；延迟加载；效率较高。


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Singleton3.getInstance() == null) {
                        System.out.println("null");
                    } else {
                        System.out.println("1");
                    }
                }
            }
        }, "t0").start();

    }
}
