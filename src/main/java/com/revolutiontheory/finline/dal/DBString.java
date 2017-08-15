package com.revolutiontheory.finline.dal;

public abstract class DBString {
    public static final String DB_NAME = "finline";
    public static final String USE_DB = "USE " + DB_NAME + ";";
    public static final String DROP_DB = "DROP DATABASE ";

    //Tables
    public static final String TABLE_ACCOUNTS = "Accounts";
    public static final String TABLE_TRANSACTIONS = "Transactions";
    public static final String TABLE_USERS = "Users";

    //MariaDB Statements
    public static final String MARIA_DB_SELECT_USER = "SELECT * FROM Users WHERE ID=%d;";
    public static final String MARIA_DB_INSERT_USER = "INSERT INTO Users VALUES (DEFAULT);";
    public static final String MARIA_DB_SELECT_USER_MOST_RECENT = "SELECT * FROM Users ORDER BY ID DESC LIMIT 1";

    public static final String MARIA_DB_INSERT_ACCOUNT = "INSERT INTO Accounts VALUES (DEFAULT, %d, '%s', %f);";
    public static final String MARIA_DB_SELECT_ACCOUNT_ALL = "SELECT * FROM Accounts WHERE AccountID=%d;";
    public static final String MARIA_DB_SELECT_ACCOUNT_SINGLE = "SELECT * FROM Accounts WHERE ID=%d;";
    public static final String MARIA_DB_DELETE_ACCOUNT = "DELETE FROM Accounts WHERE ID=%d;";
    public static final String MARIA_DB_SELECT_ACCOUNT_MOST_RECENT = "SELECT * FROM Accounts ORDER BY ID DESC LIMIT 1";

    public static final String MARIA_DB_INSERT_TRANSACTION = "INSERT INTO Transactions VALUES (DEFAULT, %d, '%s', %f);";
    public static final String MARIA_DB_SELECT_TRANSACTION_SINGLE = "SELECT * FROM Transactions WHERE ID=%d;";
    public static final String MARIA_DB_SELECT_TRANSACTION_ALL = "SELECT * FROM Transactions WHERE AccountID=%d;";
    public static final String MARIA_DB_SELECT_TRANSACTION_ALL_INCOME = "SELECT * FROM Transactions WHERE AccountID=%d AND Type='I';";
    public static final String MARIA_DB_SELECT_TRANSACTION_ALL_EXPENSE = "SELECT * FROM Transactions WHERE AccountID=%d AND Type='E';";
    public static final String MARIA_DB_DELETE_TRANSACTION = "DELETE FROM Transactions WHERE ID=%d";
    public static final String MARIA_DB_SELECT_TRANSACTION_MOST_RECENT = "SELECT * FROM Transactions ORDER BY ID DESC LIMIT 1";
}