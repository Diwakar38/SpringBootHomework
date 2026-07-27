package com.tutorial.SecurityApp.services;

import com.tutorial.SecurityApp.entities.UserEntity;

public interface SessionService {
    public void generateNewSession(UserEntity user, String refreshToken);
    public void validateSession(String refreshToken);
}
