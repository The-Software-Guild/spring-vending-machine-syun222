package com.m3.c130.vendingmachine;

import com.m3.c130.vendingmachine.controller.VMController;
import com.m3.c130.vendingmachine.dao.*;
import com.m3.c130.vendingmachine.service.VMServiceLayer;
import com.m3.c130.vendingmachine.service.VMServiceLayerImpl;
import com.m3.c130.vendingmachine.ui.UserIO;
import com.m3.c130.vendingmachine.ui.UserIOConsoleImpl;
import com.m3.c130.vendingmachine.ui.VMView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.m3.c130.vendingmachine");
        appContext.refresh();

        VMController controller = appContext.getBean("vmcontroller", VMController.class);
        controller.run();
    }
}
