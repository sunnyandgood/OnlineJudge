package com.edu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class RenderController {

//    @GetMapping("/userLogin")
//    public String userLogin(){
//        return "user_login";
//    }

    @GetMapping("/userIndex")
    public String userIndex(){
        return "user/user_index";
    }

    @GetMapping("/userQuestions")
    public String userQuestions(){
        return "user/user_questions";
    }

//    @GetMapping("/adminLogin")
//    public String adminLogin(){
//        return "admin_login";
//    }

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

    @GetMapping("/adminUserAnswer")
    public String adminUserAnswer(){
        return "admin/admin_answer";
    }


    @GetMapping("/adminUserScore")
    public String adminUserScore(){
        return "admin/admin_score";
    }

    @GetMapping("/scoreUpdate/{scoreId}")
    public String scoreUpdate(){
        return "admin/update/scoreUpdate";
    }

    @GetMapping("/adminUser")
    public String adminUser(){
        return "admin/admin_user";
    }

    @GetMapping("/userUpdate/{id}")
    public String userUpdate(){
        return "admin/update/userUpdate";
    }

    @GetMapping("/userAdd")
    public String userAdd(){
        return "admin/add/userAdd";
    }

    @GetMapping("/adminAdmin")
    public String adminAdmin(){
        return "admin/admin_admin";
    }

    @GetMapping("/adminUpdate/{id}")
    public String adminUpdate(){
        return "admin/update/adminUpdate";
    }

    @GetMapping("/adminAdd")
    public String adminAdd(){
        return "admin/add/adminAdd";
    }

    @GetMapping("/questionUp")
    public String questionUp(){
        return "admin/upload/question_up";
    }

    @GetMapping("/userUp")
    public String userUp(){
        return "admin/upload/user_up";
    }
}
