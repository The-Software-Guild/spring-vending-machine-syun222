package com.m3.c130.vendingmachine.service;

import com.m3.c130.vendingmachine.dao.NoItemInventoryException;
import com.m3.c130.vendingmachine.dao.PersistenceException;
import com.m3.c130.vendingmachine.dao.VMAuditDao;
import com.m3.c130.vendingmachine.dao.VMDao;
import com.m3.c130.vendingmachine.dto.Item;

import java.util.List;

public class VMServiceLayerImpl implements VMServiceLayer {
    private VMDao dao;
    private VMAuditDao audit;

    public VMServiceLayerImpl(VMDao dao, VMAuditDao audit) {
        this.audit = audit;
        this.dao = dao;
    }

    public Item getItem(int id) {
        return dao.getItem(id);
    }

    public int getCount(int id) {
        return dao.getCount(id);
    }

    public List<Item> getItemsList() {
        return dao.getItemsList();
    }

    public Change buyItem(int id, int money) throws NoItemInventoryException, InsufficientFundsException, PersistenceException {
        Item item = getItem(id);
        if (item == null) {
            throw new NoItemInventoryException("No such item");
        }
        int diff = money - item.getPrice();
        if (diff >= 0) {
            Change change = new Change(diff);
            dao.buyItem(id);
            audit.writeAuditEntry("Item " + item.getId() + " " + item.getName() + " Sold");
            return change;
        } else {
            throw new InsufficientFundsException("Not enough money!");
        }
    }
}
