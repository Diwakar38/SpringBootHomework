package com.homework.cakeBakery;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CakeBakeryApplication implements ApplicationRunner, CommandLineRunner {
    CakeBaker cakeBaker;

    public CakeBakeryApplication(CakeBaker cakeBaker) {
        this.cakeBaker = cakeBaker;
    }

    static void main(String[] args) {
        SpringApplication.run(CakeBakeryApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(args.toString());
        System.out.println(cakeBaker.bakeCake());
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(args.toString());
        System.out.println(cakeBaker.bakeCake());
    }
}
