<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h1>Product List</h1>
    
    
    <!-- Add New Product Button -->
    <form action="ProductServlet" method="get">
        <button type="submit">Add New Product</button>
    </form>
    
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Image</th>
                <th>Status</th>
                <th>Category</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>          
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td><img src="${product.image}" alt="Image" width="50"/></td>
                    <td>${product.status}</td>
                    <td>${product.categoryId}</td>
                    <td>
                        <!-- Nút Update sẽ chuyển hướng đến trang Product với productId -->
                        <a href="ProductServlet?productId=${product.id}">Update</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
