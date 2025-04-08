package com.example.cinema.service;


import com.example.cinema.dto.HallDTO;
import com.example.cinema.mapper.HallMapper;
import com.example.cinema.mapper.OrderMapper;
import com.example.cinema.model.Hall;
import com.example.cinema.repository.HallRepository;
import com.example.cinema.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class HallService extends GenericService<Hall, HallDTO>{

    public HallService(HallRepository hallRepository, HallMapper hallMapper) {
        repository = hallRepository;
        mapper = hallMapper;
    }
}
