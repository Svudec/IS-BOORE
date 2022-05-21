package hr.unizg.fer.is.boore.boore.Wishlist;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {
    List<Wishlist> findAllByPersonAndWasRead(Person user, Boolean wasRead);
}
