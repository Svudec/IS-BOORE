package hr.unizg.fer.is.boore.boore.Wishlist;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "procitao_ili_zeli")
public class Wishlist {
    @EmbeddedId
    private WishlistId id;

    @MapsId("idBook")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_knjiga", nullable = false)
    private Book book;

    @MapsId("idPerson")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_osoba", nullable = false)
    private Person person;

    @Column(name = "procitao")
    private Boolean wasRead;
}
