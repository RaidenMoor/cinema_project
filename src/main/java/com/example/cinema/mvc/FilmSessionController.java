package com.example.cinema.mvc;

import com.example.cinema.dto.HallDTO;
import com.example.cinema.model.Hall;
import com.example.cinema.service.HallService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.cinema.dto.FilmSessionDTO;
import com.example.cinema.service.FilmService;
import com.example.cinema.service.FilmSessionService;

import java.util.List;

@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {

    private FilmSessionService filmSessionService;
    private FilmService filmService;
    private HallService hallService;



    @PostMapping("/add")
    public String create(@ModelAttribute("filmSessionForm") FilmSessionDTO filmSessionDTO) {
        filmSessionService.create(filmSessionDTO);
        return "redirect:/films/get/" + filmSessionDTO.getFilmId();
    }

    @GetMapping("/delete/{id}")
    public String softDelete(@PathVariable Long id, HttpServletRequest request) {
        filmSessionService.softDelete(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id, HttpServletRequest request) {
        filmSessionService.restore(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        FilmSessionDTO filmSessionDTO = filmSessionService.getById(id);

        List<HallDTO> halls = hallService.getAll();

        model.addAttribute("filmSession", filmSessionDTO);
        model.addAttribute("filmSessionForm", filmSessionDTO);
        model.addAttribute("halls", halls);;
        model.addAttribute("filmTitle", filmService.getById(filmSessionDTO.getFilmId()).getTitle());
        return "filmSessions/updateFilmSession";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmSessionForm") FilmSessionDTO filmSessionDTO) {
        filmSessionService.update(filmSessionDTO);
        return "redirect:/films/get/" + filmSessionDTO.getFilmId();
    }



    @Autowired
    public void setFilmSessionService(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }
    @Autowired
    public void setHallService(HallService hallService) {
        this.hallService = hallService;
    }


    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
}
