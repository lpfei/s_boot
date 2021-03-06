package com.example.module.model.compent.task;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 *
 * @author chen
 * @date 2017/9/19
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class SaveLogTask implements Runnable {
    private ProceedingJoinPoint joinPoint;
    private long time;
    private String ip;

    public SaveLogTask(ProceedingJoinPoint point, long time, String ip) {
        this.joinPoint = point;
        this.time = time;
        this.ip = ip;
    }

    @Override
    public void run() {
        saveLog(joinPoint, time, ip);
    }

    /**
     * 保存日志 到数据库
     *
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time, String ip) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        /*SlifeLog slifeLog = new SlifeLog();
        SLog sLog = method.getAnnotation(SLog.class);

        if (slifeLog != null) {
            // 注解上的描述
            slifeLog.setMsg(sLog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        slifeLog.setSrc(className + "." + methodName + "()");

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSON.toJSONString(args[0]);
            slifeLog.setParams(params);
        } catch (Exception e) {

        }

        // 设置IP地址
        slifeLog.setIp(ip);
        // 用户名
        ShiroUser currUser = SlifeSysUser.ShiroUser();

        if (null == currUser) {
            if (null != slifeLog.getParams()) {
                slifeLog.setName(slifeLog.getParams());
                slifeLog.setLoginName(slifeLog.getParams());
            } else {
                slifeLog.setName("获取用户信息为空");
                slifeLog.setLoginName("获取用户信息为空");
                slifeLog.setCreateId(-1L);
            }
        } else {
            slifeLog.setName(currUser.getName());
            slifeLog.setLoginName(currUser.getUsername());

        }

        slifeLog.setUseTime(time);


        // 保存系统日志
        slifeLogDao.insert(slifeLog);*/
    }
}
