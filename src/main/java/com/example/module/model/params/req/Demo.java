package com.example.module.model.params.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * description:
 * Created by lpfei on 2019/7/30
 */
@Data
public class Demo {

    @NotEmpty
    private String name;

    @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
    private String idCardNo;

    @NotEmpty
    private String passWd;

    @NotNull(message = "请输入年龄")
    private Integer age;
}
