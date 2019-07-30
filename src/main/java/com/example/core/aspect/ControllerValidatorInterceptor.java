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
 * description:
 * Created by lpfei on 2019/7/30
 */
@Component
@Aspect
public class ControllerValidatorInterceptor {

    /*@Around("execution(* com.example.module.api.user.web.*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                return new BaseResponse(HttpStatus.PRECONDITION_FAILED.value(), error.getDefaultMessage(), null);
            }
        }
        return pjp.proceed();
    }*/
}
