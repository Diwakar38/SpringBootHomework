package com.tutorial.SecurityApp.configurations;

import com.tutorial.SecurityApp.auth.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AuditorAwareConfig {
    @Bean
    AuditorAware<String> getAuditorAwareImpl() {
        return new AuditorAwareImpl();
    }
}
