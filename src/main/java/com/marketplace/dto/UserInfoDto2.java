package com.marketplace.dto;

import com.marketplace.model.Media;

public class UserInfoDto2 {
    private String email;
    private String name;
    private Media profileImage;
    private long id;

    public UserInfoDto2() {
    }

    public UserInfoDto2(String email, String name) {
        this.email = email;
        this.name = name;

    }

    public Media getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Media profileImage) {
        this.profileImage = profileImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
