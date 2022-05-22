package hr.unizg.fer.is.boore.boore.Wishlist;

import hr.unizg.fer.is.boore.boore.Person.service.PersonService;
import hr.unizg.fer.is.boore.boore.Wishlist.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final PersonService personService;

    @GetMapping("/add")
    public ResponseEntity<?> addToWishlist(@RequestParam Integer bookId, @RequestParam Boolean wasRead){
        wishlistService.addToWishList(personService.getLoggedInUser(), bookId, wasRead);
        return ResponseEntity.ok("Successfully added to wishlist");
    }
}
