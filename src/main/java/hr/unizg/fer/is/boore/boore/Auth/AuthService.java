package hr.unizg.fer.is.boore.boore.Auth;

import hr.unizg.fer.is.boore.boore.Auth.config.JwtTokenProvider;
import hr.unizg.fer.is.boore.boore.Auth.dto.JwtAuthenticationResponse;
import hr.unizg.fer.is.boore.boore.Auth.dto.RegistrationDTO;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Person.PersonRepository;
import hr.unizg.fer.is.boore.boore.Person.service.PersonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final PersonService personService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationResponse loginUser(String username, String password){
        Person person = personService.getByUsername(username);

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new JwtAuthenticationResponse(tokenProvider.generateToken(auth),
                person.getFirstName() + " " + person.getLastName(),
                person.getIsModerator() ? "MODERATOR" : "USER",
                person.getId());
    }

    public JwtAuthenticationResponse registerUser(RegistrationDTO dto){
        if (personService.existsByUsername(dto.getUsername())){
            throw new IllegalArgumentException("Username already exists!");
        }
        if (personService.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Email already exists!");
        }

        personService.createUser(dto);
        return loginUser(dto.getUsername(), dto.getPassword());
    }
}
