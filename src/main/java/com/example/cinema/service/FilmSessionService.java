package com.example.cinema.service;

import com.example.cinema.model.Film;
import com.example.cinema.model.Hall;
import com.example.cinema.repository.FilmRepository;
import com.example.cinema.repository.HallRepository;
import com.example.cinema.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.cinema.dto.FilmSessionDTO;
import com.example.cinema.mapper.FilmSessionMapper;
import com.example.cinema.model.FilmSession;
import com.example.cinema.repository.FilmSessionRepository;

import java.time.LocalDateTime;

@Service
public class FilmSessionService extends GenericService<FilmSession, FilmSessionDTO> {

    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;

    public FilmSessionService(FilmSessionRepository filmSessionRepository, FilmSessionMapper filmSessionMapper,
                              FilmRepository filmRepository, HallRepository hallRepository,
                              SeatRepository seatRepository) {
        repository = filmSessionRepository;
        mapper = filmSessionMapper;
        this.filmRepository = filmRepository;
        this.seatRepository=seatRepository;
        this.hallRepository=hallRepository;
    }


    @Override
    @Transactional
    public FilmSessionDTO create(FilmSessionDTO filmSessionDTO) {

        Film film = filmRepository.findById(filmSessionDTO.getFilmId())
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id: " + filmSessionDTO.getFilmId()));

        Hall hall = hallRepository.findById(filmSessionDTO.getHallId())
                .orElseThrow(() -> new EntityNotFoundException("Hall not found with id: " + filmSessionDTO.getHallId()));

        FilmSession session = new FilmSession();
        session.setFilm(film);
        session.setHall(hall);
        session.setStartDate(filmSessionDTO.getStartDate());
        session.setStartTime(filmSessionDTO.getStartTime());
        session.setPrice(filmSessionDTO.getPrice());

        session.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        session.setCreatedWhen(LocalDateTime.now());
        session = repository.save(session);


        // Обновляем цены мест в зале
        updateSeatPrices(hall.getId(), filmSessionDTO.getPrice());

        return mapper.toDTO(repository.save(session));
    }

    private void updateSeatPrices(Long hallId, Double price) {
        seatRepository.updatePricesByHallId(hallId, price);

    }




}
