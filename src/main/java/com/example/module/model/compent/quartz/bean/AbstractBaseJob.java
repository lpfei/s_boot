package com.example.module.model.compent.quartz.bean;

import com.example.core.util.QuartzCronDateUtils;
import jodd.datetime.JDateTime;
import org.quartz.*;

import java.util.Map;

/**
 * description:
 * Created by lpfei on 2019/8/5
 */
public abstract class AbstractBaseJob implements Job {

    private CronScheduleBuilder cronScheduleBuilder(String cronExpression) {
        return CronScheduleBuilder.cronSchedule(cronExpression);
    }

    protected CronTrigger cronTrigger(String name, String group, String cronExpression) {
        return TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder(cronExpression)).build();
    }

    protected CronTrigger cronTrigger(String name, String group, int second) {
        return TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder(QuartzCronDateUtils.getCron(new JDateTime().addSecond(second).convertToDate()))).build();
    }

    public abstract void addJob(String name, String group, int second, Map jobMap) throws SchedulerException;

    public abstract void addJob(int second, Map jobMap) throws SchedulerException;
}
