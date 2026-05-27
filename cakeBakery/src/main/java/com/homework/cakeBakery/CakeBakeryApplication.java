package com.homework.cakeBakery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CakeBakeryApplication implements CommandLineRunner {
    CakeBaker cakeBaker;

    public CakeBakeryApplication(CakeBaker cakeBaker) {
        this.cakeBaker = cakeBaker;
    }

    public static void main(String[] args) {
        SpringApplication.run(CakeBakeryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(cakeBaker.bakeCake());
    }
}
