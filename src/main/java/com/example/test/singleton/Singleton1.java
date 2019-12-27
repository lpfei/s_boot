package com.example.test.singleton;

/**
 * description:恶汉式单例
 * Created by lpfei on 2019/12/26
 */
public class Singleton1 {
    // 指向自己实例的私有静态引用，主动创建
    private static Singleton1 instance = new Singleton1();

    // 私有的构造方法
    public Singleton1() {
    }

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton1 getInstance() {
        return instance;
    }

//    优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。

//    缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费
}
