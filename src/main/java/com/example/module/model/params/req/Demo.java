package com.example.module.model.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * description:
 * Created by lpfei on 2019/7/30
 */
@Data
@ApiModel(value = "demo对象", description = "demo测试")
public class Demo {


    //    @NotEmpty
    @ApiModelProperty(value = "名称")
    private String name;

    //    @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
    @ApiModelProperty(value = "身份证号")
    private String idCardNo;

    //    @NotEmpty
    @ApiModelProperty(value = "密码")
    private String passWd;

    //    @NotNull(message = "请输入年龄")
    @ApiModelProperty(value = "年龄")
    private Integer age;
}
