package hr.unizg.fer.is.boore.boore.BookAuthor;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "knjiga_pisac")
public class BookAuthor {
    @EmbeddedId
    private BookAuthorId id;

    @MapsId("idBook")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_knjiga", nullable = false)
    private Book book;

    @MapsId("idAuthor")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_osoba", nullable = false)
    private Person author;

}