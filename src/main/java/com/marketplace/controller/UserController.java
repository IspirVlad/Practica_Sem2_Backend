package com.marketplace.controller;

import com.marketplace.dto.PagingDto;
import com.marketplace.dto.UserInfoDto;
import com.marketplace.dto.UserInfoDto2;
import com.marketplace.dto.UserResponseDto;
import com.marketplace.model.User;
import com.marketplace.repository.UserRepository;
import com.marketplace.security.UserPrinciple;
import com.marketplace.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public UserInfoDto2 getUserDetails(@PathVariable Long id) throws Exception {
        return userService.getUserInfoById(id);
    }

    @GetMapping("/getUserInfo")
    public UserInfoDto2 getUserInfo(OAuth2Authentication auth2Authentication)throws Exception{
        return userService.getUserInfo(auth2Authentication);
    }

    @PostMapping("/get-users")
    public UserResponseDto getPaginatedUsers(@RequestBody PagingDto pagingDto){
        return userService.getPaginatedUsers(pagingDto);

    }

}
