package com.dburyak.sandbox.sandboxspringnative.config;

import com.dburyak.sandbox.sandboxspringnative.repository.UnknownAuditorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.dburyak.sandbox.sandboxspringnative.repository")
@EnableJpaAuditing
@EnableTransactionManagement
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new UnknownAuditorProvider();
    }
}
