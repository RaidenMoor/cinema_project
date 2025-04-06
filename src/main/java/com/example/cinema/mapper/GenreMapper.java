package com.example.cinema.mapper;


import com.example.cinema.dto.FilmCreatorDTO;
import com.example.cinema.dto.GenreDTO;
import com.example.cinema.model.FilmCreator;
import com.example.cinema.model.Genres;
import groovyjarjarpicocli.CommandLine;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper extends GenericMapper<Genres, GenreDTO>{
    public GenreMapper() {
        super(Genres.class, GenreDTO.class);
    }
}
