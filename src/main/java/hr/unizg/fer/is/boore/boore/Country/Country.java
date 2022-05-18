package hr.unizg.fer.is.boore.boore.Country;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "drzava")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_drzava", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "naziv", nullable = false)
    private String name;

}