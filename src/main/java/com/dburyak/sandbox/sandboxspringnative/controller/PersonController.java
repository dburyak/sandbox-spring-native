package com.dburyak.sandbox.sandboxspringnative.controller;

import com.dburyak.sandbox.sandboxspringnative.domain.Person;
import com.dburyak.sandbox.sandboxspringnative.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople(
            @RequestParam(required = false, defaultValue = "0") int pageNum,
            @RequestParam(required = false, defaultValue = "100") int pageSize) {
        var people = personService.findAllPeople(Pageable.ofSize(pageSize).withPage(pageNum));
        return ResponseEntity.ok(people.getContent());
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable UUID personId) {
        var person = personService.findPersonById(personId);
        return person.map(ResponseEntity::ok)
                .orElseGet(this::notFound);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        var savedPerson = personService.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    @PostMapping("/{personId}/reputation/up")
    public ResponseEntity<Person> incrementReputation(@PathVariable UUID personId) {
        var wasIncremented = personService.incrementReputation(personId);
        if (!wasIncremented) {
            return notFound();
        }
        return personService.findPersonById(personId)
                .map(ResponseEntity::ok)
                .orElseGet(this::notFound);
    }

    @PostMapping("/{personId}/reputation/down")
    public ResponseEntity<Person> decrementReputation(@PathVariable UUID personId) {
        var wasDecremented = personService.decrementReputation(personId);
        if (!wasDecremented) {
            return notFound();
        }
        return personService.findPersonById(personId)
                .map(ResponseEntity::ok)
                .orElseGet(this::notFound);
    }

    private <T> ResponseEntity<T> notFound() {
        return ResponseEntity.notFound().build();
    }
}
