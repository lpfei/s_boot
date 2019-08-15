package com.example.module.ag.user.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.module.ag.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lpfei
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/user/sys-user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
}
