package hr.unizg.fer.is.boore.boore.Person.service;

import hr.unizg.fer.is.boore.boore.Auth.dto.RegistrationDTO;
import hr.unizg.fer.is.boore.boore.Person.Person;

import java.util.Optional;

public interface PersonService {
    Person getByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Person createPerson(RegistrationDTO registrationDTO);
}
