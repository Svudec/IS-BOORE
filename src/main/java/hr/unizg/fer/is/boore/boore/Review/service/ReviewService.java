package hr.unizg.fer.is.boore.boore.Review.service;

import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Review.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllForUser(Person user);
}
