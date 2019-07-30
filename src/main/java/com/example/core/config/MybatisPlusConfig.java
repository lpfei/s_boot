package com.example.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * description:mapper扫描配置
 * Created by lpfei on 2019/7/30
 */
@Configuration
@MapperScan("com.example.**.mapper")
public class MybatisPlusConfig {
}
