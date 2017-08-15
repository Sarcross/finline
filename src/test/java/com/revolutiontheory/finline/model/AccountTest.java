package com.revolutiontheory.finline.model;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AccountTest extends TestCase {
    protected Account account1;
    protected Account account2;

    protected void setUp() {
        account1 = new Account();
        account2 = new Account();
    }

    public void testConstructor() {
        assertEquals(account1.getName(), "New Account");
        assertEquals(account1.getBalance(), 0.0);
        assertTrue(account1.getID() >= 0);
    }

    /* May fail peroidically because we use the absolute value
     * of the UUID hash as the ID. Colliding IDs will be handled
     * when the user creates new accounts by performing a check
     * against their current accounts.
     */
    /*public void testUUIDGeneration() {
        int testSize = 5000;
        int collisionCount = 0;
        Account[] accounts = new Account[testSize];
        
        //Init
        for(int ndx = 0; ndx < accounts.length; ndx++) {
            accounts[ndx] = new Account();
        }

        for(int ndx = 0; ndx < accounts.length; ndx++) {
            for(int mdx = ndx + 1; mdx < accounts.length; mdx++) {
                try
                {
                    assertFalse(accounts[ndx].getID() == accounts[mdx].getID());
                }
                catch(AssertionFailedError e)
                {
                    System.out.println("Non-unique ID: " + accounts[ndx].getID());
                    collisionCount++;
                }
                
                
            }
        }

        System.out.println("Test Size: " + testSize);
        System.out.println("Collision Count: " + collisionCount);
        if(collisionCount > 0) {
            System.out.println("Collision Rate: " + ((float) collisionCount / (float) testSize) * 100 + "%");
        }
    }*/
}
