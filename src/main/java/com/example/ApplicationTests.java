package com.example;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * description:
 * Created by lpfei on 2019/8/21
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    private ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();

    public class aa implements Runnable {

        @Override
        public void run() {
            log.info("a+");
            boolean f = clq.add("a");
            log.info("a--{}", f);
            sl();
        }

        public void sl() {
            log.info("a-show--" + clq);
        }
    }

    public class b implements Runnable {

        @Override
        public void run() {
            log.info("b+");
            boolean f = clq.add("b");
            log.info("b--{}", f);
            sl();
        }

        public void sl() {
            log.info("b-show--" + clq);
        }
    }

    @Test
    public void test() {
        Runnable aa = new aa();
        Thread t = new Thread(aa);
        t.start();


        Runnable b = new b();
        Thread t2 = new Thread(b);
        t2.start();

//        clq.add("1");
//        clq.add("2");
//        log.info("show--,{}", clq.peek());
//        log.info("{}", clq);
    }
}
