package com.example.core.result;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * description:api controller 响应
 * Created by lpfei on 2019/7/30
 */
public class ApiResult<T> extends AbstractBaseResult<T> {


    private ApiResult(Builder build) {
        this.status = build.status;
        this.message = build.message;
        this.data = (T) build.data;
    }

    @NonNull
    public static <T> ApiResult<T> ok(@Nullable String message, @Nullable T data) {
        return new Builder<T>().status(HttpStatus.OK.value()).message(message).data(data).build();
    }

    @NonNull
    public static <T> ApiResult<T> ok(@Nullable String message) {
        return ok(message, null);
    }

    @NonNull
    public static <T> ApiResult<T> ok(@NonNull T data) {
        return ok(HttpStatus.OK.getReasonPhrase(), data);
    }

    @NonNull
    public static <T> ApiResult<T> ok() {
        return ok("处理成功", null);
    }

    @NonNull
    public static <T> ApiResult<T> error(@Nullable Integer status, @Nullable String message, @Nullable T data) {
        return new Builder<T>().status(status).message(message).data(data).build();
    }

    @NonNull
    public static <T> ApiResult<T> error(@Nullable String message, @Nullable T data) {
        return error(HttpStatus.BAD_REQUEST.value(), message, data);
    }

    @NonNull
    public static ApiResult error(@Nullable Integer status, @Nullable String message) {
        return error(status, message, null);
    }

    @NonNull
    public static ApiResult error() {
        return error("处理失败", null);
    }

    @NonNull
    public static <T> ApiResult<T> error(@Nullable T data) {
        return error("处理失败", data);
    }


    public static class Builder<T> {

        private Integer status;

        private String message;

        private T data;

        public Builder() {
        }

        public Builder status(Integer val) {
            this.status = val;
            return this;
        }

        public Builder message(String val) {
            this.message = val;
            return this;
        }

        public Builder data(T val) {
            this.data = val;
            return this;
        }

        public ApiResult build() {
            return new ApiResult(this);
        }
    }
}
