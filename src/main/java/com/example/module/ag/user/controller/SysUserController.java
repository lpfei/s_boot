package com.example.module.ag.user.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.result.ApiResult;
import com.example.core.util.page.PageModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.module.ag.user.entity.SysUser;
import com.example.module.ag.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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
    private SysUserService SysUserService;

    /**
     * 获取数据列表
     */
    @RequestMapping("/list")
    public Object findListByPage(Integer pageNum,Integer pageSize){
        PageHelper.startPage(PageModel.init(pageNum,pageSize));
        List<SysUser> list = SysUserService.list();
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return ApiResult.ok(pageInfo.getList());
    }

}
