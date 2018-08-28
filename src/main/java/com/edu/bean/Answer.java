package com.edu.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "answer_id", type = IdType.AUTO)
    private Integer answerId;
    private Integer userId;
    private Integer questionId;
    private String answerResult;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date answerTime;

    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Question question;


    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", userId=" + userId +
                ", questionId=" + questionId +
                ", answerResult='" + answerResult + '\'' +
                ", answerTime=" + answerTime +
                ", user=" + user +
                ", question=" + question +
                '}';
    }
}
