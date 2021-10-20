package com.m3.c130.vendingmachine;

import com.m3.c130.vendingmachine.controller.VMController;
import com.m3.c130.vendingmachine.dao.*;
import com.m3.c130.vendingmachine.service.VMServiceLayer;
import com.m3.c130.vendingmachine.service.VMServiceLayerImpl;
import com.m3.c130.vendingmachine.ui.UserIO;
import com.m3.c130.vendingmachine.ui.UserIOConsoleImpl;
import com.m3.c130.vendingmachine.ui.VMView;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        try {
            VMDao dao = new VMDaoFileImpl();
            VMAuditDao audit = new VMAuditDaoFileImpl();
            VMServiceLayer service = new VMServiceLayerImpl(dao, audit);
            VMView view = new VMView(io, service);
            VMController controller = new VMController(view, service);
            controller.run();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }
}
