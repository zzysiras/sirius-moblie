package com.cesaba.siriusmobliemain.dto;

import java.io.Serializable;

public class Token implements Serializable {

    private static final long  serialVersionUID = 6314027741784310221L;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    private String token;
    private Long loginTime;  //登录时间戳

    public Token(String token, Long loginTime){
        super();
        this.token = token;
        this.loginTime = loginTime;
    }


}
