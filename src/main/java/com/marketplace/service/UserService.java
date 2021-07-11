package com.marketplace.service;

import com.marketplace.dto.*;
import com.marketplace.model.Media;
import com.marketplace.model.Role;
import com.marketplace.model.User;
import com.marketplace.repository.RoleRepository;
import com.marketplace.repository.UserRepository;
import com.marketplace.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public String addUser(UserDto userDto) {
        Optional<Role> role = roleRepository.findByRole("ROLE_DEFAULT");
        User user = new User(userDto);
        String inputPass = user.getPassword();
        String hashPass = BCrypt.hashpw(inputPass,BCrypt.gensalt());
        user.setPassword(hashPass);
        if(role.isPresent())
            user.setRole(role.get());
        userRepository.save(user);
        return "Ok";
    }

    public ResponseEntity<Object> addProfileImage(Media image, Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setProfileImage(image);

            userRepository.save(user);

            return new ResponseEntity<>("saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    public UserInfoDto getProfile(OAuth2Authentication auth2Authentication){
        UserPrinciple userPrinciple = (UserPrinciple) auth2Authentication.getPrincipal();
        String email = userPrinciple.getUsername();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setEmail(user.get().getEmail());
            infoDto.setName(user.get().getName());

            return infoDto;
        }else {
            return new UserInfoDto();
        }
    }

    public UserResponseDto getPaginatedUsers(PagingDto pagingDto){
        int page = pagingDto.getPage();
        int size = pagingDto.getSize();

        int firstResults = page * size;
        int maxResults = firstResults + size;

        List<User> users = userRepository.getPaginatedUsers(firstResults,maxResults);
        long totalUsers = userRepository.getTotalUsers();

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setUsers(users);
        responseDto.setFrom(firstResults);
        responseDto.setTo(maxResults);
        responseDto.setTotalUsers(totalUsers);

        return responseDto;
    }

    public UserInfoDto2 getUserInfo(OAuth2Authentication auth2Authentication)throws Exception{
        UserPrinciple userPrinciple = (UserPrinciple) auth2Authentication.getPrincipal();
        String email = userPrinciple.getUsername();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            UserInfoDto2 infoDto = new UserInfoDto2();
            infoDto.setEmail(user.get().getEmail());
            infoDto.setName(user.get().getName());
            infoDto.setId(user.get().getId());

            return infoDto;
        } throw new UsernameNotFoundException("User not found");
    }

    public UserInfoDto2 getUserInfoById(Long id)throws Exception{
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            UserInfoDto2 infoDto = new UserInfoDto2();
            infoDto.setEmail(user.get().getEmail());
            infoDto.setName(user.get().getName());
            infoDto.setId(user.get().getId());
            infoDto.setProfileImage(user.get().getProfileImage());
            return infoDto;
        }throw new UsernameNotFoundException("User not found");
    }

}
