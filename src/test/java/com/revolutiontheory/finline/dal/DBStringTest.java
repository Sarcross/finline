package com.revolutiontheory.finline.dal;

import junit.framework.TestCase;

public class DBStringTest extends TestCase {
    public void testDBStringConstants() {
        assertEquals("finline", DBString.DB_NAME);

        //Tables
        assertEquals("Accounts", DBString.TABLE_ACCOUNTS);
        assertEquals("Users", DBString.TABLE_USERS);
        assertEquals("Transactions", DBString.TABLE_TRANSACTIONS);

        //Columns

    }
}