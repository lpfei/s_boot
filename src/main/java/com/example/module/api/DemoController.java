package com.example.module.api;

import com.example.core.result.ApiResult;
import com.example.core.support.spring.quartz.QuartzManager;
import com.example.module.ag.admin.entity.SysRole2;
import com.example.module.ag.admin.entity.SysUser2;
import com.example.module.ag.admin.service.SysRole2Service;
import com.example.module.api.admin.JpaSysUser2Service;
import com.example.module.api.user.service.ApiUserService;
import com.example.module.model.compent.quartz.bean.OneJob;
import com.example.module.model.params.req.Demo;
import com.github.pagehelper.PageHelper;
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
    @Autowired
    private JpaSysUser2Service jpaSysUser2Service;
    @Autowired
    SysRole2Service sysRole2Service;

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

    /**
     * 验证权限 test
     * 触发doGetAuthorizationInfo,授权逻辑
     *
     * @return
     */
    @PostMapping(value = "permission")
    @RequiresRoles({"admin"})
    public Object permission() {
        return ApiResult.ok();
    }


    /**
     * jpa 级联操作用户-角色
     *
     * @return
     */
    @PostMapping(value = "relation")
    @RequiresUser
    public Object relation() {
        SysUser2 sysUser2 = new SysUser2();
        sysUser2.setId(2);
        sysUser2.setUserName("admin");
        SysRole2 sysRole2 = new SysRole2();
        sysRole2.setId(2);
        sysRole2.setRole("11");

        sysUser2.getRoleList().add(sysRole2);
        jpaSysUser2Service.save(sysUser2);
        return ApiResult.ok();
    }


    @Autowired
    private ApiUserService apiUserService;

    /**
     * pageheper test
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "list")
    public Object list(Integer pageNum, Integer pageSize) {
        return ApiResult.ok(apiUserService.test());
    }
}
