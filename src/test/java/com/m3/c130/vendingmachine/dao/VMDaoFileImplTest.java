package com.m3.c130.vendingmachine.dao;

import com.m3.c130.vendingmachine.dto.Item;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class VMDaoFileImplTest {
    VMDao testDao;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception{
        String testFile = "testvm.txt";
        new FileWriter(testFile);
        testDao = new VMDaoFileImpl(testFile);
    }

    @org.junit.jupiter.api.Test
    void getItem() {
        Item item1 = new Item(1, "Snickers", 100);
        Item item2 = new Item(2, "Bounty", 200);

        testDao.addItem(item1, 1);
        testDao.addItem(item2, 4);

        assertEquals(item1, testDao.getItem(1));
        assertNull(testDao.getItem(3));
    }

    @org.junit.jupiter.api.Test
    void getItemsList() {
        Item item1 = new Item(1, "Snickers", 100);
        Item item2 = new Item(2, "Bounty", 200);

        testDao.addItem(item1, 1);
        testDao.addItem(item2, 4);

        List<Item> itemsList = testDao.getItemsList();
        assertEquals(itemsList.size(), 2);
        assertTrue(itemsList.contains(item1));
        assertTrue(itemsList.contains(item2));
    }

    @org.junit.jupiter.api.Test
    void getCount() {
        Item item1 = new Item(1, "Snickers", 100);
        Item item2 = new Item(2, "Bounty", 200);

        testDao.addItem(item1, 1);
        testDao.addItem(item2, 4);

        assertEquals(testDao.getCount(2), 4);
    }

    @org.junit.jupiter.api.Test
    void buyItem() {
        Item item1 = new Item(1, "Snickers", 100);

        testDao.addItem(item1, 1);

        try {
            testDao.buyItem(item1.getId());
        } catch (NoItemInventoryException|PersistenceException e) {
            fail("Incorrect exception thrown");
        }
        assertEquals(testDao.getCount(item1.getId()), 0);

        try {
            testDao.buyItem(item1.getId());
            fail("No item exception should be thrown");
        } catch (NoItemInventoryException e) {

        } catch (PersistenceException e) {
            fail("File exception");
        }
        try {
            testDao.buyItem(3);
            fail("No item exception should be thrown");
        } catch (NoItemInventoryException e) {

        } catch (PersistenceException e) {
            fail("File exception");
        }
    }
}