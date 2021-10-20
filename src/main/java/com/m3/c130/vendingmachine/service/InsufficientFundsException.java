package com.m3.c130.vendingmachine.service;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) {
        super(msg);
    }

    public InsufficientFundsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
