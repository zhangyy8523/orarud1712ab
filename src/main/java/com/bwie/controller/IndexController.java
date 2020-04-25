package com.bwie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/22 10:01
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String toLogin(){
        return  "login";
    }
    @RequestMapping("/toLogin")
    public String toLogin2(){
        return  "login";
    }
}
