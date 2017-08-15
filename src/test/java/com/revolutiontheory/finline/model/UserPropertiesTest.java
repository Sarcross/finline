package com.revolutiontheory.finline.model;

import junit.framework.TestCase;

public class UserPropertiesTest extends TestCase {
    protected UserProperties properties;

    protected void setUp() {
        properties = new UserProperties();
    }

    public void testProperties() {
        assertNotNull(properties);
    }

    public void addToPropertiesTest() {
        properties.put("TestKey", "TestValue");
        assertEquals(1, properties.size());
        assertTrue(properties.containsKey("TestKey"));
        assertEquals("TestValue", properties.get("TestKey"));
    }
}