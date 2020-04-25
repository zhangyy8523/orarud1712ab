package com.bwie.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.bwie.model.TUser;
import com.bwie.service.UserService;
import com.bwie.utils.StaticFlag;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @描述：用户模块
 * @作者：zhangyuyang
 * @日期：2020/4/22 8:56
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/toRegister")
    public String  toRegist(){

        return "register";
    }


    /**
     * 注册功能
     * @return
     */
    @RequestMapping("/registerToUser")
    public String registerToUser(TUser tUser){
        if(null!=tUser){
            tUser.setId(RandomUtil.randomUUID().replace("-","")); //设置主键的id
            tUser.setCreateTime(new Date()); //设置添加的时间
            String md5Password = SecureUtil.md5(tUser.getPassword());//密码md5 加密
            tUser.setPassword(md5Password);
        }
        //没有效验手机号是否存在
        int flag = userService.registerToUser(tUser); //一般数据库的操作，如果成功，返回的状态就是大于0状态
        return flag>0?"login":"register"; //三目判断
    }

    @RequestMapping("/checkPhone")
    @ResponseBody
    public Map<String,String> checkPhone(String phone){
      String msg =userService.checkPhone(phone);
        Map<String,String> map =new HashMap<>();
        map.put("msg",msg);
        return map;
    }


    /**
     * 登录的效验
     */
    @RequestMapping(value = "/checkPasswordAndUserName",method = RequestMethod.POST)
    public String checkUserNameAndPassword(String loginName, String  password, Model model, HttpServletRequest request){
        if(!StringUtils.isEmpty(loginName) && !StringUtils.isEmpty(password)){
           TUser user =  userService.checkUserNameAndPassword(loginName,password);
               if (null==user){ //用户名或者密码错误
                   model.addAttribute("msg","用户名或者密码错误");
                    return "login";
               }else{
                   //放入session
                   request.getSession().setAttribute(StaticFlag.USERINFO,user);
                   model.addAttribute("msg"," ");
                   //返回到列表页面
                   return "redirect:/book/queryBookList" ;
               }
        }else{
            model.addAttribute("msg","用户名密码不能为空");
            //如果用户名或者密码为空，则返回到登录页面
            return "login";
        }
    }


    @RequestMapping("/removeSessionToLogin")
    public String removeSessionToLogin(HttpServletRequest request){
        request.getSession().removeAttribute(StaticFlag.USERINFO);
        return "redirect:/toLogin";
    }
}
