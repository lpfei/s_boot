package com.example.core.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * Created by lpfei on 2019/7/30
 */
@Configuration
@ServletComponentScan("com.example.**")
public class ServletConfiguration {
}
