package com.example.cinema.mapper;

import org.springframework.stereotype.Component;
import com.example.cinema.dto.FilmDTO;
import com.example.cinema.model.Film;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDTO> {

    public FilmMapper() {
        super(Film.class, FilmDTO.class);
    }

}
