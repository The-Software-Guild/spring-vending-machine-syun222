package com.m3.c130.vendingmachine.controller;

import com.m3.c130.vendingmachine.dao.NoItemInventoryException;
import com.m3.c130.vendingmachine.dao.PersistenceException;
import com.m3.c130.vendingmachine.service.Change;
import com.m3.c130.vendingmachine.service.InsufficientFundsException;
import com.m3.c130.vendingmachine.service.VMServiceLayer;
import com.m3.c130.vendingmachine.ui.UserIO;
import com.m3.c130.vendingmachine.ui.UserIOConsoleImpl;
import com.m3.c130.vendingmachine.ui.VMView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("vmcontroller")
public class VMController {
    private VMView view;
    private UserIO io = new UserIOConsoleImpl();
    private VMServiceLayer service;

    @Autowired
    public VMController(VMView view, VMServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            menuSelection = view.getMenuSelection();
            switch (menuSelection) {
                case 1:
                    buy();
                    keepGoing = false;
                    break;
                case 2:
                    keepGoing = false;
                    break;
                default:
                    view.invalidChoice();
            }
        }
        view.displayExit();
    }

    private void buy() {
        int money = view.insertMoney();
        boolean keepGoing = true;
        while (keepGoing) {
            int itemId = view.chooseItem();
            if (itemId == 0) {
                keepGoing = false;
            } else {
                try {
                    Change change = service.buyItem(itemId, money);
                    returnChange(change);
                    keepGoing = false;
                } catch (NoItemInventoryException e) {
                    io.print(e.getMessage());
                } catch (InsufficientFundsException e) {
                    io.print(e.getMessage());
                } catch (PersistenceException e) {
                    io.print(e.getMessage());
                    returnChange(new Change(money));
                    keepGoing = false;
                }
            }
        }
    }

    private void returnChange(Change change) {
        view.displayChange(change);
    }
}
