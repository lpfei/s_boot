package com.example.core.config;

import com.example.core.filter.Interceptor;
import com.example.core.filter.LogFilter;
import com.example.core.properties.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
@Slf4j
@Configuration
@EnableWebMvc
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    private final AppProperties appProperties;

    public WebMvcAutoConfiguration(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public Interceptor interceptor() {
        return new Interceptor();
    }

    @Bean
    public FilterRegistrationBean registrationBean(LogFilter logFilter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(logFilter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 9);
        //重复执行filter让其执行一次
        registrationBean.setEnabled(false);
        registrationBean.addUrlPatterns("/*");
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
        //addResourceLocations 指定文件放置的位置 addResourceHandler  指的是对外暴露的地址
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/assets/");
        /*swagger*/
        log.info("swagger doc isDocDisabled : {}", appProperties.isDocDisabled());
        if (!appProperties.isDocDisabled()) {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }

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
