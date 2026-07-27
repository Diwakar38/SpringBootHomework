package com.tutorial.SecurityApp.services.impl;

import com.tutorial.SecurityApp.dtos.LoginDto;
import com.tutorial.SecurityApp.dtos.LoginResponseDto;
import com.tutorial.SecurityApp.entities.SessionEntity;
import com.tutorial.SecurityApp.entities.UserEntity;
import com.tutorial.SecurityApp.repositories.SessionRepository;
import com.tutorial.SecurityApp.services.SessionService;
import com.tutorial.SecurityApp.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final UserService userService;
    private final SessionRepository sessionRepository;
    private final SessionService sessionService;

    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        sessionService.generateNewSession(userEntity,refreshToken);

        return new LoginResponseDto(userEntity.getId(),accessToken,refreshToken);
    }

    public String logout(HttpServletRequest request) throws ExpiredJwtException {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() == false) {
            return "User is not logged in!";
        }
        Cookie[] cookie = request.getCookies();
        log.trace("Cookie: {}", cookie[0].getValue());
        return "Successfully Logged out";
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        UserEntity user = userService.getUserById(userId);
        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(),accessToken,refreshToken);
    }
}
