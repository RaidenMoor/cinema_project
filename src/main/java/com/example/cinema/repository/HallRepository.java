package com.example.cinema.repository;

import com.example.cinema.dto.SeatsMapDTO;
import com.example.cinema.model.Hall;
import com.example.cinema.model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HallRepository extends GenericRepository<Hall> {

    @Query(nativeQuery = true,
            value = """
            select *
                from seats s
                where cast(s.row as varchar(2)) like coalesce(:row, '%')
                and cast(s.place as varchar(2)) like coalesce(:place,'%')
        """)
    Page<Seat> getAllByRowAndPlace(@Param(value = "row") String row, @Param(value = "place") String place, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
            select s.id as seatId, s.row, s.place, o.id as orderId, s.is_deleted as deleted
                from orders o
                join orders_seats os on o.id = os.order_id and o.is_deleted = false and o.film_session_id = :filmSessionId
                right join seats s on s.id = os.seat_id
        """)
    List<SeatsMapDTO> getSeatsMap(@Param("filmSessionId") Long filmSessionId);
}
