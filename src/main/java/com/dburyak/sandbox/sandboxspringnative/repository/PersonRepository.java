package com.dburyak.sandbox.sandboxspringnative.repository;

import com.dburyak.sandbox.sandboxspringnative.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID>, CustomPersonRepository {
}
