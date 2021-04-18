package com.colin.demo01.model.entity;

public class UserBean {
    private String mUserName;
    private String mPassword;

    public UserBean(String userName, String password) {
        mUserName = userName;
        mPassword = password;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getToken() {
        return mUserName + mPassword;
    }
}
