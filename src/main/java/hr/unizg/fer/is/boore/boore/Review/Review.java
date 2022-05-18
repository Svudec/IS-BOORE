package hr.unizg.fer.is.boore.boore.Review;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "recenzija")
public class Review {
    @EmbeddedId
    private ReviewId id;

    @MapsId("idBook")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_knjiga", nullable = false)
    private Book book;

    @MapsId("idPerson")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_osoba", nullable = false)
    private Person person;

    @Lob
    @Column(name = "tekst")
    private String text;

    @Column(name = "ocjena")
    private Integer rating;

}