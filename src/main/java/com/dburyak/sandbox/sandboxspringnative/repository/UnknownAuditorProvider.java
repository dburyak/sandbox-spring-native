package com.dburyak.sandbox.sandboxspringnative.repository;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class UnknownAuditorProvider implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("UNKNOWN");
    }
}
