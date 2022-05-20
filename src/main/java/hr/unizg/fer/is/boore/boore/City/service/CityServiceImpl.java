package hr.unizg.fer.is.boore.boore.City.service;

import hr.unizg.fer.is.boore.boore.City.City;
import hr.unizg.fer.is.boore.boore.City.CityRepository;
import hr.unizg.fer.is.boore.boore.common.dto.lovDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    public List<lovDTO> getAllCitiesLov() {
        return cityRepository.findAll(Sort.by("name")).stream()
                .map(city -> new lovDTO(city.getId(), city.getName(), city.getCountry().getName())).collect(Collectors.toList());
    }
}
