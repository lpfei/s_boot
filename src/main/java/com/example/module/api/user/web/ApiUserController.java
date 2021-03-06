package com.example.module.api.user.web;

import com.example.module.api.user.service.ApiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
