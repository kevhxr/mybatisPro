package com.xr.entity;

import java.io.Serializable;

public class UserBean implements Serializable, Comparable {

    private String userName;
    private int userId;
    private int userAge;
    private String userAlias;


    public UserBean() {
    }

    public UserBean(String userName, int userAge, String userAlias) {
        this.userName = userName;
        this.userAge = userAge;
        this.userAlias = userAlias;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String alias) {
        this.userAlias = alias;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", userAge=" + userAge +
                ", userAlias='" + userAlias + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}