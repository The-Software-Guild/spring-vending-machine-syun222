package com.m3.c130.vendingmachine.dao;

public interface VMAuditDao {
    void writeAuditEntry(String entry) throws PersistenceException;
}
