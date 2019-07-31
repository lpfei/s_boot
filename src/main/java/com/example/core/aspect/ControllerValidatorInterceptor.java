package com.example.core.aspect;

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
     * @param pjp
     * @param BindingResult bindingResult
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.example.module.api.*.web.*.*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                BaseResponse baseResponse = BaseResponse.fail(error.getDefaultMessage());
                baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                return baseResponse;
            }
        }
        return pjp.proceed();
    }
}
