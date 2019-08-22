package com.example.test.factory.abs;

/**
 * description:抽象工厂模式,抽象工厂模式是工厂方法模式的升级版本，他用来创建一组相关或者相互依赖的对象
 * Created by lpfei on 2019/8/22
 */
public interface AbstractFactory {
    public IMyMessage createMes();

    public IMySend createSend();

}
