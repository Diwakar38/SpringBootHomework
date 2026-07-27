package com.tutorial.SecurityApp.dtos;

import com.tutorial.SecurityApp.entities.enums.Permission;
import com.tutorial.SecurityApp.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
    private Set<Permission> permissions;
}
