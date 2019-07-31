package com.example.core.exception.controller;

import com.example.core.exception.BaseException;
import com.example.core.result.BaseResponse;
import com.example.core.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

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
        BaseResponse<T> baseResponse = BaseResponse.fail(t.getMessage());
        if (log.isDebugEnabled()) {
            baseResponse.setDevMessage(ExceptionUtils.getStackTrace(t));
        }
        return baseResponse;
    }

    /**
     *  ----------------------HibernateValidator 全局校验处理方式 1--------------------------------------
     *  根据HibernateValidator校验未通过抛出的异常处理响应
     *  ----------------------HibernateValidator 全局校验处理方式 2--------------------------------------
     *  ControllerValidatorInterceptor 类切面处理
     */


    /**
     * HibernateValidator 校验未通过处理@requestBody
     *
     * @param ex
     * @param <T>
     * @return
     * @requestBody 抛出MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> BaseResponse<T> validationErrorHandler(MethodArgumentNotValidException ex) {
        // 同样是获取BindingResult对象，然后获取其中的错误信息
        // 如果前面开启了fail_fast，事实上这里只会有一个信息
        // 如果没有，则可能又多个
        List<String> errorInformation = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        BaseResponse baseResponse = handleBaseException(ex);
        baseResponse.setMessage(errorInformation.toString());
        return baseResponse;
    }

    /**
     * HibernateValidator 校验未通过处理@Validated
     *
     * @param ex
     * @param <T>
     * @return
     * @requestParam 抛出ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> BaseResponse<T> validationErrorHandler(ConstraintViolationException ex) {
        List<String> errorInformation = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        BaseResponse baseResponse = handleBaseException(ex);
        baseResponse.setMessage(errorInformation.toString());
        return baseResponse;
    }
}

