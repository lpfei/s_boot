package com.example.core.support.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * Created by lpfei on 2019/10/28
 */
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * mybatis plus 3+ 分页不生效解决方式
     *         <dependency>
     *             <groupId>com.github.pagehelper</groupId>
     *             <artifactId>pagehelper</artifactId>
     *             <version>5.1.10</version>
     *         </dependency>
     *         <!-- pagehelper 依赖 -->
     *         <dependency>
     *             <groupId>com.github.jsqlparser</groupId>
     *             <artifactId>jsqlparser</artifactId>
     *             <version>2.1</version>
     *         </dependency>
     */
    /*@Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(MybatisConfiguration configuration) {
                configuration.addInterceptor(new com.github.pagehelper.PageInterceptor());
            }
        };
    }*/
}
