package hr.unizg.fer.is.boore.boore.BookToBookstore;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Bookstore.Bookstore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "knjiga_knjizara")
public class BookstoreBook {
    @EmbeddedId
    private BookstoreBookId id;

    @MapsId("idBook")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_knjiga", nullable = false)
    private Book book;

    @MapsId("idBookstore")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_knjizara", nullable = false)
    private Bookstore bookstore;

}