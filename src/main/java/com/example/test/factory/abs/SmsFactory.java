package com.example.test.factory.abs;

/**
 * description:
 * Created by lpfei on 2019/8/22
 */
public class SmsFactory implements AbstractFactory {
    public IMySend createSend() {
        return new MySmsSend();
    }

    public IMyMessage createMes() {
        return new MyMessageSms();
    }
}
