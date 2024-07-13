CREATE DATABASE FoodOrderingSystem;
USE FoodOrderingSystem;

CREATE TABLE CUSTOMER (
    CustomerID CHAR(6) PRIMARY KEY,
    Password VARCHAR(12) NOT NULL,
    CustomerName VARCHAR(30) NOT NULL
);

CREATE TABLE STAFF (
    StaffID CHAR(6) PRIMARY KEY,
    Password VARCHAR(12) NOT NULL,
    StaffName VARCHAR(30) NOT NULL
);

CREATE TABLE CUSTOMER_ORDER (
    OrderID CHAR(6) PRIMARY KEY,
    OrderDateTime DATETIME NOT NULL,
    OrderType VARCHAR(10) NOT NULL,
    TableNo INT(3),
    Status VARCHAR(12) NOT NULL,
    CustomerID CHAR(6),
    StaffID CHAR(6),
    TotalPrice DECIMAL(6,2) NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES CUSTOMER(CustomerID),
    FOREIGN KEY (StaffID) REFERENCES STAFF(StaffID)
);

CREATE TABLE PAYMENT (
    PaymentID CHAR(6) PRIMARY KEY,
    TotalPayment DECIMAL(6,2) NOT NULL,
    PaymentDateTime DATETIME NOT NULL,
    PaymentType VARCHAR(15) NOT NULL,
    OrderID CHAR(6),
    StaffID CHAR(6),
    FOREIGN KEY (OrderID) REFERENCES CUSTOMER_ORDER(OrderID),
    FOREIGN KEY (StaffID) REFERENCES STAFF(StaffID)
);

CREATE TABLE MEAL (
    MealID CHAR(6) PRIMARY KEY,
    MealName VARCHAR(30) NOT NULL,
    MealType VARCHAR(10) NOT NULL,
    Price DECIMAL(5,2) NOT NULL,
    MealImage LONGBLOB
);

CREATE TABLE ORDER_MEAL (
    OrderID CHAR(6),
    MealID CHAR(6),
    Quantity INT(3) NOT NULL,
    PRIMARY KEY (OrderID, MealID),
    FOREIGN KEY (OrderID) REFERENCES CUSTOMER_ORDER(OrderID),
    FOREIGN KEY (MealID) REFERENCES MEAL(MealID)
);


INSERT INTO CUSTOMER (CustomerID, Password, CustomerName) VALUES
('C00001', 'pass123', 'Ahmad Faliq'),
('C00002', 'pass456', 'Safiyya Aliaa'),
('C00003', 'pass789', 'Angelina'),
('C00004', 'pass135', 'Yeoh Yuan Hui'),
('C00005', 'pass246', 'Jane Smith');

INSERT INTO STAFF (StaffID, Password, StaffName) VALUES
('S00001', 'staff123', 'Vincent Tan'),
('S00002', 'staff456', 'Greta Ho'),
('S00003', 'staff789', 'Imran Salleh'),
('S00004', 'staff135', 'Azlan Yusof'),
('S00005', 'staff246', 'Satish');

INSERT INTO MEAL (MealID, MealName, MealType, Price) VALUES
('M00001', 'Burger', 'Food', 7.99),
('M00002', 'Fries', 'Food', 6.99),
('M00003', 'Coke', 'Beverage', 3.50),
('M00004', 'Americano', 'Beverage', 5.90),
('M00005', 'Chicken Wrap', 'Food', 12.50);

