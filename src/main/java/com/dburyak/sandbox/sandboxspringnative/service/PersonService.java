package com.dburyak.sandbox.sandboxspringnative.service;

import com.dburyak.sandbox.sandboxspringnative.domain.Person;
import com.dburyak.sandbox.sandboxspringnative.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Page<Person> findAllPeople(Pageable pageReq) {
        return personRepository.findAll(pageReq);
    }

    @Transactional(readOnly = true)
    public Optional<Person> findPersonById(UUID personId) {
        return personRepository.findById(personId);
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public boolean incrementReputation(UUID personId) {
        var numUpdated = personRepository.incrementReputation(personId);
        return numUpdated > 0;
    }

    @Transactional
    public boolean decrementReputation(UUID personId) {
        var numUpdated = personRepository.decrementReputation(personId);
        return numUpdated > 0;
    }
}
