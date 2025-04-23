package com.example.cinema.service;


import com.example.cinema.dto.HallDTO;
import com.example.cinema.mapper.HallMapper;
import com.example.cinema.model.Hall;
import com.example.cinema.model.Seat;
import com.example.cinema.model.enums.Coeffintient;
import com.example.cinema.repository.HallRepository;
import com.example.cinema.repository.SeatRepository;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public HallDTO update(HallDTO hallDTO){

        HallDTO updatedHall = super.update(hallDTO);

        if (updatedHall != null) {

                List<Seat> seats = seatRepository.findByHallId(updatedHall.getId());
                for(Seat seat : seats){
                    seatRepository.delete(seat);
                }
            generateSeatsForHall(updatedHall.getId(),
                    updatedHall.getCountRow(),
                    updatedHall.getCountPlace());

        }
        return updatedHall;
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
                seat.setPrice(Coeffintient.STANDART.getText());
            }
        }

        seatRepository.saveAll(seats);
    }

    @Transactional
    public void deleteRow(Long hallId, Byte rowNumber) {
        // 1. Получаем зал
        Hall hall = repository.findById(hallId)
                .orElseThrow(() -> new IllegalArgumentException("Зал с ID " + hallId + " не найден"));

        // 2. Получаем список мест для удаления
        List<Seat> seatsToDelete = seatRepository.findByHallIdAndRowAndIsDeletedFalse(hallId, rowNumber);

        // 3. Применяем softDelete к каждому месту
        for (Seat seat : seatsToDelete) {
            seatRepository.delete(seat);

        }
        if (rowNumber < hall.getCountRow()) {
            seatRepository.shiftRowsDown(hallId, rowNumber);
        }

        // 4. Уменьшаем количество рядов в зале
        Short currentRows = hall.getCountRow();
        if (currentRows > 0) {
            hall.setCountRow((short) (currentRows - 1));
        } else {
            throw new IllegalStateException("Невозможно удалить ряд, так как количество рядов уже равно 0");
        }

        // 5. Сохраняем изменения в зале
       repository.save(hall);
    }


    public void addRow(Long hallId){
        Hall hall = repository.findById(hallId)
                .orElse(null);

        // Увеличиваем количество рядов
        hall.setCountRow((short) (hall.getCountRow() + 1));
        repository.save(hall);

        // Создаем места в новом ряду
        for (byte place = 1; place <= hall.getCountPlace(); place++) {
            Seat seat = new Seat();
            seat.setRow( hall.getCountRow().byteValue());
            seat.setPlace(place);
            seat.setHall(hall);
            seat.setPrice(Coeffintient.STANDART.getText());
            seatRepository.save(seat);
        }
    }


    public void editRow(Long hallId, Byte rowNumber,double coefficient){
        // Получаем коэффициент как double


        // Находим все места в указанном ряду
        List<Seat> seats = seatRepository.findByHallIdAndRow(hallId, rowNumber);

        // Обновляем цену для каждого места
        seats.forEach(seat -> {

            seat.setPrice(coefficient);
        });

        seatRepository.saveAll(seats);

    }
}
