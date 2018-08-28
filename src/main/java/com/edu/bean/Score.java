package com.edu.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "score_id", type = IdType.AUTO)
    private Integer scoreId;
    private Integer userId;
    private Integer questionId;
    private Double scoreDegree;
    private Integer answerId;

    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Question question;


    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
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

    public Double getScoreDegree() {
        return scoreDegree;
    }

    public void setScoreDegree(Double scoreDegree) {
        this.scoreDegree = scoreDegree;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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
        return "Score{" +
                "scoreId=" + scoreId +
                ", userId=" + userId +
                ", questionId=" + questionId +
                ", scoreDegree=" + scoreDegree +
                ", answerId=" + answerId +
                ", user=" + user +
                ", question=" + question +
                '}';
    }
}
