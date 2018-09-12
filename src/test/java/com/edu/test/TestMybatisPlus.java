package com.edu.test;

import com.edu.bean.Admin;
import com.edu.bean.Answer;
import com.edu.bean.Score;
import com.edu.service.AdminService;
import com.edu.service.AnswerService;
import com.edu.service.ScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@ContextConfiguration("classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMybatisPlus {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private ScoreService scoreService;
    @Test
    public void testSelectById(){
        Admin admin = adminService.selectById(1);
        System.out.println(admin);
    }

    @Test
    public void testSelectAnswerUserQuestion(){
        List<Answer> answers = answerService.selectAnswerUserQuestion();
        System.out.println(answers);
    }

    @Test
    public void testSelectScoreUserQuestion(){
        List<Score> scores = scoreService.selectScoreUserQuestion();
        System.out.println(scores);
    }

    @Test
    public void testSelectAnswerByUserQuestion(){
        Answer answer = answerService.selectAnswerByUserQuestion(3, 3);
        System.out.println(answer);
    }

}