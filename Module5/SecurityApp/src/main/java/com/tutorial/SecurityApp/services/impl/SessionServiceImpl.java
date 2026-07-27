package com.tutorial.SecurityApp.services.impl;

import com.tutorial.SecurityApp.entities.SessionEntity;
import com.tutorial.SecurityApp.entities.UserEntity;
import com.tutorial.SecurityApp.exceptions.ResourceNotFoundException;
import com.tutorial.SecurityApp.repositories.SessionRepository;
import com.tutorial.SecurityApp.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    @Override
    public void generateNewSession(UserEntity user, String refreshToken) {
        List<SessionEntity> userSessions = sessionRepository.findByUser(user);

        if(userSessions.size() == SESSION_LIMIT) {
            userSessions.sort(Comparator.comparing(SessionEntity::getLastUsedAt));

            SessionEntity leastRecentlyUsed = userSessions.getFirst();

            sessionRepository.delete(leastRecentlyUsed);
        }

        SessionEntity newSession = SessionEntity.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {

        SessionEntity session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session not found: " + refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
