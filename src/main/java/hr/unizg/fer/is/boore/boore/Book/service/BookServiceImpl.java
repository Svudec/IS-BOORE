package hr.unizg.fer.is.boore.boore.Book.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.BookRepository;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.User.User;
import hr.unizg.fer.is.boore.boore.Wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final WishlistService wishlistService;

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
        List<Book> res = bookRepository.findAllByGenresContainsOrderByRatingDesc(genre);
        res.removeAll(wishlistService.getAllBooksForUser(user, true));

        return res;
    }

    @Override
    @Transactional
    public List<Book> getRecommendations(Person user) {
        List<Book> res = bookRepository.findAll(Sort.by("rating").descending());
        res.removeAll(wishlistService.getAllBooksForUser(user, true));

        return res;
    }
}
