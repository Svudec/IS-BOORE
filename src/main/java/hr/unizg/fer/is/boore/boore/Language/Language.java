package hr.unizg.fer.is.boore.boore.Language;

import hr.unizg.fer.is.boore.boore.Book.Book;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jezik")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jezik", nullable = false)
    private Integer id;

    @Column(name = "naziv", nullable = false)
    private String name;

    @Column(name = "oznaka", length = 2)
    private String label;

    @OneToMany(mappedBy = "language")
    private Set<Book> books;

    @Override
    public String toString() {
        return name;
    }
}