package hr.unizg.fer.is.boore.boore.Country;

import hr.unizg.fer.is.boore.boore.City.City;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "drzava")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_drzava", nullable = false)
    private Integer id;

    @Column(name = "naziv", nullable = false)
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<City> cities;
}