package com.example.module.api.user.service;

import com.example.core.result.ApiResult;
import com.example.module.ag.user.entity.SysUser;
import com.example.module.ag.user.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * Created by lpfei on 2019/7/30
 */
@Service
public class ApiUserService extends SysUserService {

    public ApiResult test() {
        PageHelper.startPage(1, 1);
        List<SysUser> list = this.list();
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return ApiResult.error();
    }
}
