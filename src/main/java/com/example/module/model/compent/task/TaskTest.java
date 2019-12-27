package com.example.module.model.compent.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 从配置文件获取定时任务执行配置
 * app:
 *  cron: '0/3 * * * * ?'
 * @Scheduled(cron = "${app.cron}")
 *
 * Created by lpfei on 2017/4/5.
 */
@Service
@Slf4j
public class TaskTest {

    @Scheduled(cron = "${app.cron}")
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
