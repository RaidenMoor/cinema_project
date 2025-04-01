package com.example.cinema.mapper;

import org.springframework.stereotype.Component;
import com.example.cinema.dto.SeatDTO;
import com.example.cinema.model.Seat;

@Component
public class SeatMapper extends GenericMapper<Seat, SeatDTO> {

    public SeatMapper() {
        super(Seat.class, SeatDTO.class);
    }

}
