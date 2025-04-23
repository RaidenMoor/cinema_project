package com.example.cinema.model.enums;

public enum Coeffintient {
    VIP(2.0),
    STANDART(1.0),
    SOFT(1.5),
    NEAR(0.5),
    FAR(1.7),
    CENTER(1.2);

    private final double text;

    Coeffintient(Double text) {
        this.text = text;
    }

    public double getText() {
        return text;
    }
}
