package hr.unizg.fer.is.boore.boore.Wishlist.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Person.Person;

import java.util.List;

public interface WishlistService {
    List<Book> getAllBooksForUser(Person user, boolean wasRead);
}
