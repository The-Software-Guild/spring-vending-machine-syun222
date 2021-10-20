package com.m3.c130.vendingmachine.ui;

import com.m3.c130.vendingmachine.dto.Item;
import com.m3.c130.vendingmachine.service.Change;
import com.m3.c130.vendingmachine.service.Coin;
import com.m3.c130.vendingmachine.service.VMServiceLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VMView {
    private UserIO io;
    VMServiceLayer service;

    public VMView(UserIO io, VMServiceLayer service) {
        this.io = io;
        this.service = service;
    }

    public int getMenuSelection() {
        io.print("Vending Machine");
        displayItems();
        io.print("1. Insert Money");
        io.print("2. Exit");
        return io.readInt("Enter 1 or 2: ");
    }

    public void displayItems() {
        List<Item> itemsList = service.getItemsList().stream().filter((item) -> service.getCount(item.getId()) > 0).collect(Collectors.toList());
        io.print("=== Items List ===");
        for (Item item : itemsList) {
            io.print("ID: " + item.getId() + ", Name: " + item.getName() + ", Price: " + item.getPrice() + "p");
        }
        io.print("==================");
    }

    public int insertMoney() {
        return io.readInt("Enter fund in pence: ");
    }

    public int chooseItem() {
        return io.readInt("Enter ID of item you want. Enter 0 to quit: ");
    }

    public void displayChange(Change change) {
        List<Coin> coins = new ArrayList<>(Arrays.asList(Coin.values()));
        io.print("Here is your change: " + change.getTotal() + "p");
        for (Coin coin : coins) {
            if (change.getCount(coin) != 0) {
                io.print(coin.getName() + " X " + change.getCount(coin));
            }
        }
    }

    public void displayExit() {
        io.print("Thank you and Goodbye!");
    }

    public void invalidChoice() {
        io.print("Invalid input");
    }
}
