package hr.unizg.fer.is.boore.boore.Genre;

import hr.unizg.fer.is.boore.boore.Book.Book;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "zanr")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zanr", nullable = false)
    private Integer id;

    @Column(name = "naziv", nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "knjiga_zanr",
        joinColumns = {@JoinColumn(name = "id_zanr")},
        inverseJoinColumns = {@JoinColumn(name = "id_knjiga")}
    )
    private Set<Book> books;

}