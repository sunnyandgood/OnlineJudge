package com.edu.controller;

import com.edu.bean.Admin;
import com.edu.service.AdminService;
import com.edu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminLoginControl {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public R loginJudge(Admin admin, HttpSession session){
        List<Admin> admins = adminService.selectList(null);
        for(Admin admin1 : admins){
            if(admin1.getAdminName().equals(admin.getAdminName()) &&
                    admin1.getAdminPassword().equals(admin.getAdminPassword())){
                //将登录状态放入session里
                session.setAttribute("admin",admin);
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
