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

CREATE TABLE [categories] (
  [id] integer identity(1,1) PRIMARY KEY,
  [name] nvarchar(255) unique,
  [status] nvarchar(225) Null
)
GO

CREATE TABLE [products] (
  [id] integer identity(1,1) PRIMARY KEY,
  [name] nvarchar(255),
  [price] float,
  [description] nvarchar(255),
  [image] nvarchar(255),
  [status] nvarchar(225) Null,
  [categoryId] integer
)
GO

CREATE TABLE [orders] (
  [id] integer identity(1,1) PRIMARY KEY,
  [userId] integer Null,
  [address] nvarchar(255),
  [phone] varchar(15),
  [name] nvarchar(225),
  [paymentMethod] nvarchar(255),
  [paymentStatus] nvarchar(255),
  [shippingStatus] nvarchar(255),
  [status] nvarchar(225) Null,
  [trackingNumber] varchar(50),
  [amount] float
)
GO

CREATE TABLE [orderDetails] (
  [id] integer identity(1,1) PRIMARY KEY,
  [orderId] integer,
  [productId] integer,
  [quantity] integer
)
GO

ALTER TABLE [orders] ADD FOREIGN KEY ([userId]) REFERENCES [users] ([id])
GO

ALTER TABLE [products] ADD FOREIGN KEY ([categoryId]) REFERENCES [categories] ([id])
GO

ALTER TABLE [orderDetails] ADD FOREIGN KEY ([orderId]) REFERENCES [orders] ([id])
GO

ALTER TABLE [orderDetails] ADD FOREIGN KEY ([productId]) REFERENCES [products] ([id])
GO
