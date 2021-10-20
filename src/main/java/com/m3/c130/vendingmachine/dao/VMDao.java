package com.m3.c130.vendingmachine.dao;

import com.m3.c130.vendingmachine.dto.Item;

import java.util.List;

public interface VMDao {
    Item getItem(int id);
    List<Item> getItemsList();
    int getCount(int id);
    void buyItem(int id) throws NoItemInventoryException, PersistenceException;
    void addItem(Item item, int count);
}
