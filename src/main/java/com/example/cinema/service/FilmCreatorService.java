package com.example.cinema.service;

import com.example.cinema.model.Film;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.cinema.dto.FilmCreatorDTO;
import com.example.cinema.mapper.FilmCreatorMapper;
import com.example.cinema.model.FilmCreator;
import com.example.cinema.repository.FilmCreatorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilmCreatorService extends GenericService<FilmCreator, FilmCreatorDTO> {

    public FilmCreatorService(FilmCreatorRepository filmCreatorRepository, FilmCreatorMapper filmCreatorMapper) {
        repository = filmCreatorRepository;
        mapper = filmCreatorMapper;
    }



    public List<FilmCreatorDTO> searchFilmCreators(final String fullName) {
        List<FilmCreator> filmCreators = ((FilmCreatorRepository) repository)
                .getAllByFullNameContainsIgnoreCase(fullName);
        return mapper.toDTOs(filmCreators);
    }

    @Override
    public void softDelete(Long id) {
        FilmCreator filmCreator = repository.findById(id).orElse(null);
        if (filmCreator!= null) {
            filmCreator.setDeleted(true);
            filmCreator.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            filmCreator.setDeletedWhen(LocalDateTime.now());
            repository.save(filmCreator);
        }
    }
}
