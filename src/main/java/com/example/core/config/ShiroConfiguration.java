package com.example.core.config;

import com.example.core.support.spring.shiro.AdminRealm;
import com.example.core.support.spring.shiro.ShiroEncrypt;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * Created by lpfei on 2019/8/9
 */
@Configuration
public class ShiroConfiguration {


    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/login", "anon");
        /**
         * 常见过滤器：
         * anon：无需认证（登录）可以访问
         * authc：必须认证才可以访问
         * user:如果使用Remember Me的功能，可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */
        definition.addPathDefinition("/add", "authc");
        definition.addPathDefinition("/update", "authc");
        //角色权限
        definition.addPathDefinition("/admin/**", "roles[admin]");
        //允许访问静态资源
        definition.addPathDefinition("/static/**", "anon");
        //所有可以访问
        definition.addPathDefinition("/**", "authc");
        return definition;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean
    DefaultWebSecurityManager getDefaultDefaultWebSecurityManager(AdminRealm adminRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(adminRealm);
        return securityManager;
    }

    /**
     * 自定义matcher比较器中的一个比较器属性
     * 该属性可以设置
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置加密次数
        matcher.setHashIterations(ShiroEncrypt.getHASHITERATIONS());
        //设置加密方式
        matcher.setHashAlgorithmName(ShiroEncrypt.getHASHALGORITHMNAME());
        return matcher;
    }

    /**
     * 创建Realm
     */
    @Bean
    AdminRealm getRealm() {
        AdminRealm adminRealm = new AdminRealm();
        //登陆认证时,加了盐值,则需注入比较器
        //adminRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return adminRealm;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
}
