package com.example.cinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.cinema.dto.SeatsMapDTO;
import com.example.cinema.model.Seat;

import java.util.List;

@Repository
public interface SeatRepository extends GenericRepository<Seat> {

    List<Seat> findByHallIdAndRow(Long hallId, Byte row);
    @Modifying
    @Query("UPDATE Seat s SET s.row = s.row - 1 WHERE s.hall.id = :hallId AND s.row > :deletedRow")
    void shiftRowsDown(@Param("hallId") Long hallId, @Param("deletedRow") Byte deletedRow);

    List<Seat> findByHallIdAndRowAndIsDeletedFalse(Long hallId, Byte row);
    List<Seat> findByHallId(Long hallId);

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

    @Query(nativeQuery = true,
            value = """
            select s.id as seatId, s.row, s.place, o.id as orderId, s.is_deleted as deleted
                from orders o
                join orders_seats os on o.id = os.order_id and o.is_deleted = false and o.film_session_id = :filmSessionId
                right join seats s on s.id = os.seat_id
                WHERE s.hall_id = :hallId
        """)
    List<SeatsMapDTO> getSeatsMap(@Param("filmSessionId") Long filmSessionId, @Param("hallId") Long hallId);

    @Modifying
    @Query("UPDATE Seat s SET s.price = :price WHERE s.hall.id = :hallId AND s.isDeleted = false")
    void updatePricesByHallId(@Param("hallId") Long hallId, @Param("price") Double price);

    // Удаление всех мест в указанном ряду зала
    @Modifying
    @Query("DELETE FROM Seat s WHERE s.hall.id = :hallId AND s.row = :row")
    void deleteByHallIdAndRow(@Param("hallId") Long hallId, @Param("row") Byte row);

    // Получение количества мест в указанном ряду
    int countByHallIdAndRow(Long hallId, Byte row);


}
