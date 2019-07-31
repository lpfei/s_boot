package com.example.module.api.user.service;

import com.example.core.result.ApiResult;
import com.example.module.ag.user.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * description:
 * Created by lpfei on 2019/7/30
 */
@Service
public class ApiUserService extends SysUserService {

    public ApiResult test() {
        return ApiResult.error();
    }
}
