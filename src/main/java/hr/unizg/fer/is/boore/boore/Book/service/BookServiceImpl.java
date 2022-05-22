package hr.unizg.fer.is.boore.boore.Book.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.BookDTO;
import hr.unizg.fer.is.boore.boore.Book.BookRepository;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Review.Review;
import hr.unizg.fer.is.boore.boore.User.User;
import hr.unizg.fer.is.boore.boore.Wishlist.Wishlist;
import hr.unizg.fer.is.boore.boore.Wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    @Override
    public Book getById(int id) {
        return bookRepository.getById(id);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookRepository.findAll(Sort.by("title"));
    }

    @Override
    @Transactional
    public List<Book> findByTitle(String title) {
        return bookRepository.findAllByTitleLike("%" + title + "%");
    }

    @Override
    @Transactional
    public List<Book> findByAuthor(Person author) {
        return bookRepository.findAllByAuthorsContains(author);
    }

    @Override
    @Transactional
    public List<Book> getRecommendations(Genre genre, Person user) {
        return bookRepository.getRecommendations(user.getId(), genre.getId());
    }

    @Override
    @Transactional
    public List<Book> getRecommendations(Person user) {
        return bookRepository.getRecommendations(user.getId());
    }

    @Override
    @Transactional
    public void calculateRatingForBook(Book book) {
        double newRating = book.getReviews().stream().mapToDouble(Review::getRating)
                .average().orElse(0.0);
        book.setRating(newRating);
        bookRepository.save(book);
    }
}
