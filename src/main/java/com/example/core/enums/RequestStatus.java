package com.example.core.enums;

/**
 * description:请求响应状态
 * Created by lpfei on 2019/7/31
 */
public enum RequestStatus implements ValueEnum<Integer> {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 失败
     */
    FAIL(-1),
    /**
     * 未知
     */
    UNNOWN(0);

    private Integer status;

    RequestStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Integer getValue() {
        return this.status;
    }
}
