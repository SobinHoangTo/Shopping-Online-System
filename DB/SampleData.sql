USE SWD392_SE1820_SP25_G1
GO

-- Insert Users
INSERT INTO [users] ([email], [password], [fullname], [role])
VALUES 
('admin@example.com', 'hashed_password1', 'Admin User', 'ADMIN'),
('staff@example.com', 'hashed_password2', 'Staff Member', 'STAFF'),
('customer1@example.com', 'hashed_password3', 'Customer One', 'CUSTOMER'),
('customer2@example.com', 'hashed_password4', 'Customer Two', 'CUSTOMER');

-- Insert Categories
INSERT INTO [categories] ([name], [status])
VALUES 
('Electronics', 'ACTIVE'),
('Books', 'ACTIVE'),
('Clothing', 'ACTIVE'),
('Home Appliances', 'ACTIVE');

-- Insert Products
INSERT INTO [products] ([name], [price], [description], [image], [status], [categoryId])
VALUES 
('Smartphone X', 999.99, 'High-end smartphone', 'image1.jpg', 'ACTIVE', 1),
('Laptop Pro', 1499.99, 'Powerful business laptop', 'image2.jpg', 'ACTIVE', 1),
('The Great Gatsby', 15.99, 'Classic novel', 'image3.jpg', 'ACTIVE', 2),
('Jeans Slim Fit', 39.99, 'Stylish jeans', 'image4.jpg', 'ACTIVE', 3),
('Washing Machine 3000', 499.99, 'Automatic washing machine', 'image5.jpg', 'ACTIVE', 4);

-- Insert Orders
INSERT INTO [orders] ([userId], [address], [phone], [name], [paymentMethod], [paymentStatus], [shippingStatus], [status], [trackingNumber], [amount])
VALUES 
(3, '123 Main St', '0123456789', 'Customer One', 'Credit Card', 'Paid', 'Shipped', 'Completed', 'LPAYVU', 1015.98),
(4, '456 Elm St', '0987654321', 'Customer Two', 'PayPal', 'Pending', 'Processing', 'Pending', 'LBLQRU', 1499.99);

-- Insert OrderDetails
INSERT INTO [orderDetails] ([orderId], [productId], [quantity])
VALUES 
(1, 1, 1), -- 1 Smartphone X
(1, 3, 1), -- 1 The Great Gatsby
(2, 2, 1); -- 1 Laptop Pro
