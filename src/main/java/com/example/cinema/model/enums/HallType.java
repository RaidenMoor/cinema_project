package com.example.cinema.model.enums;

import lombok.Getter;

@Getter
public enum HallType {
    VIP("VIP"),
    SOFT("Мягкий"),
    STANDART("Стандартный");


    private final String displayName;

    HallType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
