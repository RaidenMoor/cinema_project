package com.example.cinema.model.enums;

public enum Coeffintient {
    VIP("2"),
    STANDART("1"),
    SOFT("1.5"),
    NEAR("0.5"),
    FAR("1.7"),
    CENTER("1.2");

    private final String text;

    Coeffintient(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
