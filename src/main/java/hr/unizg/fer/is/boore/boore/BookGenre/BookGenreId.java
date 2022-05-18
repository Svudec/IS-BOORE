package hr.unizg.fer.is.boore.boore.BookGenre;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class BookGenreId implements Serializable {
    private static final long serialVersionUID = 4606681419725432684L;
    @Column(name = "id_knjiga", nullable = false)
    private Integer idBook;

    @Column(name = "id_zanr", nullable = false)
    private Integer idGenre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookGenreId entity = (BookGenreId) o;
        return Objects.equals(this.idBook, entity.idBook) &&
                Objects.equals(this.idGenre, entity.idGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, idGenre);
    }

}