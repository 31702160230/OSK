package com.example.wechat.Beans;

import java.util.List;

public class DataBeans {
    private String status;
    private List<UserBeans> userBeans;
    private List<ChatBeans> chatBeans;
    private LoginBeans loginBeans;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserBeans> getUserBeans() {
        return userBeans;
    }

    public void setUserBeans(List<UserBeans> userBeans) {
        this.userBeans = userBeans;
    }

    public List<ChatBeans> getChatBeans() {
        return chatBeans;
    }

    public void setChatBeans(List<ChatBeans> chatBeans) {
        this.chatBeans = chatBeans;
    }

    public LoginBeans getLoginBeans() {
        return loginBeans;
    }

    public void setLoginBeans(LoginBeans loginBeans) {
        this.loginBeans = loginBeans;
    }
}
