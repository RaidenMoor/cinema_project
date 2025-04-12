package com.example.cinema.mvc;

import com.example.cinema.dto.HallDTO;
import com.example.cinema.model.enums.HallType;
import com.example.cinema.service.HallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/halls")
public class HallController  {

    private HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("")
    public String getAll(
        @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            Model model    ) {
        List<?> data = null;
        Object newEntry = null;

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<HallDTO> hallDTOPage = hallService.getAll(pageRequest);
        data = hallDTOPage.getContent();
        newEntry = new HallDTO();
        model.addAttribute("data", data);
        model.addAttribute("newEntry", newEntry);

        return "halls/allHalls";
    }

    @GetMapping("/constructor")
    public String constructorHall(Model model){
        return "halls/hallConstructor";
    }

    @GetMapping("/edit/{id}")
    public String editHall(@PathVariable("id") Long id, Model model) {
        model.addAttribute("hall", hallService.getById(id));
        return "halls/hallConstructor";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("hallDto", new HallDTO());
        model.addAttribute("hallType", HallType.values());
        return "halls/addHall";
    }

    @PostMapping("/add")
    public String handleAddForm(
            @Valid @ModelAttribute("hallDto") HallDTO hallDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("hallTypes", HallType.values()); // Важно добавить при ошибке!
            return "halls/addHall";
        }

        hallService.create(hallDto);
        return "redirect:/halls";
    }


}
