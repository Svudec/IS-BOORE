package hr.unizg.fer.is.boore.boore.Book;

import hr.unizg.fer.is.boore.boore.Bookstore.Bookstore;
import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Language.Language;
import hr.unizg.fer.is.boore.boore.Person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "knjiga")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_knjiga", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jezik")
    private Language language;

    @Column(name = "naziv", nullable = false)
    private String title;

    @Column(name = "opis")
    private String description;

    @Column(name = "godina_izdanja")
    private Integer yearOfIssue;

    @Column(name = "ocjena")
    private Double rating;

    @ManyToMany(mappedBy = "booksWritten")
    private Set<Person> authors;

    @ManyToMany(mappedBy = "books")
    private Set<Genre> genres;

    @ManyToMany(mappedBy = "books")
    private Set<Bookstore> soldInBookstores;
}