package com.example.core.properties;

import com.example.core.consts.AppConst;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * description:
 * Created by lpfei on 2019/7/30
 */
@Data
@ConfigurationProperties("app")
@Component
public class AppProperties {
    /**
     * swagger
     * 0.参数名称与配置文件设置对应,可直接获取
     * app:
     *   docDisabled: false
     */
    private boolean docDisabled = true;

    private String workDir = AppConst.USER_HOME + "/.springboot/";

    public AppProperties() throws IOException {
        Files.createDirectories(Paths.get(workDir));
    }

    /**
     * 1.静态属性赋值
     * 需要@Component + set@value
     *
     * 普通参数获取直接使用@value
     * @Value("${}")
     * private  String env;
     */
    private static String env;

    @Value("${spring.profiles.active}")
    public void setEnv(String env) {
        AppProperties.env = env;
    }

    /**
     * 2.使用spring Environment 获取配置文件参数
     */
    @Autowired
    private Environment environment;

    public void main(String[] args) {
        environment.getProperty("");
    }


    /** 3.从配置文件获取定时任务执行配置
     * app:
     *  cron: '0/3 * * * * ?'
     * @Scheduled(cron = "${app.cron}")*/
}
