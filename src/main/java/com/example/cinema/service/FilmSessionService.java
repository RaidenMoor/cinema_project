package com.example.cinema.service;

import org.springframework.stereotype.Service;
import com.example.cinema.dto.FilmSessionDTO;
import com.example.cinema.mapper.FilmSessionMapper;
import com.example.cinema.model.FilmSession;
import com.example.cinema.repository.FilmSessionRepository;

@Service
public class FilmSessionService extends GenericService<FilmSession, FilmSessionDTO> {

    public FilmSessionService(FilmSessionRepository filmSessionRepository, FilmSessionMapper filmSessionMapper) {
        repository = filmSessionRepository;
        mapper = filmSessionMapper;
    }

}
