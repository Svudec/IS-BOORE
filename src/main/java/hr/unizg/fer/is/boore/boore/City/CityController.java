package hr.unizg.fer.is.boore.boore.City;

import hr.unizg.fer.is.boore.boore.City.service.CityService;
import hr.unizg.fer.is.boore.boore.common.dto.lovDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
