package com.example.cinema.service;



import com.example.cinema.dto.GenreDTO;
import com.example.cinema.mapper.GenreMapper;
import com.example.cinema.model.Genres;
import com.example.cinema.repository.GenreRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GenreService extends GenericService<Genres, GenreDTO>{
    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        repository = genreRepository;
        mapper = genreMapper;
    }



    public List<GenreDTO> searchCountries(final String genreName) {
        List<Genres> genres = ((GenreRepository) repository)
                .getAllByGenreNameContainsIgnoreCase(genreName);
        return mapper.toDTOs(genres);
    }

    @Override
    public void softDelete(Long id) {
        Genres genres = repository.findById(id).orElse(null);
        if (genres != null) {
            genres.setDeleted(true);
            genres.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            genres.setDeletedWhen(LocalDateTime.now());
            repository.save(genres);
        }
    }
}