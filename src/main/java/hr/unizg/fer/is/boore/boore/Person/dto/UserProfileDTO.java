package hr.unizg.fer.is.boore.boore.Person.dto;

import hr.unizg.fer.is.boore.boore.Book.BookDTO;
import hr.unizg.fer.is.boore.boore.City.City;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileDTO {
    private Integer id;
    private String city;
    private String firstName;
    private String lastName;
    private Integer birthYear;
    private String username;
    private String email;
    private String biography;
    private List<BookDTO> wantsToRead;
    private List<BookDTO> hasRead;
}
