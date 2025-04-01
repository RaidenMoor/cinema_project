package com.example.cinema.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cinema.dto.FilmCreatorDTO;
import com.example.cinema.model.FilmCreator;
import com.example.cinema.service.FilmCreatorService;

@RestController
@RequestMapping("/filmCreators")
@Tag(name = "Создатели фильмов", description = "Контроллер для работы с создателями фильмов")
public class FilmCreatorRestController extends GenericRestController<FilmCreator, FilmCreatorDTO> {

    public FilmCreatorRestController(FilmCreatorService filmCreatorService) {
        service = filmCreatorService;
    }

}
