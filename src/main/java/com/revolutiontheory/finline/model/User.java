package com.revolutiontheory.finline.model;

import java.sql.ResultSet;
import java.sql.SQLException;
public class User {
    private int ID;

    public User() {
        
    }
    public User(ResultSet results) {
        try
        {
            results.first();
            this.ID = results.getInt("ID");
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        
    }
    public int getID() {
        return ID;
    }
}
