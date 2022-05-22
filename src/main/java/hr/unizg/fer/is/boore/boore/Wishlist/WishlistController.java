package hr.unizg.fer.is.boore.boore.Wishlist;

import hr.unizg.fer.is.boore.boore.Person.service.PersonService;
import hr.unizg.fer.is.boore.boore.Wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<?> addToWishlist(@RequestParam Integer bookId, @RequestParam Boolean wasRead){
        wishlistService.addToWishList(personService.getLoggedInUser(), bookId, wasRead);
        return ResponseEntity.ok("Successfully added to wishlist");
    }

    @DeleteMapping
    public ResponseEntity<?> removeFromWishlist(@RequestParam Integer bookId){
        try {
            wishlistService.removeFromWishList(personService.getLoggedInUser().getId(), bookId);
            return ResponseEntity.ok("Successfully removed from wishlist");
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
