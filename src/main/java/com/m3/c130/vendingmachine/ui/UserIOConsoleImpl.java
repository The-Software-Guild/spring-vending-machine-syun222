package com.m3.c130.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    private Scanner sc = new Scanner(System.in);

    public void print(String msg) {
        System.out.println(msg);
    }

    public int readInt(String prompt) {
        System.out.println(prompt);
        Boolean isValid = false;
        int answer = 0;
        do {
            try {
                answer = Integer.parseInt(sc.nextLine());
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Not integer");
            }
        } while (!isValid);
        return answer;
    }

    public double readDouble(String prompt) {
        System.out.println(prompt);
        Boolean isValid = false;
        double answer = 0;
        do {
            try {
                answer = Double.parseDouble(sc.nextLine());
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Not double");
            }
        } while (!isValid);
        return answer;
    }

    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }

    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        return BigDecimal.valueOf(readDouble(prompt));
    }
}
