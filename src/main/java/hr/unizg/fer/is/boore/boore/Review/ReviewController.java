package hr.unizg.fer.is.boore.boore.Review;

import hr.unizg.fer.is.boore.boore.Person.service.PersonService;
import hr.unizg.fer.is.boore.boore.Review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<?> createOrEditReview(@RequestBody ReviewDTO dto){
        try {
            reviewService.createOrEdit(dto);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@RequestParam Integer bookId){
        try {
            reviewService.delete(personService.getLoggedInUser().getId(), bookId);
            return ResponseEntity.ok("Success");
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
