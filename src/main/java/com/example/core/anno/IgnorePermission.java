package com.example.core.anno;


import com.example.core.enums.IgnoreType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by fanlei on 2015/4/20.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnorePermission {
    IgnoreType value() default IgnoreType.ALL;
}
