package com.example.cinema.dto;

public interface SeatsMapDTO {

    Long getSeatId();
    Byte getRow();
    Byte getPlace();
    Long getOrderId();
    boolean isDeleted();
}
