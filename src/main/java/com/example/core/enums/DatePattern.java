package com.example.core.enums;

/**
 * description:
 * Created by lpfei on 2019/7/31
 */

public enum DatePattern implements ValueEnum<String> {
    /**
     * YYYY-MM-DD hh:mm:ss
     */
    YMDHMS("YYYY-MM-DD hh:mm:ss"),
    /**
     * YYYY-MM-DD
     */
    YMD("YYYY-MM-DD");

    private String pattern;

    DatePattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getValue() {
        return this.pattern;
    }
}
