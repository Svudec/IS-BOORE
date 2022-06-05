package hr.unizg.fer.is.boore.boore.Review.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.service.BookService;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Person.service.PersonService;
import hr.unizg.fer.is.boore.boore.Review.Review;
import hr.unizg.fer.is.boore.boore.Review.ReviewDTO;
import hr.unizg.fer.is.boore.boore.Review.ReviewId;
import hr.unizg.fer.is.boore.boore.Review.ReviewRepository;
import hr.unizg.fer.is.boore.boore.Wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
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
    private final WishlistService wishlistService;

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
            id.setIdPerson(Integer.parseInt(dto.getPerson()));
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Book with that id not found");
        }
        Person user = personService.getById(dto.getPerson());

        Review review;
        if(reviewRepository.existsById(id)){
            review = reviewRepository.getById(id);
        } else {
            Book book = bookService.getById(id.getIdBook());

            review = new Review();
            review.setId(id);
            review.setPerson(user);
            review.setBook(book);
            book.getReviews().add(review);
        }

        review.setRating(dto.getRating());
        review.setText(dto.getText());
        reviewRepository.save(review);
        wishlistService.addToWishList(user, id.getIdBook(), true);
        bookService.calculateRatingForBook(review.getBook());
    }

    @Override
    public void createOrEdit(DelegateExecution execution) {
        String personId = (String) execution.getVariable("userId");
        String bookId = (String) execution.getVariable("bookId");
        String text = (String) execution.getVariable("text");
        Integer rating = (Integer) execution.getVariable("rating");

        ReviewDTO dto = new ReviewDTO();
        dto.setBook(bookId);
        dto.setPerson(personId);
        dto.setRating(rating);
        dto.setText(text);

        createOrEdit(dto);
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
