package hr.unizg.fer.is.boore.boore.Auth;

import hr.unizg.fer.is.boore.boore.Auth.dto.LoginDTO;
import hr.unizg.fer.is.boore.boore.Auth.dto.RegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

//@CrossOrigin(origins = "https://localhost:3000")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationDTO) throws ParseException {
        return ResponseEntity.ok(authService.registerUser(registrationDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws ParseException {
        return ResponseEntity.ok(authService.loginUser(loginDTO.getUsername(), loginDTO.getPassword()));
    }
}
