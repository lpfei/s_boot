package com.example.test.singleton;

/**
 * description:懒汉式单例
 * Created by lpfei on 2019/12/26
 */
public class Singleton2 {
    // 指向自己实例的私有静态引用
    private static Singleton2 instance;

    // 私有的构造方法
    public Singleton2() {
    }

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton2 getInstance() {
        // 被动创建，在真正需要使用时才去创建
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }

//    我们从懒汉式单例可以看到，单例实例被延迟加载，即只有在真正使用的时候才会实例化一个对象并交给自己的引用。

//    这种写法起到了Lazy Loading的效果，但是只能在单线程下使用。如果在多线程下，一个线程进入了if (singleton == null)判断语句块，
//    还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式。所以在多线程环境下不可使用这种方式


    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Singleton2.getInstance() == null) {
                        System.out.println("null");
                    } else {
                        System.out.println("1");
                    }
                }
            }
        }, "t0").start();
    }
}
