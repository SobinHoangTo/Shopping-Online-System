<%-- 
    Document   : cart
    Created on : Mar 15, 2025, 12:34:27 PM
    Author     : vdqvi
--%>
<%@page import="org.json.JSONObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Cart</title>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />

        <link
            href="https://fonts.googleapis.com/css2?family=Spectral:ital,wght@0,200;0,300;0,400;0,500;0,700;0,800;1,200;1,300;1,400;1,500;1,700&display=swap"
            rel="stylesheet"
            />

        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
            />

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/animate.css" />

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/owl.carousel.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/owl.theme.default.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/magnific-popup.css" />

        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css"
            />

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/flaticon.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/style.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>

        <section
            class="hero-wrap hero-wrap-2"
            style="background-image: url('<%=request.getContextPath()%>/Images/bg_2.jpg')"
            data-stellar-background-ratio="0.5"
            >
            <div class="overlay"></div>
            <div class="container">
                <div
                    class="row no-gutters slider-text align-items-end justify-content-center"
                    >
                    <div class="col-md-9 ftco-animate mb-5 text-center">
                        <p class="breadcrumbs mb-0">
                            <span class="mr-2"
                                  ><a href="<%=request.getContextPath()%>/home"
                                >Home <i class="fa fa-chevron-right"></i></a
                                ></span>
                            <span>Cart <i class="fa fa-chevron-right"></i></span>
                        </p>
                        <h2 class="mb-0 bread">My Cart</h2>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-section">
            <div class="container">
                <div class="row">
                    <div class="table-wrap">
                        <table class="table">
                            <thead class="thead-primary">
                                <tr>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody id="cartItems">
                                <c:forEach var="product" items="${products}">
                                    <tr class="alert" role="alert">
                                        <td>
                                            <label class="checkbox-wrap checkbox-primary">
                                                <input type="checkbox" id="${product.id}_checkbox" name="selectCheckBox" onclick="selectProduct(${product.id}, ${product.price})" />
                                                <span class="checkmark"></span>
                                            </label>
                                        </td>
                                        <td>
                                            <div
                                                class="img"
                                                style="background-image: url(${product.image})"
                                                ></div>
                                        </td>
                                        <td>
                                            <div class="email">
                                                <span>${product.name}</span>
                                                <span></span>
                                            </div>
                                        </td>
                                        <td>${product.price} VNĐ</td>
                                        <td class="quantity">
                                            <div class="input-group">
                                                <input
                                                    type="text"
                                                    id="${product.id}_quantity"
                                                    name="quantity"
                                                    class="quantity form-control input-number"
                                                    value="${cartItems.get(product.id)}"
                                                    onchange="updateQuantity(${product.id}, ${product.price})"
                                                    min="1"
                                                    max="100"
                                                    />
                                            </div>
                                        </td>
                                        <td id="${product.id}_totalPrice">${cartItems.get(product.id) * product.price} VNĐ</td>
                                        <td>
                                            <button
                                                type="button"
                                                class="close"
                                                data-dismiss="alert"
                                                aria-label="Close"
                                                onclick="removeProduct(${product.id}, ${product.price})"
                                                >
                                                <span aria-hidden="true"
                                                      ><i class="fa fa-close"></i
                                                    ></span>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row justify-content-end">
                    <div class="col col-lg-5 col-md-6 mt-5 cart-wrap ftco-animate">
                        <div class="cart-total mb-3">
                            <h3>Cart Totals</h3>
                            <hr />
                            <p class="d-flex total-price">
                                <span>Total</span>
                                <span id="totalPrice">0 VNĐ</span>
                            </p>
                        </div>
                        <p class="text-center">
                            <a onclick="redirectToOrder()" class="btn btn-primary py-3 px-4"
                               >Proceed to Order</a
                            >
                        </p>
                    </div>
                </div>
            </div>
        </section>

        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen">
            <svg class="circular" width="48px" height="48px">
            <circle
                class="path-bg"
                cx="24"
                cy="24"
                r="22"
                fill="none"
                stroke-width="4"
                stroke="#eeeeee"
                />
            <circle
                class="path"
                cx="24"
                cy="24"
                r="22"
                fill="none"
                stroke-width="4"
                stroke-miterlimit="10"
                stroke="#F96D00"
                />
            </svg>
        </div>
        <%@include file="footer.jsp" %>
        <script>
            let totalPrice = 0;

            function getCartItems() {
                let cartItems = getCookie("cartItems");
                return cartItems ? JSON.parse(cartItems) : {};
            }

            function updateQuantity(id, price) {
                let quantityInput = document.getElementById(id + "_quantity");
                let newQuantity = parseInt(quantityInput.value);

                if (isNaN(newQuantity) || newQuantity <= 0 || newQuantity >= 100) {
                    quantityInput.value = getCartItems()[id] || 1;
                    alert("Invalid quantity!!!");
                    return;
                }

                let cartItems = getCartItems();
                let previousQuantity = cartItems[id] || 0;
                cartItems[id] = newQuantity;
                setCookie("cartItems", JSON.stringify(cartItems), 7);

                if (document.getElementById(id + "_checkbox").checked) {
                    totalPrice += (newQuantity - previousQuantity) * price;
                }

                document.getElementById(id + "_totalPrice").innerHTML = "$" + newQuantity * price;
                document.getElementById("totalPrice").innerHTML = totalPrice + " VNĐ";

                displayCartLength();
                alert("Quantity updated successfully.");
            }

            function removeProduct(id, price) {
                let cartItems = getCartItems();
                let previousQuantity = cartItems[id] || 0;

                if (document.getElementById(id + "_checkbox").checked) {
                    totalPrice -= previousQuantity * price;
                }

                delete cartItems[id];
                setCookie("cartItems", JSON.stringify(cartItems), 7);

                document.getElementById("totalPrice").innerHTML = totalPrice + " VNĐ";
                displayCartLength();
                alert("Product removed successfully.");
            }

            function selectProduct(id, price) {
                let cartItems = getCartItems();
                let priceChange = (cartItems[id] || 0) * price;

                totalPrice += document.getElementById(id + "_checkbox").checked ? priceChange : -priceChange;
                document.getElementById("totalPrice").innerHTML = totalPrice + " VNĐ";
            }

            function redirectToOrder() {
                let checkboxes = document.getElementsByName("selectCheckBox");
                let checkedBoxes = [];
                for (let checkbox of checkboxes) {
                    if (checkbox.checked) {
                        checkedBoxes.push(checkbox.id.split("_")[0]);
                    }
                }

                if (checkedBoxes.length === 0) {
                    alert("Please select at least 1 product to order!");
                    return;
                }

                let form = document.createElement("form");
                form.method = "POST";
                form.action = "order-confirm";

                for (let checkedBox of checkedBoxes) {
                    let input = document.createElement("input");
                    input.type = "hidden";
                    input.name = "productIds";
                    input.value = checkedBox;
                    form.appendChild(input);
                }

                document.body.appendChild(form);
                form.submit();
            }
        </script>
    </body>
</html>

