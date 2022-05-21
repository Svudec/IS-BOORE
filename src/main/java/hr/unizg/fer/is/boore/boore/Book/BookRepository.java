package hr.unizg.fer.is.boore.boore.Book;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByTitleLike(String searchString);

    List<Book> findAllByAuthorsContains(Person author);

    List<Book> findAllByGenresContainsOrderByRatingDesc(Genre genre);
}
