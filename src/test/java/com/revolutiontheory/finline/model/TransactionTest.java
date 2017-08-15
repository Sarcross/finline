package com.revolutiontheory.finline.model;

import junit.framework.TestCase;

public class TransactionTest extends TestCase {
    private Transaction transaction;

    protected void setUp() {
        transaction = new Transaction();
    }

    public void testConstructor() {
        assertEquals(1, transaction.getID());
        assertEquals(1, transaction.getAccountID());
        assertEquals("I", transaction.getType());
        assertEquals(0.0, transaction.getAmount());
    }
}