package hr.unizg.fer.is.boore.boore.Auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private String firstName;
    private String lastname;
    private Integer birthYear;
    private String username;
    private String password;
    private String email;
    private String biography;
    private Integer cityId;
}
