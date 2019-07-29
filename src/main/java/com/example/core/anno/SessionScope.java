package com.example.core.anno;

import java.lang.annotation.*;

/**
 * session获取参数 注解
 *
 * @author fanlei
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionScope {
    String value();
}
