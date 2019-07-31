package com.example.core.result;

import lombok.Data;

/**
 * description:
 * Created by lpfei on 2019/7/31
 */
@Data
public abstract class AbstractBaseResult<T> {

    /**
     * Response status.
     */
    protected Integer status;

    /**
     * Response message.
     */
    protected String message;

    /**
     * Response development message
     */
    protected String devMessage;

    /**
     * Response data
     */
    protected T data;

    protected Integer code;

    public AbstractBaseResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    protected AbstractBaseResult() {
    }
}
