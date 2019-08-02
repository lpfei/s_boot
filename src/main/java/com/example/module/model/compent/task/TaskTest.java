package com.example.module.model.compent.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by lpfei on 2017/4/5.
 */
@Service
@Slf4j
public class TaskTest {

    //@Scheduled(fixedRate = 5000)
    public void print() {
        //计划执行
        log.info("每5秒钟执行一次........");
        for (int i = 0; i < 10; i++) {
            print2(i);
            print3(i);
        }

    }

    @Async
    public void print2(int i) {
        //异步执行
        Thread t = Thread.currentThread();
        log.info("异步执行.......{}---------------------{}", t.getName(), i + 1);

    }

    @Async
    public void print3(int i) {
        //异步执行
        Thread t = Thread.currentThread();

        log.info("异步执行.......{}===={}", t.getName(), i);

    }

}
