package com.m3.c130.vendingmachine.service;

import com.m3.c130.vendingmachine.dao.NoItemInventoryException;
import com.m3.c130.vendingmachine.dao.PersistenceException;
import com.m3.c130.vendingmachine.dto.Item;

import java.util.List;

public interface VMServiceLayer {
    Item getItem(int id);

    int getCount(int id);

    List<Item> getItemsList();

    Change buyItem(int id, int money) throws NoItemInventoryException, InsufficientFundsException, PersistenceException;
}
