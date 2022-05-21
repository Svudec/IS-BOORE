package hr.unizg.fer.is.boore.boore.Review;

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
public class ReviewId implements Serializable {
    private static final long serialVersionUID = 3108320092719244650L;
    @Column(name = "id_knjiga", nullable = false)
    private Integer idBook;

    @Column(name = "id_osoba", nullable = false)
    private Integer idPerson;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReviewId entity = (ReviewId) o;
        return Objects.equals(this.idBook, entity.idBook) &&
                Objects.equals(this.idPerson, entity.idPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, idPerson);
    }
}