package com.dburyak.sandbox.sandboxspringnative.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int reputation;
}
