package com.example.module.model.compent.quartz.bean;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * description:
 * Created by lpfei on 2019/8/5
 */
@Slf4j
@Component
public class OneJob extends AbstractBaseJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("OneJob running ...");
    }

}
