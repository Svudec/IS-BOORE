package hr.unizg.fer.is.boore.boore.Review.service;

import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Review.Review;
import hr.unizg.fer.is.boore.boore.Review.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public List<Review> getAllForUser(Person user) {
        return reviewRepository.findAllByPerson(user);
    }
}
