package hr.unizg.fer.is.boore.boore.Book;

import hr.unizg.fer.is.boore.boore.Bookstore.BookstoreDTO;
import hr.unizg.fer.is.boore.boore.Person.dto.AuthorDTOBasic;
import hr.unizg.fer.is.boore.boore.Review.ReviewDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookDTO extends BookDTOBasic{
    private List<AuthorDTOBasic> authors;
    private List<ReviewDTO> reviews;
    private List<BookstoreDTO> soldInBookstores;
}
