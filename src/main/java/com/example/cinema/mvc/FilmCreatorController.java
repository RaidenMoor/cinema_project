package com.example.cinema.mvc;

import com.example.cinema.config.DuplicateDataException;
import com.example.cinema.dto.CountryDTO;
import com.example.cinema.dto.GenreDTO;
import com.example.cinema.model.Country;
import com.example.cinema.model.Genres;
import com.example.cinema.service.CountryService;
import com.example.cinema.service.GenreService;
import jakarta.validation.Valid;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.cinema.dto.FilmCreatorDTO;
import com.example.cinema.service.FilmCreatorService;

import java.util.List;

@Controller
@RequestMapping("/filmCreators")
public class FilmCreatorController {



    private CountryService countryService;
    private GenreService genreService;
    private final FilmCreatorService filmCreatorService;

    public FilmCreatorController(CountryService countryService, GenreService genreService, FilmCreatorService filmCreatorService) {

        this.countryService = countryService;
        this.genreService = genreService;
        this.filmCreatorService = filmCreatorService;
    }


    @GetMapping("")
    public String getAll(
            @RequestParam(name = "section", required = false) String section,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            Model model
    ) {
        List<?> data = null;
        Object newEntry = null;
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "fullName"));
        PageRequest countryPageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "countryName"));
        PageRequest genrePageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "genreName"));

        String activeSection = (section != null) ? section : "directors";

        switch (activeSection) {
            case "directors":
                Page<FilmCreatorDTO> filmCreatorDTOPage = filmCreatorService.getAll(pageRequest);
                data = filmCreatorDTOPage.getContent(); // Получаем список FilmCreatorDTO из Page
                newEntry = new FilmCreatorDTO();
                break;
            case "countries":
                Page<CountryDTO> countryDTOPage = countryService.getAll(countryPageRequest);
                data = countryDTOPage.getContent();
                newEntry = new CountryDTO();
                break;
            case "genres":
                Page<GenreDTO> genreDTOPage = genreService.getAll(genrePageRequest);
                data = genreDTOPage.getContent();
                newEntry = new GenreDTO();
                break;
            default:
                Page<FilmCreatorDTO> filmCreatorDTOPageDefault = filmCreatorService.getAll(pageRequest);
                data = filmCreatorDTOPageDefault.getContent();
                newEntry = new FilmCreatorDTO();
                activeSection = "directors"; // Default section
                break;
        }

        model.addAttribute("data", data);
        model.addAttribute("activeSection", activeSection);
        model.addAttribute("newEntry", newEntry);

        return "filmCreators/referenceBooks";
    }

    @PostMapping("/add")
    public String addEntry(
            @ModelAttribute("filmCreatorForm") @Valid FilmCreatorDTO filmCreatorDTO,  // for directors
            @ModelAttribute("countryForm") @Valid CountryDTO countryDTO,        // for countries
            @ModelAttribute("genreForm") @Valid GenreDTO genreDTO,            // for genres
            @RequestParam("section") String section,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            // Обработка ошибок валидации
            return "filmCreators/referenceBooks"; // Вернуться на страницу со справочниками
        }
        try{
        switch (section) {
            case "directors":
                filmCreatorService.create(filmCreatorDTO);
                break;
            case "countries":
                countryService.create(countryDTO);
                break;
            case "genres":
                genreService.create(genreDTO);
                break;
        }
        return "redirect:/filmCreators?section=" + section;}
        catch (DataIntegrityViolationException e){
            String errorMessage = e.getMessage();
            return "errors/errorDub";
        }
    }


    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id, @RequestParam("section") String section) {
        if ("directors".equals(section)) {
            filmCreatorService.softDelete(id);
        }
        else if ("countries".equals(section)) {
            countryService.softDelete(id);
        }
        else if ("genres".equals(section)) {
            genreService.softDelete(id);
        }
        return "redirect:/filmCreators?section=" + section;
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id, @RequestParam("section") String section) {
        if ("directors".equals(section)) {
            filmCreatorService.restore(id);
        }
        return "redirect:/filmCreators?section=" + section;
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id, @RequestParam("section") String section) {
        model.addAttribute("activeSection", section);
        if ("directors".equals(section)) {
            model.addAttribute("filmCreator", filmCreatorService.getById(id));
            return "filmCreators/updateFilmCreator";
        }
        else if ("countries".equals(section)) {
            model.addAttribute("country", countryService.getById(id));
            return "filmCreators/updateCountry";
        }
        else if ("genres".equals(section)) {
            model.addAttribute("genres", genreService.getById(id));
            return "filmCreators/updateGenre";
        }
        return "redirect:/filmCreators?section=" + section;
    }


    @PostMapping("/update")
    public String update(
            @RequestParam("section") String section,
            @ModelAttribute("filmCreatorForm") FilmCreatorDTO filmCreatorDTO,
            @ModelAttribute("countryForm") CountryDTO countryDTO,
            @ModelAttribute("genresForm") GenreDTO genreDTO,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                return "filmCreators/update" + section.substring(0, 1).toUpperCase() + section.substring(1);
            }

        switch (section) {
            case "directors":
                filmCreatorService.update(filmCreatorDTO);
                return "redirect:/filmCreators?section=" + section;
            case "countries":
                countryService.update(countryDTO);
                return "redirect:/filmCreators?section=" + section;
            case "genres":
                genreService.update(genreDTO);
                return "redirect:/filmCreators?section=" + section;
        }

        return "redirect:/filmCreators?section=" + section;
        }   catch (DataIntegrityViolationException e) {
        return "errors/errorDub";
        }
    }


    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
}