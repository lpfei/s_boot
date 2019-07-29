package com.example.core.anno;


import com.example.core.enums.LogEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志记录 注解
 *
 * @author fanlei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 操作类型
     *
     * @return
     */
    LogEnum.Type type() default LogEnum.Type.SPACE;

    /**
     * 操作描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 操作模块
     *
     * @return
     */
    LogEnum.Module module();

}
