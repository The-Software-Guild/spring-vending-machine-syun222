package com.m3.c130.vendingmachine.dao;

import com.m3.c130.vendingmachine.dto.Item;

import java.util.ArrayList;
import java.util.List;

public class VMDaoStubImpl implements VMDao{
    public Item onlyItem;
    public int onlyCount;

    public VMDaoStubImpl() {
        onlyItem = new Item();
        onlyItem.setId(1);
        onlyItem.setName("Snickers");
        onlyItem.setPrice(100);
        onlyCount = 2;
    }
    public Item getItem(int id) {
        if (id == onlyItem.getId()) {
            return onlyItem;
        } else {
            return null;
        }
    }
    public List<Item> getItemsList() {
        List<Item> list = new ArrayList<>();
        list.add(onlyItem);
        return list;
    }
    public int getCount(int id) {
        return onlyCount;
    }
    public void buyItem(int id) throws NoItemInventoryException, PersistenceException {
        if (id == onlyItem.getId()) {
            return;
        } else {
            throw new NoItemInventoryException("No item");
        }
    }
    public void addItem(Item item, int count) {
        return;
    }
}
