package com.example.cinema.repository;

import com.example.cinema.model.Genres;

import java.util.List;

public interface GenreRepository extends GenericRepository<Genres> {
    List<Genres> getAllByGenreNameContainsIgnoreCase(String genreName);
    List<Genres> findByIsDeletedFalse();
}
