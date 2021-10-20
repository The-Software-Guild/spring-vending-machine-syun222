package com.m3.c130.vendingmachine.service;

public enum Coin {
    TWO_POUND(200, "Two Pound"),
    POUND(100, "One Pound"),
    FIFTY(50, "Fifty Pence"),
    TWENTY(20, "Twenty Pence"),
    TEN(10, "Ten Pence"),
    FIVE(5, "Five Pence"),
    TWO(2, "Two Pence"),
    ONE(1, "One Pence");

    private int value;
    private String name;

    Coin(int pence, String name) {
        this.value = pence;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}
