package com.bwie.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：资源配置
 * @作者：zhangyuyang
 * @日期：2020/4/22 10:37
 */
@Configuration
public class MvcResurce  extends WebMvcConfigurationSupport {


    /***
     * 资源的过滤跟配置
     *
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list =  new ArrayList<String>();
        list.add("/toLogin"); //请求的地址
        list.add("/"); //请求的地址
        list.add("/user/checkPasswordAndUserName"); //请求的地址
        list.add("/user/registerToUser"); //注册
        list.add("/user/toRegister"); //去注册页面


        list.add("/static/css/**"); //请求的地址
        list.add("/static/js/**"); //请求的地址
        list.add("/static/img/**"); //请求的地址
        list.add("/static/bootstrap-datetimepicker/**"); //请求的地址
        list.add("/static/kindeditor/**"); //请求的地址
        /*    addPathPatterns  /** 代表拦截所有  拦截的规则
        excludePathPatterns 配置不拦截的规则
         */
        registry.addInterceptor(new LoginIncerceptor()).addPathPatterns("/**").
                excludePathPatterns(list);

    }




    /*磁盘上的路径*/
    @Value("${file.upload.path}")
    private String filePath ;
    /*相对路径*/
    @Value("${file.upload.relative}")
    private String relativePath;

    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler(relativePath).addResourceLocations("file:/"+filePath);

    }


}
