package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marketplace.dto.UserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String email;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="profileImage_id", referencedColumnName="id")
    private Media profileImage;

    @ManyToOne(fetch=FetchType.EAGER)
    private Role role;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //@OneToMany(mappedBy="product", cascade= CascadeType.ALL)
    //private List<Product> productList = new ArrayList<>();

    //@OneToMany(mappedBy="review", cascade= CascadeType.ALL)
    //private List<Review> reviewList = new ArrayList<>();

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
    }

    public Media getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Media profileImage) {
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        this.name=name;
    }

}