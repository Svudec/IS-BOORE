package hr.unizg.fer.is.boore.boore.Auth.dto;

import lombok.Data;

@Data
public class RegistrationDTO {
    private String firstName;
    private String lastname;
    private Integer birthYear;
    private String username;
    private String password;
    private String email;
    private String about;
    private String biography;
    private Integer cityId;
}
