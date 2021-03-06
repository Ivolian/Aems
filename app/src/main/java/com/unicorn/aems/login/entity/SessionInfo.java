package com.unicorn.aems.login.entity;

import java.io.Serializable;

public class SessionInfo implements Serializable{

    private UserInfo currentUser;
    private String jsessionid;
    private boolean success;

    public UserInfo getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserInfo currentUser) {
        this.currentUser = currentUser;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
