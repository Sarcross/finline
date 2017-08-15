package com.revolutiontheory.finline.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    private int ID;
    private int AccountID;
    private String type;
    private double amount;

    public Transaction() {
        this.ID = 1;
        this.AccountID = 1;
        this.type = "I";
        this.amount = 0.0;
    }

    public Transaction(ResultSet results) {
        try
        {
            results.first();
            this.ID = results.getInt("ID");
            this.AccountID = results.getInt("AccountID");
            this.type = results.getString("Type");
            this.amount = results.getDouble("Amount");
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
    }

    public int getID() {
        return this.ID;
    }

    public int getAccountID() {
        return this.AccountID;
    }

    public String getType() {
        return this.type;
    }

    public double getAmount() {
        return this.amount;
    }
}