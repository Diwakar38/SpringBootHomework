package com.tutorial.SecurityApp;

import com.tutorial.SecurityApp.entities.UserEntity;
import com.tutorial.SecurityApp.services.impl.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTests {

    @Autowired
    JwtServiceImpl jwtService;

    @Test
    void test() {
        UserEntity userEntity = new UserEntity(4L, "diwakar@gamil.com", "1235", "diwakar");
        String token = jwtService.generateToken(userEntity);
        System.out.println(token);
        Long id = jwtService.getUserIdFromToken(token);
        System.out.println("Id: " + id);
    }
}
