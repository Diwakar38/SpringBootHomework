package com.tutorial.SecurityApp.services;

import com.tutorial.SecurityApp.dtos.SignUpDto;
import com.tutorial.SecurityApp.dtos.UserDto;
import com.tutorial.SecurityApp.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity getUserById(Long userId);

    UserDto signup(SignUpDto signUpDto);
}
