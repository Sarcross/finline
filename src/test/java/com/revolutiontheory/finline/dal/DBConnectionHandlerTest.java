package com.revolutiontheory.finline.dal;

import junit.framework.TestCase;

public class DBConnectionHandlerTest extends TestCase {
    public void testGetInstance() {
        DBConnectionHandler dbConnectionHandler = DBConnectionHandler.getInstance();
        assertNotNull(dbConnectionHandler);
        DBConnectionHandler.flushInstance();
        
    }

    public void testFlushInstance() {
        DBConnectionHandler dbConnectionHandler = DBConnectionHandler.getInstance();
        assertNotNull(dbConnectionHandler);
        DBConnectionHandler.flushInstance();
        assertEquals(null, dbConnectionHandler.getConnection());
    }

    public void testGetConnection() {
        DBConnectionHandler dbConnectionHandler = DBConnectionHandler.getInstance();
        assertNotNull(dbConnectionHandler.getConnection());
        DBConnectionHandler.flushInstance();
        assertEquals(null, dbConnectionHandler.getConnection());
    }
}