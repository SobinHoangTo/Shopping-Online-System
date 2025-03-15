<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
        <style>
            body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    min-height: 100vh;
    background-color: #f4f4f4;
}

.container {
    width: 80%;
    max-width: 1200px;
    display: flex;
    justify-content: space-between;
    background-color: white;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    margin-top: 20px;
    gap: 20px; /* Thêm khoảng cách giữa hai cột */
}

.form-left, .form-right {
    width: 48%;
}

.form-left {
    display: flex;
    flex-direction: column;
}

.form-right {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

label {
    display: block;
    margin: 8px 0 5px;
    font-weight: bold;
}

input[type="text"], input[type="file"], textarea, select {
    width: 100%;
    padding: 10px;
    margin: 5px 0 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    font-size: 14px;
}

textarea {
    height: 120px;
    resize: vertical;
}

.image-box {
    width: 100%;
    height: 250px;
    border: 1px solid #ddd;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    margin-bottom: 15px;
    background-color: #f4f4f4;
}

.image-box img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
    gap: 10px; /* Khoảng cách giữa các nút */
}

button {
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    width: 48%; /* Cả hai nút đều chiếm 48% chiều rộng */
}

button:hover {
    background-color: #45a049;
}

.button-discard {
    background-color: #f44336;
}

.button-discard:hover {
    background-color: #e60000;
}

        </style>
    </head>
    <body>
        <div class="container">
            <form action="ProductServlet" method="POST" enctype="multipart/form-data">
                <div class="form-left">
                    <h2>Edit Product</h2>
                    <input type="hidden" name="productId" value="${product.id != null ? product.id : '4'}">

                    <label for="productName">Product Name:</label>
                    <input type="text" id="productName" name="productName" value="${product.name}" required>

                    <label for="category">Category:</label>
                    <select id="category" name="category" required>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}" ${category.id == product.categoryId ? 'selected' : ''}>${category.name}</option>
                        </c:forEach>
                    </select>

                    <label for="price">Price:</label>
                    <input type="text" id="price" name="price" value="${product.price}" required pattern="[0-9]+(\.[0-9]{1,2})?" title="Enter a valid price (e.g., 10 or 10.99)">

                    <label for="status">Status:</label>
                    <select id="status" name="status" required>
                        <option value="active" ${product.status == 'active' ? 'selected' : ''}>Active</option>
                        <option value="inactive" ${product.status == 'inactive' ? 'selected' : ''}>Inactive</option>
                    </select>

                    <label for="description">Description:</label>
                    <textarea id="description" name="description">${product.description}</textarea>
                </div>

                <div class="form-right">
                    <div class="image-box">
                        <img id="previewImage" src="${product.image != null ? product.image : 'https://via.placeholder.com/365'}" alt="Product Image">
                    </div>
                    <input type="file" name="productImages" id="productImages" accept="image/*" onchange="previewImage(event)">
                </div>

                <div class="buttons">
                    <button type="submit" name="action" value="update">Save Changes</button>
                    <button type="reset" class="button-discard">Discard</button>
                </div>
            </form>
        </div>

        <script>
            function previewImage(event) {
                const file = event.target.files[0];
                const preview = document.getElementById('previewImage');
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        preview.src = e.target.result;
                    };
                    reader.readAsDataURL(file);
                }
            }
        </script>
    </body>
</html>