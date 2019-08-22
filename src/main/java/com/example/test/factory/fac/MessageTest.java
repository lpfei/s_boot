package com.example.test.factory.fac;

import com.example.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description:
 * Created by lpfei on 2019/8/22
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageTest {

    @Test
    public void test() {
        IMyMessageFactory myMessageFactory = new SmsMessageFactory();
        IMyMessage myMessageSms = myMessageFactory.createMessage();
        myMessageSms.sendMesage();

    }
}
