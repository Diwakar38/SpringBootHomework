package com.tutorial.SecurityApp.controllers;

import com.tutorial.SecurityApp.dtos.LoginDto;
import com.tutorial.SecurityApp.dtos.SignUpDto;
import com.tutorial.SecurityApp.dtos.UserDto;
import com.tutorial.SecurityApp.services.impl.AuthService;
import com.tutorial.SecurityApp.services.impl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceImpl userService;

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signup(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody LoginDto loginDto,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
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
