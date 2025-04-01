package com.example.cinema.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cinema.dto.SeatDTO;
import com.example.cinema.model.Seat;
import com.example.cinema.service.SeatService;

@RestController
@RequestMapping("/seats")
@Tag(name = "Места", description = "Контроллер для работы с местами в кинозале")
public class SeatRestController extends GenericRestController<Seat, SeatDTO> {

    public SeatRestController(SeatService seatService) {
        service = seatService;
    }
}
