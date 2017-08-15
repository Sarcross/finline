package com.revolutiontheory.finline;

import com.revolutiontheory.finline.dal.*;
import com.revolutiontheory.finline.model.*;

public class App{
    private static final String APP_NAME = "Finline";
    private static final String APP_DESC = "An oversimplified command line version of QuickBooks";
    private static final String VERSION = "0.1.0";
    public static void main(String[] args) {
        if(args.length < 1) {
            printUsage();
            return;
        }

        User tempUser = null;
        Account tempAccount = null;
        Transaction tempTransaction = null;

        switch(args[0])
        {
            case "-h":
            case "--help":
                printUsage();
                break;

            case "-I":
            case "--init":
                System.out.println("Initializing Finline...");
                DBOperations.createDatabase(DBConnectionHandler.getInstance().getConnection());
                System.out.println("Finline Database Initialized");
                System.out.println("Setting up initial user...");
                DBOperations.addUser(DBConnectionHandler.getInstance().getConnection());
                tempUser = new User(DBOperations.getMostRecentUser(DBConnectionHandler.getInstance().getConnection()));
                System.out.printf("User created. Your ID is: %d\n", tempUser.getID());
                break;

            case "--addaccount":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                if(args.length != 4) {
                    System.out.println("Usage: --addaccount [UserID] [AccountName] [Balance]");
                    break;
                }
                System.out.println("Adding account...");
                if(DBOperations.addAccount(DBConnectionHandler.getInstance().getConnection(), args)) {
                    //tempAccount = DBOperations.getMostRecentAccount(DBConnectionHandler.getInstance().getConnection());
                    //System.out.printf("Account added. Your Account ID is: %d\n", tempAccount.getID());
                }
                else {
                    System.out.println("Adding failed.");
                }
                
                break;

            case "--removeaccount":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                if(args.length != 2) {
                    System.out.println("Error. Usage: --removeaccount [AccountID]");
                    break;
                }
                System.out.println("Removing account...");
                if(DBOperations.deleteAccount(DBConnectionHandler.getInstance().getConnection(), Integer.parseInt(args[1]))) {
                    System.out.println("Account Removed!");
                }
                else {
                    System.out.println("Removal failed.");
                }
                
                break;

            case "--listaccounts":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                System.out.println("Listing accounts...");
                break;

            case "--listtransactions":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                System.out.println("Listing transactions...");
                break;

            case "-r":
            case "--report":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                System.out.println("Generating report...");
                break;

            case "-R":
            case "--reset":
                //System.out.printf("Are you sure you want to reset %s? Resetting %s will erase all data. Continue: [y/N]", APP_NAME, APP_NAME);
                System.out.printf("Resetting %s...\n", APP_NAME);
                DBOperations.dropDatabase(DBConnectionHandler.getInstance().getConnection());
                System.out.printf("%s reset. Please use -I or --init on your next command.\n", APP_NAME);
                break;

            case "-e":
            case "--expense":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                if(args.length != 4) {
                    System.out.println("Error. Usage: -e | --expense [AccountID] [Type (E | I)] [Amount]");
                    break;
                }
                System.out.println("Adding expense to account...");
                DBOperations.addTransaction(DBConnectionHandler.getInstance().getConnection(), args);
                System.out.println("Expense added!");
                break;
            case "--removeexpense":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                if(args.length != 2) {
                    System.out.println("Error. Usage: --removeexpense [TransactionID]");
                    break;
                }
                System.out.println("Removing expense...");
                if(DBOperations.deleteTransaction(DBConnectionHandler.getInstance().getConnection(), Integer.parseInt(args[1]))) {
                    System.out.println("Expense removed!");
                }
                else {
                    System.out.println("Removal failed.");
                }
                break;

            case "-i":
            case "--income":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                if(args.length != 4) {
                    System.out.println("Error. Usage: -e | --expense [AccountID] [Type (E | I)] [Amount]");
                    break;
                }
                System.out.println("Adding income to account...");
                DBOperations.addTransaction(DBConnectionHandler.getInstance().getConnection(), args);
                System.out.println("Income added!");
                break;

            case "--removeincome":
                if(!DBOperations.verifyDatabaseExists(DBConnectionHandler.getInstance().getConnection())) {
                    System.out.println("Please use the -I or --init command first.");
                    break;
                }
                if(args.length != 2) {
                    System.out.println("Error. Usage: --removeincome [TransactionID]");
                    break;
                }
                System.out.println("Removing income...");
                if(DBOperations.deleteTransaction(DBConnectionHandler.getInstance().getConnection(), Integer.parseInt(args[1]))) {
                    System.out.println("Income removed!");
                }
                else {
                    System.out.println("Removal failed.");
                }
                break;

            default:
                System.out.println("Error, command \"" + args[0] + "\" not found.");
                break;
        }
    }

    public static void printUsage() {
        System.out.println(APP_NAME);
        System.out.println(VERSION);
        System.out.println(APP_DESC);
        System.out.println("Usage: Finline [-option]");
        System.out.println("Where options include:");

        System.out.println("\t-h | --help\t\t Display help");

        System.out.println("\t-I | --init\t\t Initialize Finline");

        System.out.println("\t-[TBD] AccountName | --addaccount AccountName\t\t Add account");

        System.out.println("\t-[TBD] AccountNumber | --removeaccount AccountNumber\t\t Remove account");

        System.out.println("\t-[TBD] | --listaccounts\t\t Display acount list");

        System.out.println("\t-[TBD] [AccountNumber] | --listtransactions [AccountNumber]\t\t Display transactions");

        System.out.println("\t-r | --report\t\t Generate report");

        System.out.println("\t-R | --reset\t\t Reset Finline");

        System.out.println("\t-e AccountNumber Amount | --expense AccountNumber Amount\t\t Add expense to account");

        System.out.println("\t--removeexpense ExpenseID\t\t Remove expense from account");

        System.out.println("\t-i AccountNumber Amount | --income AccountNumber Amount\t\t Add income to account");

        System.out.println("\t--removeincome IncomeID\t\t Remove income from account");
    }
}
