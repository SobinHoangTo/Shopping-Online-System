USE SWD392_SE1820_SP25_G1
GO

USE [master]
GO

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'SWD392_SE1820_SP25_G1')
BEGIN
	ALTER DATABASE SWD392_SE1820_SP25_G1 SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE SWD392_SE1820_SP25_G1 SET ONLINE;
	DROP DATABASE SWD392_SE1820_SP25_G1;
END

GO
CREATE DATABASE SWD392_SE1820_SP25_G1
GO


USE SWD392_SE1820_SP25_G1
GO

/*******************************************************************************
	Drop tables if exists
*******************************************************************************/
DECLARE @sql nvarchar(MAX) 
SET @sql = N'' 

SELECT @sql = @sql + N'ALTER TABLE ' + QUOTENAME(KCU1.TABLE_SCHEMA) 
    + N'.' + QUOTENAME(KCU1.TABLE_NAME)
    + N' DROP CONSTRAINT ' -- + QUOTENAME(rc.CONSTRAINT_SCHEMA)  + N'.'  -- not in MS-SQL
    + QUOTENAME(rc.CONSTRAINT_NAME) + N'; ' + CHAR(13) + CHAR(10) 
FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS AS RC 

INNER JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS KCU1 
    ON KCU1.CONSTRAINT_CATALOG = RC.CONSTRAINT_CATALOG  
    AND KCU1.CONSTRAINT_SCHEMA = RC.CONSTRAINT_SCHEMA 
    AND KCU1.CONSTRAINT_NAME = RC.CONSTRAINT_NAME 

EXECUTE(@sql) 

GO
DECLARE @sql2 NVARCHAR(max)=''

SELECT @sql2 += ' Drop table ' + QUOTENAME(TABLE_SCHEMA) + '.'+ QUOTENAME(TABLE_NAME) + '; '
FROM   INFORMATION_SCHEMA.TABLES
WHERE  TABLE_TYPE = 'BASE TABLE'

Exec Sp_executesql @sql2
CREATE TABLE [users] (
  [id] integer identity(1,1) PRIMARY KEY,
  [email] nvarchar(255) unique,
  [password] nvarchar(255),
  [fullname] nvarchar(255),
  [role] nvarchar(255)
)
GO

CREATE TABLE [category] (
  [id] integer identity(1,1) PRIMARY KEY,
  [name] nvarchar(255) unique,
  [status] nvarchar(225) Null
)
GO

CREATE TABLE [product] (
  [id] integer identity(1,1) PRIMARY KEY,
  [name] nvarchar(255),
  [price] float,
  [description] nvarchar(255),
  [image] nvarchar(255),
  [status] nvarchar(225) Null,
  [categoryId] integer
)
GO

CREATE TABLE [order] (
  [id] integer identity(1,1) PRIMARY KEY,
  [userId] integer,
  [address] nvarchar(255),
  [paymentMethod] nvarchar(255),
  [paymentStatus] nvarchar(255),
  [shippingStatus] nvarchar(255),
  [status] nvarchar(225) Null,
  [amount] float
)
GO

CREATE TABLE [orderDetail] (
  [id] integer identity(1,1) PRIMARY KEY,
  [orderId] integer,
  [productId] integer,
  [quantity] integer
)
GO

ALTER TABLE [order] ADD FOREIGN KEY ([userId]) REFERENCES [users] ([id])
GO

ALTER TABLE [product] ADD FOREIGN KEY ([categoryId]) REFERENCES [category] ([id])
GO

ALTER TABLE [orderDetail] ADD FOREIGN KEY ([orderId]) REFERENCES [order] ([id])
GO

ALTER TABLE [orderDetail] ADD FOREIGN KEY ([orderId]) REFERENCES [product] ([id])
GO


-- Insert into users
INSERT INTO [users] ([email], [password], [fullname], [role]) VALUES
('admin@example.com', 'admin123', 'Administrator', 'Admin'),
('staff@example.com', 'staff123', 'Staff Member', 'Staff'),
('customer1@example.com', 'cust123', 'Customer One', 'Customer'),
('customer2@example.com', 'cust123', 'Customer Two', 'Customer');

-- Insert into category
INSERT INTO [category] ([name], [status]) VALUES
('Electronics', 'Active'),
('Clothing', 'Active'),
('Books', 'Inactive'),
('Home Appliances', 'Active');

-- Insert into product
INSERT INTO [product] ([name], [price], [description], [image], [status], [categoryId]) VALUES
('Laptop', 999.99, 'High-performance laptop', 'laptop.jpg', 'Available', 1),
('Smartphone', 599.99, 'Latest model smartphone', 'smartphone.jpg', 'Available', 1),
('T-shirt', 19.99, 'Cotton T-shirt', 'tshirt.jpg', 'Available', 2),
('Cookbook', 14.99, 'Healthy recipes cookbook', 'cookbook.jpg', 'Out of Stock', 3),
('Microwave', 89.99, 'Compact microwave oven', 'microwave.jpg', 'Available', 4);

-- Insert into order
INSERT INTO [order] ([userId], [address], [paymentMethod], [paymentStatus], [shippingStatus], [status], [amount]) VALUES
(3, '123 Main St, City A', 'Credit Card', 'Paid', 'Shipped', 'Completed', 1019.98),
(4, '456 Oak St, City B', 'PayPal', 'Pending', 'Processing', 'Pending', 34.98);

-- Insert into orderDetail
INSERT INTO [orderDetail] ([orderId], [productId], [quantity]) VALUES
(1, 1, 1), -- 1 Laptop
(1, 3, 1), -- 1 T-shirt
(2, 4, 2); -- 2 Cookbooks

select * from category