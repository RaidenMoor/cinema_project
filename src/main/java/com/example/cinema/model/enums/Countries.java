package com.example.cinema.model.enums;


public enum Countries {
    RUSSIA("Россия"),
    USA("США"),
    UK("Великобритания"),
    FRANCE("Франция"),
    GERMANY("Германия"),
    CHINA("Китай"),
    JAPAN("Япония"),
    SOUTH_KOREA("Южная Корея"),
    ITALY("Италия"),
    SPAIN("Испания"),
    CANADA("Канада");

    private final String text;

    Countries(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
