package com.edu.controller;

import com.edu.bean.User;
import com.edu.service.UserService;
import com.edu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserLoginControl {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R loginJudge(User user, HttpSession session){
        List<User> lists = userService.selectList(null);
//        for(int i=0;i<lists.size();i++){
//            User user1 = lists.get(i);
//            if(user1.getNuserNumber().equals(user.getNuserNumber())
//                    && user1.getUserPassword().equals(user.getUserPassword())){
//                session.setAttribute("nuserNumber",user.getNuserNumber());
//                session.setAttribute("userPassword",user.getUserPassword());
//                return R.ok();
//            }
//
//        }
        for(User user1 : lists){
            if(user1.getNuserNumber().equals(user.getNuserNumber()) &&
                    user1.getUserPassword().equals(user.getUserPassword())){
                //将登录状态放入session里
                session.setAttribute("user",user1);
                return R.ok();
            }
        }
        return R.error("用户名与密码不匹配");
    }

    @GetMapping("/logout")
    public void logout(HttpSession session){
//        session.invalidate();
    }
}
