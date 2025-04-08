package com.example.cinema.mapper;

import com.example.cinema.dto.HallDTO;
import com.example.cinema.model.Hall;
import org.springframework.stereotype.Component;

@Component
public class HallMapper  extends GenericMapper<Hall, HallDTO>{
    public HallMapper() {
        super(Hall.class, HallDTO.class);
    }
}
