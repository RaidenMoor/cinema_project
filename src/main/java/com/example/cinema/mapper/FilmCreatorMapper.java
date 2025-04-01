package com.example.cinema.mapper;

import org.springframework.stereotype.Component;
import com.example.cinema.dto.FilmCreatorDTO;
import com.example.cinema.model.FilmCreator;

@Component
public class FilmCreatorMapper extends GenericMapper<FilmCreator, FilmCreatorDTO> {

    public FilmCreatorMapper() {
        super(FilmCreator.class, FilmCreatorDTO.class);
    }

}
