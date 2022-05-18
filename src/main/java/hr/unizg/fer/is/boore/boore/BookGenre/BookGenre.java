package hr.unizg.fer.is.boore.boore.BookGenre;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "knjiga_zanr")
public class BookGenre {
    @EmbeddedId
    private BookGenreId id;

    @MapsId("idBook")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_knjiga", nullable = false)
    private Book book;

    @MapsId("idGenre")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_zanr", nullable = false)
    private Genre genre;

}