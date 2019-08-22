package com.example.test.factory.fac;

/**
 * description:工厂方法模式-工厂接口
 * Created by lpfei on 2019/8/22
 */

/**
 * 新增产品实现该工厂接口,不需要改动工厂方法
 */
public interface IMyMessageFactory {
    public IMyMessage createMessage();
}
