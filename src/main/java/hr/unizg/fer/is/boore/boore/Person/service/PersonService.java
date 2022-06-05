package hr.unizg.fer.is.boore.boore.Person.service;

import hr.unizg.fer.is.boore.boore.Auth.dto.RegistrationDTO;
import hr.unizg.fer.is.boore.boore.Book.BookDTO;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Person.dto.UserProfileDTO;

import java.util.List;

public interface PersonService {
    Person getById(int id);

    Person getById(String id);

    Person getLoggedInUser();

    Person getByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Person createUser(RegistrationDTO registrationDTO);

    List<BookDTO> getRecommendations();

    List<BookDTO> getRecommendations(Genre genre);

    UserProfileDTO getUserProfile();

    UserProfileDTO getUserProfile(String userId);

    UserProfileDTO getUserProfile(Person person);

    void resetDeclinedReviewsCount(int personId);

    void increaseDeclinedReviewsCount(int personId);

    boolean canLeaveReviews(int personId);
}
