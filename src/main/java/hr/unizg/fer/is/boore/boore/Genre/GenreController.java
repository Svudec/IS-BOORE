package hr.unizg.fer.is.boore.boore.Genre;

import hr.unizg.fer.is.boore.boore.Genre.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/lov")
    public ResponseEntity<?> getLov(){
        return ResponseEntity.ok(genreService.getAllGenresLov());
    }
}
