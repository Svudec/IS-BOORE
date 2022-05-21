package hr.unizg.fer.is.boore.boore.Wishlist.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Wishlist.Wishlist;
import hr.unizg.fer.is.boore.boore.Wishlist.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private final WishlistRepository wishlistRepository;

    @Override
    @Transactional
    public List<Book> getAllBooksForUser(Person user, boolean wasRead) {
        return wishlistRepository.findAllByPersonAndWasRead(user, wasRead)
                .stream().map(Wishlist::getBook).collect(Collectors.toList());
    }
}
