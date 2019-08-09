package com.example.core.support.spring.quartz;

import com.example.core.util.QuartzCronDateUtils;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description:
 * Created by lpfei on 2019/8/5
 */
@Slf4j
@Component
public class QuartzManager {
    @Autowired
    private Scheduler scheduler;

    private CronScheduleBuilder cronScheduleBuilder(String cronExpression) {
        return CronScheduleBuilder.cronSchedule(cronExpression);
    }

    private CronTrigger cronTrigger(String name, String group, String cronExpression) {
        return TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder(cronExpression)).build();
    }

    private CronTrigger cronTrigger(String name, String group, int second) {
        return TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder(QuartzCronDateUtils.getCron(new JDateTime().addSecond(second).convertToDate()))).build();
    }

    /**
     * 删除一个任务job
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        this.scheduler.deleteJob(new JobKey(name, group));
    }

    public void addJob(Class<? extends Job> jobClass, String jobName, String jobGroupName, int jobTime, Map jobMap) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).setJobData(new JobDataMap(jobMap)).build();
        CronTrigger cronTrigger = cronTrigger(jobName, jobGroupName, jobTime);
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
