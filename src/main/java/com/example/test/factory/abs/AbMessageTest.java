package com.example.test.factory.abs;

import com.example.Application;
import com.example.test.factory.fac.IMyMessageFactory;
import com.example.test.factory.fac.MyMessageSms;
import com.example.test.factory.fac.SmsMessageFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description:抽象工厂模式
 * Created by lpfei on 2019/8/22
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbMessageTest {

    @Test
    public void test() {
        SmsFactory smsFactory = new SmsFactory();
        smsFactory.createMes().sendMesage();
        smsFactory.createSend().query();


        EamilFactory eamilFactory = new EamilFactory();
        eamilFactory.createMes().sendMesage();
        eamilFactory.createSend().query();
    }
}
