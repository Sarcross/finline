package com.revolutiontheory.finline.dal;

import com.revolutiontheory.finline.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

public class DBOperationsTest extends TestCase {
    private static int CASES = 100;
    public void testDropDatabase() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        if(DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection()))
            fail();
    }

    public void testCreateDatabase() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection()))
            fail();
    }

    public void testAddUser() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        for(int ndx = 0 ;ndx < CASES; ndx++) {
            if(!DBOperations.addUser(DBConnectionHandler.getInstance().getConnection()))
                fail();
        }
        Statement statement =  null;
        ResultSet resultSet = null;
        try
        {
            statement = DBConnectionHandler.getInstance().getConnection().createStatement();
            statement.execute(DBString.USE_DB);
            String sql = "SELECT COUNT(*) FROM Users";
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                int value = resultSet.getInt(1);
                assertEquals(CASES, value);
            }

        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }

    public void testGetUser() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.addUser(DBConnectionHandler.getInstance().getConnection());
        User result = new User(DBOperations.getUser(DBConnectionHandler.getInstance().getConnection(), 1));
        assertNotNull(result);
        assertEquals(1, result.getID());
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }

    public void testGetMostRecentUser() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        for(int ndx = 0; ndx < CASES; ndx++) {
            if(!DBOperations.addUser(DBConnectionHandler.getInstance().getConnection()))
                fail();
        }
        
        User result = new User(DBOperations.getMostRecentUser(DBConnectionHandler.getInstance().getConnection()));
        assertNotNull(result);
        assertEquals(CASES, result.getID());

        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }

    public void testAddAccount() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        if(!DBOperations.addUser(DBConnectionHandler.getInstance().getConnection()))
            fail();

        String[] args = {"blankarg", "1", "AccountName", "5000.00"};

        for(int ndx = 0; ndx < CASES; ndx++) {
            if(!DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), args))
                fail();
        }
        
        Statement statement =  null;
        ResultSet resultSet = null;
        try
        {
            statement = DBConnectionHandler.getInstance().getConnection().createStatement();
            statement.execute(DBString.USE_DB);
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM Accounts");
            resultSet.next();
            int value = resultSet.getInt(1);
            assertEquals(CASES, value);

        }
        catch(SQLException se)
        {
            se.printStackTrace();
            fail();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }

    public static void testDeleteAccount() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.addUser(DBConnectionHandler.getInstance().getConnection());

        String[] args1 = {"blankarg", "1", "AccountName1", "5000.00"};
        String[] args2 = {"blankarg", "1", "AccountName2", "5400.00"};
        String[] args3 = {"blankarg", "1", "AccountName3", "2000.00"};
        String[] args4 = {"blankarg", "1", "AccountName4", "50.00"};
        String[] args5 = {"blankarg", "1", "AccountName5", "59000.00"};
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), args1);
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), args2);
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), args3);
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), args4);
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), args5);

        if(!DBOperations.deleteAccount(DBConnectionHandler.getInstance().getConnection(), 2))
            fail();
        if(!DBOperations.deleteAccount(DBConnectionHandler.getInstance().getConnection(), 5))
            fail();

        Statement statement =  null;
        ResultSet resultSet = null;
        try
        {
            statement = DBConnectionHandler.getInstance().getConnection().createStatement();
            statement.execute(DBString.USE_DB);
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM Accounts");
            resultSet.first();
            int value = resultSet.getInt(1);
            assertEquals(3, value);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            fail();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }

    public void testGetAccount() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.addUser(DBConnectionHandler.getInstance().getConnection());

        String[] accountArgs = {"blankarg", "1", "AccountName", "5000.00"};
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), accountArgs);
        Account account = new Account(DBOperations.getAccount(DBConnectionHandler.getInstance().getConnection(), 1));
        assertEquals(1, account.getID());
        assertEquals("AccountName", account.getName());
        assertEquals(5000.00, account.getBalance());
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }

    public void testAddTransaction() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.addUser(DBConnectionHandler.getInstance().getConnection());

        String[] accountArgs = {"blankarg", "1", "AccountName", "59000.00"};
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), accountArgs);
        String[] transactionArgs = {"blankarg", "1", "I", "100.00"};
        for(int ndx = 0; ndx < CASES; ndx++) {
            if(!DBOperations.addTransaction(DBConnectionHandler.getInstance().getConnection(), transactionArgs))
                fail();
        }
        
        Statement statement =  null;
        ResultSet resultSet = null;
        try
        {
            statement = DBConnectionHandler.getInstance().getConnection().createStatement();
            statement.execute(DBString.USE_DB);
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM Transactions");
            resultSet.next();
            int value = resultSet.getInt(1);
            assertEquals(CASES, value);

        }
        catch(SQLException se)
        {
            se.printStackTrace();
            fail();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }

    public void testDeleteTransaction() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.addUser(DBConnectionHandler.getInstance().getConnection());

        String[] accountArgs = {"blankarg", "1", "AccountName", "5000.00"};
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), accountArgs);
        String[] transactionArgs = {"blankarg", "1", "E", "100.00"};
        for(int ndx = 0; ndx < 5; ndx++) {
            if(!DBOperations.addTransaction(DBConnectionHandler.getInstance().getConnection(), transactionArgs))
                fail();
        }

        if(!DBOperations.deleteTransaction(DBConnectionHandler.getInstance().getConnection(), 1))
            fail();
        if(!DBOperations.deleteTransaction(DBConnectionHandler.getInstance().getConnection(), 3))
            fail();

        Statement statement =  null;
        ResultSet resultSet = null;
        try
        {
            statement = DBConnectionHandler.getInstance().getConnection().createStatement();
            statement.execute(DBString.USE_DB);
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM Transactions");
            resultSet.first();
            int value = resultSet.getInt(1);
            assertEquals(3, value);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            fail();
        }
        finally
        {
            try
            {
                statement.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }

    public void testGetTransaction() {
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
        DBOperations.addUser(DBConnectionHandler.getInstance().getConnection());

        String[] accountArgs = {"blankarg", "1", "AccountName", "5000.00"};
        DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), accountArgs);
        String[] transactionArgs = {"blankarg", "1", "E", "100.00"};
        for(int ndx = 0; ndx < 5; ndx++) {
            if(!DBOperations.addTransaction(DBConnectionHandler.getInstance().getConnection(), transactionArgs))
                fail();
        }
        Transaction transaction = new Transaction(DBOperations.getTransaction(DBConnectionHandler.getInstance().getConnection(), 3));
        assertEquals(3, transaction.getID());
        assertEquals(1, transaction.getAccountID());
        assertEquals("E", transaction.getType());
        assertEquals(100.00, transaction.getAmount());
        DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
    }
}