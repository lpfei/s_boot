package com.example.module.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: lpfei.
 * @Description: 首页
 * @Date:Created in 2017/9/14.
 * @Modified By:
 */
@Controller
@Slf4j
@Api(value = "首页", tags = "首页")
public class HomeController {

    /**
     * 跳转首页
     *
     * @return
     */
    @GetMapping(value = {"/", "home"})
    @ApiOperation(value = "首页", notes = "首页")
    public String home(Model model) {
        model.addAttribute("test", "---------------");
        return "btzero/index";
    }

    /**
     * 博客列表
     *
     * @return
     */
    @GetMapping(value = "blog")
    public String blog() {
        return "btzero/blog-archive";
    }

    /**
     * 博客详情
     *
     * @return
     */
    @GetMapping(value = "single")
    public String single() {
        return "btzero/blog-single";
    }
}
