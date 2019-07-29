package com.example;

import com.example.core.filter.Interceptor;
import com.example.core.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * mvc配置类
 * Created by lpfei on 2017/4/14.
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public Interceptor interceptor() {
        return new Interceptor();
    }

    @Bean
    public FilterRegistrationBean registrationBean(LogFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        //重复执行filter让其执行一次
        registrationBean.setEnabled(false);
        return registrationBean;
    }


    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceLocations 指定文件放置的位置
        //addResourceHandler  指的是对外暴露的地址
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/assets/");
    }


    /**
     * 无业务逻辑跳转配置
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*请求方法index跳转到index页面*/
        registry.addViewController("home").setViewName("index");
    }
}
