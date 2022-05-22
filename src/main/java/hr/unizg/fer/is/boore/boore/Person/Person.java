package hr.unizg.fer.is.boore.boore.Person;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.City.City;
import hr.unizg.fer.is.boore.boore.Review.Review;
import hr.unizg.fer.is.boore.boore.Wishlist.Wishlist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "osoba")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_osoba", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mjesto")
    @ToString.Exclude
    private City city;

    @Column(name = "je_korisnik", nullable = false)
    private Boolean isUser = false;

    @Column(name = "ime")
    private String firstName;

    @Column(name = "prezime")
    private String lastName;

    @Column(name = "godina_rodenja")
    private Integer birthYear;

    @Column(name = "korisnicko_ime")
    private String username;

    @Column(name = "lozinka")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "biografija")
    private String biography;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "knjiga_pisac",
            joinColumns = { @JoinColumn(name = "id_osoba")},
            inverseJoinColumns = {@JoinColumn(name = "id_knjiga")}
    )
    private Set<Book> booksWritten;

    @OneToMany(mappedBy = "person")
    private Set<Wishlist> booksInWishlist;

    @OneToMany(mappedBy = "person")
    private Set<Review> reviews;
}