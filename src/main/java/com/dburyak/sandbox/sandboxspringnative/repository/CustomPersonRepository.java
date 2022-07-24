package com.dburyak.sandbox.sandboxspringnative.repository;

import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;

public interface CustomPersonRepository {
    @Modifying
    int incrementReputation(UUID id);

    @Modifying
    int decrementReputation(UUID id);
}
