package com.example.cinema.dto;

public interface SeatsMapDTO {

    Long getSeatId();
    Byte getRow();
    Byte getPlace();
    Long getOrderId();
    Double getPrice();
    boolean isDeleted();
}
