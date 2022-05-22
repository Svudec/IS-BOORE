package hr.unizg.fer.is.boore.boore.Person;

import hr.unizg.fer.is.boore.boore.Book.Book;
import hr.unizg.fer.is.boore.boore.Book.BookDTO;
import hr.unizg.fer.is.boore.boore.Genre.service.GenreService;
import hr.unizg.fer.is.boore.boore.Person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class PersonController {

    private final PersonService personService;
    private final GenreService genreService;

    @GetMapping()
    public ResponseEntity<?> getUserProfile(){
        return ResponseEntity.ok(personService.getUserProfile());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<BookDTO>> getRecommendations(@RequestParam(required = false) Integer genreId){
        if (genreId != null){
            return ResponseEntity.ok(personService.getRecommendations(genreService.getById(genreId)));
        }
        else {
            return ResponseEntity.ok(personService.getRecommendations());
        }
    }
}
