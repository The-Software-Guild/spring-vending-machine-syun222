package com.m3.c130.vendingmachine.ui;

import java.math.BigDecimal;

public interface UserIO {
    void print(String msg);

    int readInt(String prompt);

    String readString(String prompt);

    double readDouble(String prompt);

    BigDecimal readBigDecimal(String prompt);
}
