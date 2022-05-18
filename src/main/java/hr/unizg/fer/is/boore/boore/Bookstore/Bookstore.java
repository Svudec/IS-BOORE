package hr.unizg.fer.is.boore.boore.Bookstore;

import hr.unizg.fer.is.boore.boore.City.City;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Lob
    @Column(name = "naziv", nullable = false)
    private String name;

    @Lob
    @Column(name = "adresa")
    private String adress;

    @Lob
    @Column(name = "url")
    private String url;

}