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
    public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationDTO){
        try {
            return ResponseEntity.ok().body(authService.registerUser(registrationDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try {
            return ResponseEntity.ok().body(authService.loginUser(loginDTO.getUsername(), loginDTO.getPassword()));
        } catch (IllegalArgumentException e) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }
}
