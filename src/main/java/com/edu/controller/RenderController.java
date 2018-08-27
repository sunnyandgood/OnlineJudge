package com.edu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class RenderController {

    @GetMapping("/userLogin")
    public String userLogin(){
        return "user_login";
    }

    @GetMapping("/userIndex")
    public String userIndex(){
        return "user/user_index";
    }

    @GetMapping("/userQuestions")
    public String userQuestions(){
        return "user/user_questions";
    }

    @GetMapping("/adminLogin")
    public String adminLogin(){
        return "admin_login";
    }

    @GetMapping("/adminIndex")
    public String adminIndex(){
        return "admin/admin_index";
    }

    @GetMapping("/adminQuestions")
    public String adminQuestions(){
        return "admin/admin_questions";
    }

    @GetMapping("/questionUpdate/{id}")
    public String questionUpdate(){
        return "admin/update/questionUpdate";
    }

    @GetMapping("/questionAdd")
    public String questionAdd() {
        return "admin/add/questionAdd";
    }
}