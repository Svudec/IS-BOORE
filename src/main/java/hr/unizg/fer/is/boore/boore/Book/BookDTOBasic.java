package hr.unizg.fer.is.boore.boore.Book;

import hr.unizg.fer.is.boore.boore.Bookstore.BookstoreDTO;
import hr.unizg.fer.is.boore.boore.Person.dto.AuthorDTOBasic;
import hr.unizg.fer.is.boore.boore.Review.ReviewDTO;
import lombok.Data;

import java.util.List;

@Data
public class BookDTOBasic {
    private Integer id;
    private String language;
    private String title;
    private String description;
    private Integer yearOfIssue;
    private Double rating;
}
