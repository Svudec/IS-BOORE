package hr.unizg.fer.is.boore.boore.Genre.service;

import hr.unizg.fer.is.boore.boore.Genre.Genre;
import hr.unizg.fer.is.boore.boore.Genre.GenreRepository;
import hr.unizg.fer.is.boore.boore.common.dto.lovDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public Genre getById(int id) {
        return genreRepository.getById(id);
    }

    @Override
    @Transactional
    public List<lovDTO> getAllGenresLov() {
        return genreRepository.findAll(Sort.by("name")).stream()
                .map(genre -> new lovDTO(genre.getId(), genre.getName())).collect(Collectors.toList());
    }
}
