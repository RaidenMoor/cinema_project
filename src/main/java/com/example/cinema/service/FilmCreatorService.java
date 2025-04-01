package com.example.cinema.service;

import org.springframework.stereotype.Service;
import com.example.cinema.dto.FilmCreatorDTO;
import com.example.cinema.mapper.FilmCreatorMapper;
import com.example.cinema.model.FilmCreator;
import com.example.cinema.repository.FilmCreatorRepository;

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
}
