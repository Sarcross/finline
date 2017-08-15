package com.revolutiontheory.finline.dal;

import com.ibatis.common.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

public abstract class DBOperations {
    private final static String dbSetupScriptPath = "/home/sarcross/Development/finline/src/main/sql/dbSetup.sql";
    public static void dropDatabase(Connection connection) {
        if(!verifyDatabaseExists(connection))
            return;
        
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(DBString.DROP_DB + DBString.DB_NAME);
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
    }

    public static void createDatabase(Connection connection) {
        if(!verifyDatabaseExists(connection)) {
            try
            {
                ScriptRunner scriptRunner = new ScriptRunner(connection, false, false);
                Reader reader = new BufferedReader(new FileReader(dbSetupScriptPath));
                scriptRunner.runScript(reader);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }

    public static boolean addUser(Connection connection) {
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            statement.executeUpdate(DBString.MARIA_DB_INSERT_USER);

        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
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
        return true;
    }

    public static ResultSet getUser(Connection connection, int id) {
        ResultSet resultSet = null;
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            String sql = String.format(DBString.MARIA_DB_SELECT_USER, id);
            resultSet = statement.executeQuery(sql);

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
        return resultSet;
    }

    public static ResultSet getMostRecentUser(Connection connection) {
        ResultSet resultSet = null;
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            resultSet = statement.executeQuery(DBString.MARIA_DB_SELECT_USER_MOST_RECENT);

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
        return resultSet;
    }

    public static boolean addAccount(Connection connection, String[] args) {
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            String sql = String.format(DBString.MARIA_DB_INSERT_ACCOUNT, Integer.parseInt(args[1]), args[2], Double.parseDouble(args[3]));
            statement.executeUpdate(sql);

            
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
        }
        catch(NumberFormatException nfe)
        {
            nfe.printStackTrace();
            return false;
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
        return true;
    }

    public static boolean deleteAccount(Connection connection, int ID) {
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            String sql = String.format(DBString.MARIA_DB_DELETE_ACCOUNT, ID);
            statement.executeUpdate(sql);

            
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
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
        return true;
    }

    public static ResultSet getAccount(Connection connection, int ID) {
        ResultSet resultSet = null;
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            String sql = String.format(DBString.MARIA_DB_SELECT_ACCOUNT_SINGLE, ID);
            resultSet = statement.executeQuery(sql);

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
        return resultSet;
    }

    public static boolean addTransaction(Connection connection, String[] args) {
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            String sql = String.format(DBString.MARIA_DB_INSERT_TRANSACTION, Integer.parseInt(args[1]), args[2], Double.parseDouble(args[3]));
            statement.executeUpdate(sql);

            
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
        }
        catch(NumberFormatException nfe)
        {
            nfe.printStackTrace();
            return false;
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
        return true;
    }

    public static boolean deleteTransaction(Connection connection, int ID) {
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            String sql = String.format(DBString.MARIA_DB_DELETE_TRANSACTION, ID);
            statement.executeUpdate(sql);

            
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
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
        return true;
    }

    public static ResultSet getTransaction(Connection connection, int ID) {
        ResultSet resultSet = null;
        Statement statement =  null;
        try
        {
            statement = connection.createStatement();
            statement.execute(DBString.USE_DB);
            String sql = String.format(DBString.MARIA_DB_SELECT_TRANSACTION_SINGLE, ID);
            resultSet = statement.executeQuery(sql);

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
        return resultSet;
    }

    public static boolean verifyDatabaseExists(Connection connection) {
        try
        {
            ResultSet resultSet = connection.getMetaData().getCatalogs();
            String dbName;
            while(resultSet.next()) {
                dbName = resultSet.getString(1);
                if(dbName.equals(DBString.DB_NAME))
                    return true;
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }

        return false;
    }
}