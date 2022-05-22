package hr.unizg.fer.is.boore.boore.Wishlist.service;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.service.BookService;
import hr.unizg.fer.is.boore.boore.Person.Person;
import hr.unizg.fer.is.boore.boore.Wishlist.Wishlist;
import hr.unizg.fer.is.boore.boore.Wishlist.WishlistId;
import hr.unizg.fer.is.boore.boore.Wishlist.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private final WishlistRepository wishlistRepository;
    private final BookService bookService;

    @Override
    @Transactional
    public List<Book> getAllBooksForUser(Person user, boolean wasRead) {
        return wishlistRepository.findAllByPersonAndWasRead(user, wasRead)
                .stream().map(Wishlist::getBook).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addToWishList(Person user, int bookId, boolean wasRead) {
        WishlistId id = new WishlistId();
        id.setIdBook(bookId);
        id.setIdPerson(user.getId());

        Wishlist wishlist;

        if(wishlistRepository.existsById(id)){
            wishlist = wishlistRepository.getById(id);
        } else {
            Book book = bookService.getById(bookId);

            wishlist = new Wishlist();
            wishlist.setBook(book);
            wishlist.setPerson(user);
            wishlist.setId(id);
        }
        wishlist.setWasRead(wasRead);
        wishlistRepository.save(wishlist);
    }

    @Override
    @Transactional
    public void removeFromWishList(int userId, int bookId) {
        WishlistId id = new WishlistId();
        id.setIdPerson(userId);
        id.setIdBook(bookId);

        if(wishlistRepository.existsById(id)){
            wishlistRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Book is not on a wishlist!");
        }
    }
}
