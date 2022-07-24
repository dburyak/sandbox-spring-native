package com.dburyak.sandbox.sandboxspringnative.repository.impl;

import com.dburyak.sandbox.sandboxspringnative.repository.CustomPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Timestamp;
import java.time.Clock;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class CustomPersonRepositoryImpl implements CustomPersonRepository {
    private final Clock systemClock;
    private final AuditorAware<String> auditorProvider;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int incrementReputation(UUID id) {
        var query = """
                update person
                  set reputation = reputation + 1,
                    updated_at = :updatedAt,
                    updated_by = :updatedBy
                  where id = :id
                """;
        return jdbcTemplate.update(query, Map.of(
                "id", id,
                "updatedBy", currentAuditor(),
                "updatedAt", Timestamp.from(systemClock.instant())
        ));
    }

    @Override
    public int decrementReputation(UUID id) {
        var query = """
                update person
                  set reputation = reputation - 1,
                    updated_at = :updatedAt,
                    updated_by = :updatedBy
                  where id = :id
                """;
        return jdbcTemplate.update(query, Map.of(
                "id", id,
                "updatedBy", currentAuditor(),
                "updatedAt", Timestamp.from(systemClock.instant())
        ));
    }

    private String currentAuditor() {
        return auditorProvider.getCurrentAuditor().orElse("UNKNOWN");
    }
}
