package com.m3.c130.vendingmachine.service;

import java.util.*;

public class Change {
    private Map<Coin, Integer> changes = new HashMap<>();
    private static List<Coin> coins = new ArrayList<>(Arrays.asList(Coin.values()));
    private int total;

    public Change(int x) {
        this.total = x;
        for (Coin coin : coins) {
            int n = x / coin.getValue();
            changes.put(coin, n);
            x -= n * coin.getValue();
        }

    }

    public int getCount(Coin coin) {
        return changes.get(coin);
    }

    public int getTotal() {
        return this.total;
    }
}
