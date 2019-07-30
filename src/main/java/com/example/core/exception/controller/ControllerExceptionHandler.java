package com.example.core.exception.controller;

import com.example.core.exception.BaseException;
import com.example.core.result.BaseResponse;
import com.example.core.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler of controller.
 *
 * @author johnniang
 */
@RestControllerAdvice({"com.example.module"})
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse> handleHaloException(BaseException e) {
        BaseResponse<Object> baseResponse = handleBaseException(e);
        baseResponse.setStatus(e.getStatus().value());
        baseResponse.setData(e.getErrorData());
        return new ResponseEntity<>(baseResponse, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleGlobalException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(status.getReasonPhrase());
        return baseResponse;
    }

    private <T> BaseResponse<T> handleBaseException(Throwable t) {
        Assert.notNull(t, "Throwable must not be null");
        log.error("Captured an exception", t);
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(t.getMessage());
        if (log.isDebugEnabled()) {
            baseResponse.setDevMessage(ExceptionUtils.getStackTrace(t));
        }
        return baseResponse;
    }

}

