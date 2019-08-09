package com.example.module.api.user.web;

import com.example.core.result.ApiResult;
import com.example.core.support.spring.quartz.QuartzManager;
import com.example.module.api.user.service.ApiUserService;
import com.example.module.model.compent.quartz.bean.OneJob;
import com.example.module.model.params.req.Demo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * description:
 * Created by lpfei on 2019/7/30
 */
@Slf4j
@RestController
@RequestMapping(value = "api/user")
public class ApiUserController {
    @Autowired
    ApiUserService apiUserService;
    @Autowired
    private QuartzManager quartzManager;

    @PostMapping(value = "test")
    public ApiResult test() {
        return apiUserService.test();
    }

    @PostMapping(value = "user")
    public ApiResult<Demo> user(@RequestBody @Valid Demo demo) {
        log.info("test");
        return ApiResult.ok(demo);
    }

    @PostMapping(value = "user2")
    public ApiResult<Demo> user2(@RequestBody @Valid Demo demo, BindingResult bindingResult) {
        return ApiResult.ok(demo);
    }


    @PostMapping(value = "testq")
    public ApiResult quartzTest() throws SchedulerException {
        String param = "1";
        quartzManager.addJob(OneJob.class, "one_" + param, "one", 10, new HashMap());
        return ApiResult.ok();
    }
}
