package com.example.module.model.compent.quartz.bean;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * Created by lpfei on 2019/8/5
 */
@Slf4j
@Component
public class OneJob extends AbstractBaseJob {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(int second, Map jobMap) throws SchedulerException {
        String name = "one-job-";
        if (jobMap != null) {
            name += jobMap.get("param");
        } else {
            jobMap = new HashMap();
        }
        addJob(name, "one-group", second, jobMap);
    }

    @Override
    public void addJob(String name, String group, int second, Map jobMap) throws SchedulerException {
        Assert.hasText(name, "name not empty");
        Assert.hasText(group, "group not empty");
        Assert.notNull(jobMap, "jobMap not null");
        JobDetail jobDetail = JobBuilder.newJob(this.getClass()).withIdentity(name, group).setJobData(new JobDataMap(jobMap)).build();
        CronTrigger cronTrigger = super.cronTrigger(name, group, second);
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("OneJob running ...");
    }

}
