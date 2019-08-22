package com.example.test.factory.fac;

/**
 * description:工厂方法模式-工厂实现
 * Created by lpfei on 2019/8/22
 */
public class EmailMessageFactory implements IMyMessageFactory {
    @Override
    public IMyMessage createMessage() {
        return new MyMessageEmail();
    }
}
