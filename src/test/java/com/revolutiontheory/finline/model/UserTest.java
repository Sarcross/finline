package com.revolutiontheory.finline.model;

import junit.framework.TestCase;

public class UserTest extends TestCase {
    protected User user;

    protected void setUp() {
        user = new User();
    }

    public void testUser() {
        assertEquals(0, user.getID());
    }
}
