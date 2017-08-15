package com.revolutiontheory.finline.dal;

import java.sql.*;


public class DBConnectionHandler {
    private final String DB_URL = "jdbc:mysql://localhost";
    private final String USER = "sarcross";
    private final String PASS = "008677968";

    // Can try several drivers on init
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MARIA_DRIVER = "org.mariadb.jdbc.Driver";

    private Connection connection;
    private static DBConnectionHandler _dbConnectionHandler;

    private DBConnectionHandler() {
        try
        {
            Class.forName(MARIA_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
    }

    public static DBConnectionHandler getInstance() {
        if(_dbConnectionHandler == null)
            _dbConnectionHandler = new DBConnectionHandler();
        return _dbConnectionHandler;
    }

    public static void flushInstance() {
        if(_dbConnectionHandler == null)
            return;
        
        try
        {
            _dbConnectionHandler.connection.close();
            _dbConnectionHandler.connection = null;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        _dbConnectionHandler = null;

    }
    public Connection getConnection() {
        return this.connection;
    }
}