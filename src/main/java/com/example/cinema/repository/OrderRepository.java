package com.example.cinema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.cinema.dto.OrderInfoDTO;
import com.example.cinema.model.Order;

import java.time.LocalDate;

@Repository
public interface OrderRepository extends GenericRepository<Order>{

    @Query("SELECT new com.example.cinema.dto.OrderInfoDTO(o.id, o.createdWhen, f.title, o.cost, o.purchase) "
            + "FROM Film f JOIN f.filmSessions fs JOIN fs.orders o WHERE o.user.id = ?1 AND o.isDeleted = false ORDER BY o.createdWhen DESC")
    Page<OrderInfoDTO> getAllInfoByUserId(Long userId, PageRequest pageRequest);

    @Query(nativeQuery = true,
            value = "select sum(cost) from orders " +
                    "where created_when between :startDate and :endDate " +
                    "and is_deleted = false and purchase = true")
    Long getTotalCost(@Param(value = "startDate") LocalDate startDate, @Param(value = "endDate") LocalDate endDate);

    @Query(nativeQuery = true,
            value = """
                select count(*)
                    from orders o
                    join orders_seats os on o.id = os.order_id
                                        and o.created_when between :startDate and :endDate
                                        and o.is_deleted = false
                                        and o.purchase = true
            """)
    Long getTotalTickets(@Param(value = "startDate") LocalDate startDate, @Param(value = "endDate") LocalDate endDate);
}
