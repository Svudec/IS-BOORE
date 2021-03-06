package hr.unizg.fer.is.boore.boore.Review.service;

import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Review.Review;
import hr.unizg.fer.is.boore.boore.Review.ReviewDTO;

import java.util.List;

public interface ReviewService {
    boolean exists(int userId, int bookId);
    void createOrEdit(ReviewDTO dto);
    void delete(int userId, int bookId);
}
