package com.edu.bean;

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
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;
    private String questionChapter;
    private Integer questionDifficult;
    private String questionTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date questionTime;
    private String questionAuthor;
    private String questionAnswer;
    private String questionPara1;
    private String questionPara2;
    private String questionPara3;


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

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionPara1() {
        return questionPara1;
    }

    public void setQuestionPara1(String questionPara1) {
        this.questionPara1 = questionPara1;
    }

    public String getQuestionPara2() {
        return questionPara2;
    }

    public void setQuestionPara2(String questionPara2) {
        this.questionPara2 = questionPara2;
    }

    public String getQuestionPara3() {
        return questionPara3;
    }

    public void setQuestionPara3(String questionPara3) {
        this.questionPara3 = questionPara3;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionChapter='" + questionChapter + '\'' +
                ", questionDifficult=" + questionDifficult +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionTime=" + questionTime +
                ", questionAuthor='" + questionAuthor + '\'' +
                ", questionAnswer='" + questionAnswer + '\'' +
                ", questionPara1='" + questionPara1 + '\'' +
                ", questionPara2='" + questionPara2 + '\'' +
                ", questionPara3='" + questionPara3 + '\'' +
                '}';
    }
}
