package com.m3.c130.vendingmachine.service;

import com.m3.c130.vendingmachine.dao.*;
import com.m3.c130.vendingmachine.dto.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VMServiceLayerImplTest {
    private VMServiceLayer service;

    public VMServiceLayerImplTest() {
        VMDao dao = new VMDaoStubImpl();
        VMAuditDao audit = new VMAuditDaoStubImpl();
        service = new VMServiceLayerImpl(dao, audit);
    }

    @Test
    void normalBuyItem() {
        Item onlyItem = new Item(1, "Snickers", 100);

        int fund = 200;
        Change change;
        try {
            change = service.buyItem(onlyItem.getId(), fund);
        } catch (InsufficientFundsException| NoItemInventoryException| PersistenceException e) {
            fail("Unexpected exception thrown");
            return;
        }
        Change correctChange = new Change(fund - onlyItem.getPrice());

        for (Coin coin: new ArrayList<>(Arrays.asList(Coin.values()))) {
            assertEquals(change.getCount(coin), correctChange.getCount(coin));
        }
    }

    @Test
    void insufficientFundTest() {
        Item onlyItem = new Item(1, "Snickers", 100);
        try {
            service.buyItem(onlyItem.getId(), 80);
            fail("Exception should have been thrown");
        } catch (InsufficientFundsException e) {
            return;
        } catch ( NoItemInventoryException| PersistenceException e) {
            fail("Incorrect exception thrown");
        }
    }

    @Test
    void noItemTest() {
        try {
            service.buyItem(3, 80);
            fail("Exception should have been thrown");
        } catch (NoItemInventoryException e) {
            return;
        } catch ( InsufficientFundsException| PersistenceException e) {
            fail("Incorrect exception thrown");
        }
    }
}