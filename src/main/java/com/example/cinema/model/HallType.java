package com.example.cinema.model;

public enum HallType {
    VIP("VIP"),
    SOFT("Мягкий"),
    STANDART("Стандартный");

    private final String text;

    HallType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
