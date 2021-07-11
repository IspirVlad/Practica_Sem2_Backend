package com.marketplace.controller;

import com.marketplace.dto.*;
import com.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//controller => service => repository

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @GetMapping("/publicEndpoint")
    public String getPublicEndpoint(){
        return "public";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/userAccess")
    public String getUserAccess(){
        return "user";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adminAccess")
    public String getAdminAccess(){
        return "admin";
    }


    @PostMapping("/register")
    public String addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);

    }


    @GetMapping("/test")
    public String getTest() {
        return "Ok";
    }

    @GetMapping("/add")
    public String addMethod(@RequestParam String value) {

        return value;
    }

}
