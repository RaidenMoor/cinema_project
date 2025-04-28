package com.example.cinema.mvc;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/about")
public class AboutController {

    @GetMapping("")
    public String getAll(
            Model model) {
        return "about/mainAbout";
    }

    @GetMapping("/authors")
    public String developers(
            Model model) {
        return "about/aboutDevelopers";
    }

    @GetMapping("/system")
    public String system(
            Model model) {
        return "about/aboutSystem";
    }
}
