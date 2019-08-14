package com.example.core.support.spring.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.module.ag.admin.entity.SysPermission2;
import com.example.module.ag.admin.entity.SysRole2;
import com.example.module.ag.admin.entity.SysUser2;
import com.example.module.ag.admin.service.SysUser2Service;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description:admin 权限
 * Created by lpfei on 2019/8/9
 */
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    private SysUser2Service sysUser2Service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //登陆后,验证权限时执行,比如@RequiresRoles
        //执行授权逻辑
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser2 sysUser = (SysUser2) principalCollection.getPrimaryPrincipal();
        for (SysRole2 role : sysUser.getRoleList()) {
            //用户角色集合
            authorizationInfo.addRole(role.getRole());
            for (SysPermission2 p : role.getPermissions()) {
                //用户权限集合
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }

        //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
        //simpleAuthorInfo.addRole("admin");
        //添加权限
        //simpleAuthorInfo.addStringPermission("admin:manage");
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //执行认证逻辑
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        SysUser2 sysUser = sysUser2Service.getOne(new QueryWrapper<SysUser2>().lambda().eq(SysUser2::getUserName, username), false);
        if (sysUser == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser, //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                sysUser.getPassWord(), //密码
//                ByteSource.Util.bytes(sysUser.getSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
