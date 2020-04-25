package com.bwie.filter;



import com.bwie.model.TUser;
import com.bwie.utils.StaticFlag;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @描述： 登录的拦截
 * @作者：zhangyuyang
 * @日期：2020/3/27 10:42
 */
/*把当前类 注入到spring boot的组件中*/
@Component
public class LoginIncerceptor implements HandlerInterceptor {


    /***
     * 校验登录session状态
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入方法之前");
        /*从 session获取user的信息， 如果有user的信息，则认为他是已经登陆过的，
        如果没有user的数据，则认为他是非法访问，
        让他回登陆页面*/
        try {
            HttpSession session = request.getSession();
            TUser user = (TUser) session.getAttribute(StaticFlag.USERINFO);
            if(null == user){
                response.sendRedirect("/toLogin");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("进入方法处理业务之后");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(">>>LoginIncerceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
