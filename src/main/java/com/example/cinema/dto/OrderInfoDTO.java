package com.example.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {

    private Long orderId;

    protected LocalDateTime orderCreatedWhen;

    private String filmTitle;
    private LocalDate startDate;
    private LocalTime startTime;


    private double cost;

    private boolean purchase;
}
