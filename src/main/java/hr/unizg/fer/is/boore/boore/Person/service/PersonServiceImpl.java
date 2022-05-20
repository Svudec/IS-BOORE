package hr.unizg.fer.is.boore.boore.Person.service;

import hr.unizg.fer.is.boore.boore.Auth.dto.RegistrationDTO;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Person.PersonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Person getByUsername(String username) {
        return personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    @Transactional
    public Boolean existsByUsername(String username) {
        return personRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public Boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Person createUser(RegistrationDTO registrationDTO) {
        Person newPerson = mapper.map(registrationDTO, Person.class);
        newPerson.setId(null);
        newPerson.setIsUser(true);
        newPerson.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        return personRepository.save(newPerson);
    }
}
