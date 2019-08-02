package com.example.core.aspect;

import com.example.core.result.ApiResult;
import com.example.core.result.BaseResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * description: 切面全局处理HibernateValidator
 * Created by lpfei on 2019/7/30
 */
@Aspect
@Component
public class ControllerValidatorInterceptor {

    /**
     * 仅作demo使用
     *
     * @param pjp
     * @param BindingResult bindingResult
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.example.module.api.*.web.*.*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                ApiResult apiResult = ApiResult.error(error.getDefaultMessage());
                return apiResult;
            }
        }
        return pjp.proceed();
    }
}
