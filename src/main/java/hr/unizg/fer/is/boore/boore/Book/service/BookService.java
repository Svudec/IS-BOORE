package hr.unizg.fer.is.boore.boore.Book.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.BookDTO;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.User.User;
import hr.unizg.fer.is.boore.boore.Wishlist.Wishlist;

import java.util.List;

public interface BookService {
    Book getById(int id);
    List<Book> getAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(Person author);
    List<Book> getRecommendations(Genre genre, Person user);
    List<Book> getRecommendations(Person user);
    void calculateRatingForBook(Book book);
}
