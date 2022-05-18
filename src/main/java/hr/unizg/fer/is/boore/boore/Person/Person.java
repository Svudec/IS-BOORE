package hr.unizg.fer.is.boore.boore.Person;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.City.City;
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

    @Lob
    @Column(name = "ime")
    private String firstName;

    @Lob
    @Column(name = "prezime")
    private String lastName;

    @Column(name = "godina_rodenja")
    private Integer birthYear;

    @Lob
    @Column(name = "korisnicko_ime")
    private String username;

    @Lob
    @Column(name = "lozinka")
    private String password;

    @Lob
    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "biografija")
    private String biography;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "knjiga_pisac",
            joinColumns = { @JoinColumn(referencedColumnName = "id_osoba")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id_knjiga")}
    )
    private Set<Book> booksWritten;

}