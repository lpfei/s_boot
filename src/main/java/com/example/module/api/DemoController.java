package com.example.module.api;

import com.example.core.result.ApiResult;
import com.example.core.support.spring.quartz.QuartzManager;
import com.example.module.model.compent.quartz.bean.OneJob;
import com.example.module.model.params.req.Demo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * description:
 * Created by lpfei on 2019/8/14
 */
@Slf4j
@RestController
public class DemoController {

    @Autowired
    private QuartzManager quartzManager;

    /**
     * HibernateValidator test
     *
     * @param demo
     * @return
     */
    @PostMapping(value = "valid")
    public ApiResult<Demo> user(@RequestBody @Valid Demo demo) {
        return ApiResult.ok(demo);
    }

    /**
     * HibernateValidator test
     *
     * @param demo
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "valid2")
    public ApiResult<Demo> user2(@RequestBody @Valid Demo demo, BindingResult bindingResult) {
        return ApiResult.ok(demo);
    }

    /**
     * 任务调度 test
     *
     * @return
     * @throws SchedulerException
     */
    @PostMapping(value = "quartzTest")
    public ApiResult quartzTest() throws SchedulerException {
        String param = "1";
        quartzManager.addJob(OneJob.class, "one_" + param, "one", 10, new HashMap());
        return ApiResult.ok();
    }

    /**
     * shiro登陆 test
     *
     * @return
     */
    @PostMapping(value = "login")
    public Object login() {
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        //调用realm认证
        subject.login(token);
        return ApiResult.ok();
    }

    @PostMapping(value = "permission")
    @RequiresRoles({"admin"})
    public Object permission() {
        return ApiResult.ok();
    }
}
