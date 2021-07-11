package com.marketplace.dto;

import com.marketplace.model.User;

import java.util.List;

public class UserResponseDto {
    private List<User> users;
    private long totalUsers;
    private int from;
    private int to;

    public UserResponseDto() {
    }

    public UserResponseDto(List<User> users, long totalUsers, int from, int to) {
        this.users = users;
        this.totalUsers = totalUsers;
        this.from = from;
        this.to = to;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
