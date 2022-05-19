package hr.unizg.fer.is.boore.boore.Bookstore;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.City.City;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "knjizara")
public class Bookstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_knjizara", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mjesto")
    private City city;

    @Column(name = "naziv", nullable = false)
    private String name;

    @Column(name = "adresa")
    private String adress;

    @Column(name = "url")
    private String url;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "knjiga_knjizara",
            joinColumns = {@JoinColumn(name = "id_knjizara")},
            inverseJoinColumns = {@JoinColumn(name = "id_knjiga")}
    )
    private Set<Book> books;
}