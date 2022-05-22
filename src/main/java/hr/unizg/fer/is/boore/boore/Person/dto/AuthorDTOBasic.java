package hr.unizg.fer.is.boore.boore.Person.dto;

import lombok.Data;

@Data
public class AuthorDTOBasic {
    private Integer id;
    private String firstName;
    private Integer birthYear;
    private String biography;
}
