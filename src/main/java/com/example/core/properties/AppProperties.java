package com.example.core.properties;

import com.example.core.consts.AppConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
     */
    private boolean docDisabled = true;

    private String workDir = AppConst.USER_HOME + "/.springboot/";

    public AppProperties() throws IOException {
        Files.createDirectories(Paths.get(workDir));
    }
}
