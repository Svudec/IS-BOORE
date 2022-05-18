package hr.unizg.fer.is.boore.boore.BookToBookstore;

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
public class BookstoreBookId implements Serializable {
    private static final long serialVersionUID = 2040806281650172138L;
    @Column(name = "id_knjiga", nullable = false)
    private Integer idBook;

    @Column(name = "id_knjizara", nullable = false)
    private Integer idBookstore;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookstoreBookId entity = (BookstoreBookId) o;
        return Objects.equals(this.idBookstore, entity.idBookstore) &&
                Objects.equals(this.idBook, entity.idBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBookstore, idBook);
    }

}