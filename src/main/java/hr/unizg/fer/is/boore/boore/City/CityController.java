package hr.unizg.fer.is.boore.boore.City;

import hr.unizg.fer.is.boore.boore.City.service.CityService;
import hr.unizg.fer.is.boore.boore.common.dto.lovDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/lov")
    public ResponseEntity<List<lovDTO>> getLov(){
        return ResponseEntity.ok(cityService.getAllCitiesLov());
    }
}
