package com.tutorial.SecurityApp.services.impl;

import com.tutorial.SecurityApp.dtos.SignUpDto;
import com.tutorial.SecurityApp.dtos.UserDto;
import com.tutorial.SecurityApp.entities.UserEntity;
import com.tutorial.SecurityApp.exceptions.ResourceNotFoundException;
import com.tutorial.SecurityApp.repositories.UserRepository;
import com.tutorial.SecurityApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new BadCredentialsException("User not found with username: " + username));
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public UserDto signup(SignUpDto signUpDto) {
        Optional<UserEntity> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()) throw new BadCredentialsException("Email is already registered!");

        UserEntity newUserEntity = modelMapper.map(signUpDto, UserEntity.class);
        newUserEntity.setPassword(passwordEncoder.encode(newUserEntity.getPassword()));

        return modelMapper.map(userRepository.save(newUserEntity), UserDto.class);
    }

    @Override
    public UserEntity save(UserEntity newUser) {
        return userRepository.save(newUser);
    }

}
