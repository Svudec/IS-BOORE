package hr.unizg.fer.is.boore.boore.Genre.service;

import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.common.dto.lovDTO;

import java.util.List;

public interface GenreService {
    Genre getById(int id);

    List<lovDTO> getAllGenresLov();
}
