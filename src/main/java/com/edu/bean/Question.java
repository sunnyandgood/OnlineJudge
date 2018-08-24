package com.edu.bean;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sunny
 * @since 2018-08-24
 */
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;
    private String questionChapter;
    private Integer questionDifficult;
    private String questionTitle;
    private Date questionTime;
    private String questionAuthor;


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionChapter() {
        return questionChapter;
    }

    public void setQuestionChapter(String questionChapter) {
        this.questionChapter = questionChapter;
    }

    public Integer getQuestionDifficult() {
        return questionDifficult;
    }

    public void setQuestionDifficult(Integer questionDifficult) {
        this.questionDifficult = questionDifficult;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public String getQuestionAuthor() {
        return questionAuthor;
    }

    public void setQuestionAuthor(String questionAuthor) {
        this.questionAuthor = questionAuthor;
    }

    @Override
    public String toString() {
        return "Question{" +
        "questionId=" + questionId +
        ", questionChapter=" + questionChapter +
        ", questionDifficult=" + questionDifficult +
        ", questionTitle=" + questionTitle +
        ", questionTime=" + questionTime +
        ", questionAuthor=" + questionAuthor +
        "}";
    }
}
