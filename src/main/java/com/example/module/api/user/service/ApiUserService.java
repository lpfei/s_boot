package com.example.module.api.user.service;

import com.example.core.result.ApiResult;
import com.example.module.ag.user.service.SysUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * description:
 * Created by lpfei on 2019/7/30
 */
@Service
public class ApiUserService extends SysUserService {

    public ApiResult test() {
        this.list();
        return ApiResult.error();
    }
}
