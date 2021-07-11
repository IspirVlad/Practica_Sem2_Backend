package com.marketplace.dto;

public class LoginResponseDto {

    private long userId;
    private String token;
    private String msg;

    public LoginResponseDto(long userId, String token, String msg) {
        this.userId = userId;
        this.token = token;
        this.msg = msg;
    }

    public LoginResponseDto() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
