package hr.unizg.fer.is.boore.boore.Person.dto;

import hr.unizg.fer.is.boore.boore.Book.BookDTO;
import hr.unizg.fer.is.boore.boore.Book.BookDTOBasic;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDTOFull extends AuthorDTOBasic{
    private List<BookDTOBasic> books;
}
