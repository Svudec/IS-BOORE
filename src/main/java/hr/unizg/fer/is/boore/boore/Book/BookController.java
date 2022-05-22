package hr.unizg.fer.is.boore.boore.Book;

import hr.unizg.fer.is.boore.boore.Book.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id){
        try {
            BookDTO res = mapper.map(bookService.getById(id), BookDTO.class);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
