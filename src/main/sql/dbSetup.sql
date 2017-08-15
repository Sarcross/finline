CREATE DATABASE finline;

USE finline;

CREATE TABLE Users(
    ID INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (ID)
);

CREATE TABLE Accounts(
    ID INT NOT NULL AUTO_INCREMENT,
    UserID INT NOT NULL,
    Name VARCHAR(255) NOT NULL,
    Balance DECIMAL (18, 2) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (UserID) REFERENCES Users(ID) ON DELETE CASCADE
);

CREATE TABLE Transactions(
    ID INT NOT NULL AUTO_INCREMENT,
    AccountID INT NOT NULL,
    Type CHAR (1) NOT NULL,
    Amount DECIMAL (18, 2) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (AccountID) REFERENCES Accounts(ID) ON DELETE CASCADE
);
