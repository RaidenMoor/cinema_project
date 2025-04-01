package com.example.cinema.repository;

import org.springframework.stereotype.Repository;
import com.example.cinema.model.FilmCreator;

import java.util.List;

@Repository
public interface FilmCreatorRepository extends GenericRepository<FilmCreator> {

    List<FilmCreator> getAllByFullNameContainsIgnoreCase(String fullName);
}
