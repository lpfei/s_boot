package com.example.module.api.user.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lpfei.
 * @Description: 首页
 * @Date:Created in 2017/9/14.
 * @Modified By:
 */
@Controller
@Slf4j
public class HomeController {


    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = {"/", "home"})
    public String home(Model model) {
        model.addAttribute("test", "---------------");
        return "btzero/index";
    }

    /**
     * 博客列表
     *
     * @return
     */
    @RequestMapping(value = "blog")
    public String blog() {
        return "btzero/blog-archive";
    }

    /**
     * 博客详情
     *
     * @return
     */
    @RequestMapping(value = "single")
    public String single() {
        return "btzero/blog-single";
    }
}
