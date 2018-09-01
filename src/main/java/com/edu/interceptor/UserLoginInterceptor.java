package com.edu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {
    private boolean flag = false;

    //URL请求前使用
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object o)
            throws Exception {

        System.out.println(request.getRequestURL());
        if(request.getRequestURL().indexOf("/user/login")>0){
            flag = true;
            return true;
        }else {
            //获取session里的登录状态
            HttpSession session = request.getSession();

            //判断session里的登录状态在数据库里是否存在
                if(session.getAttribute("user") != null){
                    flag = true;
                    return true;
                }else {
                    flag = false;
                    if(request.getRequestURL().indexOf("page")>0){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
        }

        //URL请求时（视图请求）使用
        @Override
        public void postHandle(HttpServletRequest request,
                               HttpServletResponse response,
                               Object o, ModelAndView modelAndView)
                throws Exception {
            if(!flag){
                modelAndView.setViewName("forward:/user_login.jsp");
        }
    }

    //全部请求完成后
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object o, Exception e)
            throws Exception {

    }
}
