package com.example.core.exception.controller;

import com.example.core.exception.BaseException;
import com.example.core.result.BaseResponse;
import com.example.core.util.ExceptionUtils;
import com.example.core.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Map;

/**
 * Exception handler of controller.
 *
 * @author johnniang
 */
@RestControllerAdvice({"com.example.module"})
@Slf4j
public class ControllerExceptionHandler {

    private <T> BaseResponse<T> handleBaseException(Throwable t) {
        Assert.notNull(t, "Throwable must not be null");
        //log.error("Captured an exception", t);
        //校验异常不打印堆栈信息
        if (!(t instanceof MethodArgumentNotValidException
                || t instanceof ConstraintViolationException)) {
            log.error("Captured an exception", t);
        }
        BaseResponse<T> baseResponse = BaseResponse.fail(t.getMessage());
        if (log.isDebugEnabled()) {
            baseResponse.setDevMessage(ExceptionUtils.getStackTrace(t));
        }
        return baseResponse;
    }

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

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            baseResponse = handleBaseException(e.getCause());
        }
        baseResponse.setMessage("Failed to validate request parameter");
        return baseResponse;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setMessage(String.format("Missing request parameter, required %s type %s parameter", e.getParameterType(), e.getParameterName()));
        return baseResponse;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return baseResponse;
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public BaseResponse handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        return baseResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        baseResponse.setMessage("Required request body is missing");
        return baseResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public BaseResponse handleNoHandlerFoundException(NoHandlerFoundException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        baseResponse.setStatus(status.value());
        baseResponse.setMessage(status.getReasonPhrase());
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
        BaseResponse baseResponse = handleBaseException(ex);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        baseResponse.setMessage(ValidationUtils.stringWithFieldError(ex.getBindingResult().getFieldErrors()));
        Map<String, String> errMap = ValidationUtils.mapWithFieldError(ex.getBindingResult().getFieldErrors());
        baseResponse.setData(errMap);
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
        BaseResponse baseResponse = handleBaseException(ex);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        baseResponse.setMessage(ValidationUtils.stringWithValidError(ex.getConstraintViolations()));
        baseResponse.setData(ValidationUtils.mapWithValidError(ex.getConstraintViolations()));
        return baseResponse;
    }


    /**
     * ----------------------------shiro 异常
     * AuthenticationException 异常是Shiro在登录认证过程中，认证失败需要抛出的异常。 AuthenticationException包含以下子类：
     * --CredentitalsException 凭证异常
     * ----IncorrectCredentialsException 不正确的凭证
     * ----ExpiredCredentialsException 凭证过期
     * <p>
     * --AccountException 账号异常
     * ----ConcurrentAccessException 并发访问异常（多个用户同时登录时抛出）
     * ----UnknownAccountException 未知的账号
     * ----ExcessiveAttemptsException 认证次数超过限制
     * ----DisabledAccountException 禁用的账号
     * ----LockedAccountException 账号被锁定
     * --UnsupportedTokenException 使用了不支持的Token
     * <p>
     * AuthorizationException:子类:
     * --UnauthorizedException:抛出以指示请求的操作或对请求的资源的访问是不允许的。
     * --UnanthenticatedException:当尚未完成成功认证时，尝试执行授权操作时引发异常。
     */

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse handleHttpMessageUnknownAccountException(UnknownAccountException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        baseResponse.setMessage("账号不存在");
        return baseResponse;
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse handleHttpMessageIncorrectCredentialsException(IncorrectCredentialsException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        baseResponse.setMessage("账号/密码错误");
        return baseResponse;
    }

    @ExceptionHandler(ExpiredCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse handleHttpMessageExpiredCredentialsException(ExpiredCredentialsException e) {
        BaseResponse<?> baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        baseResponse.setMessage("登陆超时,请重新登陆");
        return baseResponse;
    }
}

