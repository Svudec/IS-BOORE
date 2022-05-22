package hr.unizg.fer.is.boore.boore.City;

import hr.unizg.fer.is.boore.boore.Bookstore.Bookstore;
import hr.unizg.fer.is.boore.boore.Country.Country;
import hr.unizg.fer.is.boore.boore.Person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "mjesto")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mjesto", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_drzava")
    private Country country;

    @Column(name = "naziv", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city")
    private Set<Bookstore> bookstores;

    @OneToMany(mappedBy = "city")
    private Set<Person> users;

    @Override
    public String toString() {
        return name + ", " + country.getName();
    }
}