package com.revolutiontheory.finline.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Account {
    private int ID;
    private int UserID;
    private String name;
    private double balance;

    public Account() {
        ID = generateID();
        UserID = 0;
        this.name = "New Account";
        this.balance = 0.0f;
    }

    public Account(ResultSet results) {
        try
        {
            results.first();
            this.ID = results.getInt("ID");
            this.UserID = results.getInt("UserID");
            this.name = results.getString("Name");
            this.balance = results.getDouble("Balance");


        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
    }

    public int getID() {
        return ID;
    }

    public int getUserID() {
        return this.UserID;
    }
    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    private int generateID() {
        return Math.abs(UUID.randomUUID().hashCode());
    }
}
