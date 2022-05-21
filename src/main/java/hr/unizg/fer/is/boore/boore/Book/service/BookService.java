package hr.unizg.fer.is.boore.boore.Book.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.User.User;

import java.util.List;

public interface BookService {

    List<Book> getAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(Person author);
    List<Book> getRecommendations(Genre genre, Person user);
    List<Book> getRecommendations(Person user);
}
