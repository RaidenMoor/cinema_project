package com.example.cinema.mvc;

import com.example.cinema.config.DuplicateDataException;
import com.example.cinema.dto.*;
import com.example.cinema.model.Genres;
import com.example.cinema.repository.CountryRepository;
import com.example.cinema.repository.FilmCreatorRepository;
import com.example.cinema.repository.GenreRepository;
import com.example.cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.cinema.utils.KinopoiskApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {

    private FilmService filmService;
    private FilmCreatorService filmCreatorService;
    private HallService hallService;




    @GetMapping("")
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "4") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdWhen"));
        model.addAttribute("films", filmService.getAll(pageRequest));
        return "films/viewAllFilms";
    }

    @GetMapping("/get/{id}")
    public String viewFilm(@PathVariable Long id,  Model model) {
        FilmSessionDTO filmSessionForm = new FilmSessionDTO();
        filmSessionForm.setFilmId(id); // Устанавливаем ID фильма
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String minDate = today.format(formatter);
        model.addAttribute("minDate", minDate);
        model.addAttribute("filmSessionForm", filmSessionForm);
        model.addAttribute("minTime", "09:45");
        model.addAttribute("maxTime", "23:30");
        model.addAttribute("halls", hallService.getAll());
        model.addAttribute("film", filmService.getExtendedById(id));
        return "films/viewFilm";
    }

    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO filmDTO, @RequestParam("file") MultipartFile file) {
        filmDTO.setRatingKp(filmService.getRating(filmDTO, KinopoiskApi.RatingType.KP));
       try {
           if (file != null && file.getSize() > 0) {
               filmService.create(filmDTO, file);
           } else {
               filmService.create(filmDTO);
           }
           return "redirect:/films";
       }
       catch (DuplicateDataException e) {
           //  Пример: Получить имя поля, вызвавшего дубликат (если оно есть)

           String errorMessage = e.getMessage();

           //  Можно сформировать более информативный ответ API
           //  Map<String, String> error = new HashMap<>();
           //  error.put("message", errorMessage);
           //  if (duplicatedField != null) {
           //      error.put("field", duplicatedField);
           //  }
           //  return new ResponseEntity<>(error, HttpStatus.CONFLICT);

           return "errors/errorDub"; // Возвращаем сообщение об ошибке
       }
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id) {
        filmService.softDelete(id);
        return "redirect:/films";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        filmService.restore(id);
        return "redirect:/films";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        model.addAttribute("film", filmService.getById(id));
        model.addAttribute("filmCreators", filmService.getFilmCreators(id));
        return "films/updateFilm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmForm") FilmDTO filmDTO, @RequestParam("file") MultipartFile file) {
        try {
            Float ratingKp = filmService.getRating(filmDTO, KinopoiskApi.RatingType.KP);
            if (ratingKp != null) {
                filmDTO.setRatingKp(ratingKp);
            }
            if (file != null && file.getSize() > 0) {
                filmService.update(filmDTO, file);
            } else {
                filmService.update(filmDTO);
            }
            return "redirect:/films/get/" + filmDTO.getId();
        } catch (DataIntegrityViolationException e) {
            return "errors/errorDub";
        }
    }

    @GetMapping("/{filmId}/addFilmCreator")
    public String addFilmCreator(@PathVariable Long filmId, Model model) {
        model.addAttribute("film", filmService.getById(filmId));
        model.addAttribute("filmCreators", filmCreatorService.getAll());
        return "films/addFilmCreator";
    }

    @PostMapping("/addFilmCreator")
    public String addFilmCreator(@ModelAttribute("filmCreatorAddForm") AddFilmCreatorDTO addFilmCreatorDTO) {
        filmService.addFilmCreator(addFilmCreatorDTO.getFilmId(), addFilmCreatorDTO.getFilmCreatorId());
        return "redirect:/films/update/" + addFilmCreatorDTO.getFilmId();
    }

    @GetMapping("/{filmId}/deleteFilmCreator/{filmCreatorId}")
    public String deleteFilmCreator(@PathVariable Long filmId, @PathVariable Long filmCreatorId) {
        filmService.deleteFilmCreator(filmId, filmCreatorId);
        return "redirect:/films/update/" + filmId;
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("filmSearchForm") FilmSearchDTO filmSearchDTO, Model model) {
        model.addAttribute("films", filmService.findFilms(filmSearchDTO));
        return "films/viewFoundFilms";
    }



    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @Autowired
    public void setFilmCreatorService(FilmCreatorService filmCreatorService) {
        this.filmCreatorService = filmCreatorService;
    }

    @Autowired
    public void setHallService(HallService hallService) {
        this.hallService = hallService;
    }
}
