package com.tutorial.SecurityApp.controllers;

import com.tutorial.SecurityApp.advices.ApiResponse;
import com.tutorial.SecurityApp.dtos.LoginDto;
import com.tutorial.SecurityApp.dtos.LoginResponseDto;
import com.tutorial.SecurityApp.dtos.SignUpDto;
import com.tutorial.SecurityApp.dtos.UserDto;
import com.tutorial.SecurityApp.services.impl.AuthService;
import com.tutorial.SecurityApp.services.impl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserServiceImpl userService;

    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signup(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> Login(@RequestBody LoginDto loginDto,
                                                              HttpServletRequest request,
                                                              HttpServletResponse response){
        LoginResponseDto loginResponse = authService.login(loginDto);

        Cookie refreshCookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure("production".equals(deployEnv));
        response.addCookie(refreshCookie);
        log.info("Tokens: {}", refreshCookie.toString());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the cookies"));

        LoginResponseDto loginResponse = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponse);

    }

    @GetMapping("/logout")
    public ResponseEntity<String> Logout(HttpServletRequest request,
                                         HttpServletResponse response){
        String token = authService.logout(request);

        Cookie cookie = new Cookie("token", null);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
