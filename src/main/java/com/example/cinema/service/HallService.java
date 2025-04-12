package com.example.cinema.service;


import com.example.cinema.dto.HallDTO;
import com.example.cinema.mapper.HallMapper;
import com.example.cinema.mapper.OrderMapper;
import com.example.cinema.model.Hall;
import com.example.cinema.model.Seat;
import com.example.cinema.repository.HallRepository;
import com.example.cinema.repository.OrderRepository;
import com.example.cinema.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HallService extends GenericService<Hall, HallDTO>{

    private final SeatRepository seatRepository;

    public HallService(HallRepository hallRepository, HallMapper hallMapper,  SeatRepository seatRepository) {
        repository = hallRepository;
        mapper = hallMapper;
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional
    public HallDTO create(HallDTO hallDto) {
        HallDTO createdHall = super.create(hallDto);
        if (createdHall != null) {
            generateSeatsForHall(createdHall.getId(),
                    hallDto.getCountRow(),
                    hallDto.getCountPlace());
        }
        return createdHall;
    }

    private void generateSeatsForHall(Long hallId, short rows, short places) {

        Hall hall = repository.findById(hallId)
                .orElseThrow(() -> new EntityNotFoundException("Hall not found"));
        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        List<Seat> seats = new ArrayList<>();
        for (short row = 1; row <= rows; row++) {
            for (short place = 1; place <= places; place++) {
                Seat seat = new Seat();
                seat.setRow((byte) row);
                seat.setPlace((byte) place);
                seat.setHall(hall);
                seat.setCreatedBy(createdBy);
                seat.setCreatedWhen(now);
                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
    }

}
