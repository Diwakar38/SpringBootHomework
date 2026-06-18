package com.tutorial.SecurityApp.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional getCurrentAuditor() {
        // Get security context
        // Get authentication
        // Get the principle
        // Get the username
        return Optional.of("Diwakar Arya");
    }
}
