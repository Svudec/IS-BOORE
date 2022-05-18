package hr.unizg.fer.is.boore.boore.City;

import hr.unizg.fer.is.boore.boore.Country.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Lob
    @Column(name = "naziv", nullable = false)
    private String name;

}