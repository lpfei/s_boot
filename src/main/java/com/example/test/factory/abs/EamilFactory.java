package com.example.test.factory.abs;

/**
 * description:
 * Created by lpfei on 2019/8/22
 */
public class EamilFactory implements AbstractFactory {
    @Override
    public IMyMessage createMes() {
        return new MyMessageEmail();
    }

    @Override
    public IMySend createSend() {
        return new MyEmailSend();
    }
}
