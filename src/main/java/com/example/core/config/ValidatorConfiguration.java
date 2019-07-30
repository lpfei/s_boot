package com.example.core.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * description: HibernateValidator
 * Created by lpfei on 2019/7/30
 * 普通模式:会校验完所有的属性，然后返回所有的验证失败信息
 * 快速返回:只要有一个验证失败，则返回
 */
@Configuration
public class ValidatorConfiguration {
    /*
     * post方式 @RequestBody @Valid Demo demo, BindingResult result
     * */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }

    /*
     * get方式 RequestParam
     * 需在controller加上@Validated
     * */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        /**设置validator模式为快速失败返回*/
        postProcessor.setValidator(validator());
        return postProcessor;
    }
}
