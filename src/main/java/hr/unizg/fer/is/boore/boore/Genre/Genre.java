package hr.unizg.fer.is.boore.boore.Genre;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "zanr")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zanr", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "naziv", nullable = false)
    private String name;

}