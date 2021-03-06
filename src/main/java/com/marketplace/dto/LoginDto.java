package com.marketplace.dto;

public class LoginDto {

    private String password;
    private String email;

    public LoginDto() {
    }

    public LoginDto(String email,String password) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
