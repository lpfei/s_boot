package com.example.core.anno;

/**
 * Created by chang on 2017/3/6.
 */
public @interface Token {

    boolean save() default false;

    boolean check() default false;
}
