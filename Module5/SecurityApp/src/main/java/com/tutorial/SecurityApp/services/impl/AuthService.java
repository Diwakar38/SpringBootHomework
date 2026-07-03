package com.tutorial.SecurityApp.services.impl;

import com.tutorial.SecurityApp.dtos.LoginDto;
import com.tutorial.SecurityApp.entities.SessionEntity;
import com.tutorial.SecurityApp.entities.UserEntity;
import com.tutorial.SecurityApp.repositories.SessionRepository;
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

    private final SessionRepository sessionRepository;

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = jwtService.generateToken(userEntity);
        SessionEntity sessionEntity = new SessionEntity(userEntity, token);

        sessionRepository.save(sessionEntity);

        return token;
    }

    public String logout(HttpServletRequest request) throws ExpiredJwtException {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() == false) {
            return "User is not logged in!";
        }
        Cookie[] cookie = request.getCookies();
        log.trace("Cookie: {}", cookie[0].getValue());
        Optional<SessionEntity> currentLoggedInUser = sessionRepository.findById(cookie[0].getValue());
        sessionRepository.deleteById(currentLoggedInUser.get().getToken());
        return "Successfully Logged out";
    }
}
