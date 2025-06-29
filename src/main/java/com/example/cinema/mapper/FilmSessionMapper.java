package com.example.cinema.mapper;

import com.example.cinema.model.Hall;
import com.example.cinema.repository.HallRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.cinema.dto.FilmSessionDTO;
import com.example.cinema.model.Film;
import com.example.cinema.model.FilmSession;
import com.example.cinema.repository.FilmRepository;

@Component
public class FilmSessionMapper extends GenericMapper<FilmSession, FilmSessionDTO>
        implements ConverterForSpecificFields<FilmSession, FilmSessionDTO> {

    private FilmRepository filmRepository;
    private HallRepository hallRepository;



    public FilmSessionMapper() {
        super(FilmSession.class, FilmSessionDTO.class);
    }


    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(FilmSession.class, FilmSessionDTO.class)
                .addMappings(m -> {
                    m.skip(FilmSessionDTO::setFilmId);
                    m.skip(FilmSessionDTO::setHallId);
                })
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmSessionDTO.class, FilmSession.class)
                .addMappings(m -> {
                    m.skip(FilmSession::setFilm);
                    m.skip(FilmSession::setHall);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(FilmSessionDTO source, FilmSession destination) {
        Long filmId = source.getFilmId();
        if (filmId != null) {
            destination.setFilm(filmRepository.findById(filmId).orElse(null));
        } else destination.setFilm(null);

        Long hallId = source.getHallId();
        if (hallId != null) {
            destination.setHall(hallRepository.findById(hallId).orElse(null));
        } else {
            destination.setHall(null);
        }
    }

    @Override
    public void mapSpecificFields(FilmSession source, FilmSessionDTO destination) {
        Long filmId = null;
        Film film = source.getFilm();
        if (film != null) {
            filmId = film.getId();
        }
        destination.setFilmId(filmId);

        Long hallId = null;
        Hall hall = source.getHall();
        if (hall != null) {
            hallId = hall.getId();
        }
        destination.setHallId(hallId);
    }


    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    @Autowired
    public void setHallRepository(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

}
