package hr.unizg.fer.is.boore.boore.Book;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByTitleLike(String searchString);

    List<Book> findAllByAuthorsContains(Person author);

    @Query(nativeQuery = true, value = "SELECT k.* FROM knjiga k " +
            "JOIN knjiga_zanr kz on k.id_knjiga = kz.id_knjiga and id_zanr = ?2 " +
            "LEFT JOIN procitao_ili_zeli piz on k.id_knjiga = piz.id_knjiga and piz.id_osoba = ?1 " +
            "WHERE piz.procitao = FALSE or piz.procitao IS NULL")
    List<Book> getRecommendations(int userId, int genreId);

    @Query(nativeQuery = true, value = "SELECT k.* FROM knjiga k " +
            "LEFT JOIN procitao_ili_zeli piz on k.id_knjiga = piz.id_knjiga and piz.id_osoba = ?1 " +
            "WHERE piz.procitao = FALSE or piz.procitao IS NULL")
    List<Book> getRecommendations(int userId);
}
