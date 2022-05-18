package hr.unizg.fer.is.boore.boore.BookAuthor;

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
public class BookAuthorId implements Serializable {
    private static final long serialVersionUID = 1581595386434678344L;
    @Column(name = "id_knjiga", nullable = false)
    private Integer idBook;

    @Column(name = "id_osoba", nullable = false)
    private Integer idAuthor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookAuthorId entity = (BookAuthorId) o;
        return Objects.equals(this.idBook, entity.idBook) &&
                Objects.equals(this.idAuthor, entity.idAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, idAuthor);
    }

}