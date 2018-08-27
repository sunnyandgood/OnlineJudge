package com.edu.interceptor;

import com.edu.bean.User;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {
    private boolean flag = false;

    @Autowired
    private UserService userService;

    //URL请求前使用
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object o)
            throws Exception {

        if(request.getRequestURL().indexOf("loginJudge")>0){
            flag = true;
            return true;
        }else {
            //获取session里的登录状态
            HttpSession session = request.getSession();
            String nuserNumber = session.getAttribute("nuserNumber").toString();
            String userPassword = (String)session.getAttribute("userPassword");
            List<User> users = userService.selectList(null);

            //判断session里的登录状态在数据库里是否存在
            for(User user : users){
                if(user.getNuserNumber().equals(nuserNumber) && user.getUserPassword().equals(userPassword)){
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
            return false;
        }
    }

    //URL请求时（视图请求）使用
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object o, ModelAndView modelAndView)
            throws Exception {
        if(!flag){
            modelAndView.setViewName("user_login");
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
