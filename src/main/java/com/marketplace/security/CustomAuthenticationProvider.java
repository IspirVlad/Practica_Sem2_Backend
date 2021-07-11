package com.marketplace.security;

import com.marketplace.dto.AuthDto;
import com.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    private UserRepository userRepository;


    @Autowired
    public void setServices(UserRepository userRepository) { this.userRepository = userRepository;}

    @Override
    public Authentication authenticate(Authentication authentication){
        String email = authentication.getName().trim();
        String inputPassword = authentication.getCredentials().toString();


        Optional<AuthDto> optionalAuthDto = userRepository.getByEmailWithPasswordAndRole(email);
        //Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalAuthDto.isPresent()) {
            AuthDto authDto = optionalAuthDto.get();
//            User user = optionalUser.get();
            String dbPassword = authDto.getPassword();

//            Role role = user.getRole();

            if (BCrypt.checkpw(inputPassword, dbPassword)) {
                UserDetails userDetails = UserPrinciple.build(authDto);
                Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);
                return newAuth;
            } throw new BadCredentialsException("Wrong Password");
        }

        //Optional<AuthDto> optionalAuthDto = userRepository.getByEmailWithPassowrdAndRole(username);
        return null;
    }
}
