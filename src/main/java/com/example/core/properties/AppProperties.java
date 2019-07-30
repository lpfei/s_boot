package com.example.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
}
