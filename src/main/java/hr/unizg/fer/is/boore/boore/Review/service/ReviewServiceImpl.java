package hr.unizg.fer.is.boore.boore.Review.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.service.BookService;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Person.service.PersonService;
import hr.unizg.fer.is.boore.boore.Review.Review;
import hr.unizg.fer.is.boore.boore.Review.ReviewDTO;
import hr.unizg.fer.is.boore.boore.Review.ReviewId;
import hr.unizg.fer.is.boore.boore.Review.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final PersonService personService;
    private final BookService bookService;

    @Override
    public boolean exists(int userId, int bookId) {
        ReviewId id = new ReviewId();
        id.setIdBook(bookId);
        id.setIdPerson(userId);
        return reviewRepository.existsById(id);
    }

    @Override
    @Transactional
    public void createOrEdit(ReviewDTO dto) {
        ReviewId id = new ReviewId();
        try {
            id.setIdBook(Integer.parseInt(dto.getBook()));

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Book with that id not found");
        }
        try {
            id.setIdPerson(Integer.parseInt(dto.getPerson()));

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Person with that id not found");
        }

        Person loggedInUser = personService.getLoggedInUser();
        if(!Objects.equals(id.getIdPerson(), loggedInUser.getId())){
            throw new IllegalArgumentException("User Id is not logged in user!");
        }

        Review review;
        if(reviewRepository.existsById(id)){
            review = reviewRepository.getById(id);
        } else {
            Book book = bookService.getById(id.getIdBook());

            review = new Review();
            review.setId(id);
            review.setPerson(loggedInUser);
            review.setBook(book);
            book.getReviews().add(review);
        }

        review.setRating(dto.getRating());
        review.setText(dto.getText());
        reviewRepository.save(review);
        bookService.calculateRatingForBook(review.getBook());
    }

    @Override
    @Transactional
    public void delete(int userId, int bookId) {
        ReviewId id = new ReviewId();
        id.setIdBook(bookId);
        id.setIdPerson(userId);

        if(reviewRepository.existsById(id)){
            Review review = reviewRepository.getById(id);
            Book book = review.getBook();
            book.getReviews().remove(review);
            reviewRepository.deleteById(id);
            bookService.calculateRatingForBook(book);
        } else {
            throw new EntityNotFoundException("Review not found!");
        }
    }
}
