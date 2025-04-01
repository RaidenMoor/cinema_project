package com.example.cinema.mapper;

import org.springframework.stereotype.Component;
import com.example.cinema.dto.FilmExtendedDTO;
import com.example.cinema.model.Film;

@Component
public class FilmExtendedMapper extends GenericMapper<Film, FilmExtendedDTO> {

    public FilmExtendedMapper() {
        super(Film.class, FilmExtendedDTO.class);
    }
}
