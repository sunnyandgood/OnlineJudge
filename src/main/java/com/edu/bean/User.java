package com.edu.bean;

import com.baomidou.mybatisplus.enums.IdType;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    private Integer nuserNumber;
    private String userName;
    private String userPassword;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNuserNumber() {
        return nuserNumber;
    }

    public void setNuserNumber(Integer nuserNumber) {
        this.nuserNumber = nuserNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", nuserNumber=" + nuserNumber +
        ", userName=" + userName +
        ", userPassword=" + userPassword +
        "}";
    }
}
