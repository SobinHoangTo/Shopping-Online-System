<%-- 
    Document   : header
    Created on : Mar 8, 2025, 1:12:12 AM
    Author     : vdqvi
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="wrap">
    <div class="container">
        <div class="row">
            <div class="col-md-6 d-flex align-items-center">
                <p class="mb-0 phone pl-md-2">
                    <a href="#" class="mr-2"
                       ><span class="fa fa-phone mr-1"></span> +00 1234 567</a
                    >
                    <a href="#"
                       ><span class="fa fa-paper-plane mr-1"></span>
                        youremail@email.com</a
                    >
                </p>
            </div>
            <div class="col-md-6 d-flex justify-content-md-end">
                <div class="social-media mr-4">
                    <p class="mb-0 d-flex">
                        <a
                            href="#"
                            class="d-flex align-items-center justify-content-center"
                            ><span class="fa fa-facebook"
                               ><i class="sr-only">Facebook</i></span
                            ></a
                        >
                        <a
                            href="#"
                            class="d-flex align-items-center justify-content-center"
                            ><span class="fa fa-twitter"
                               ><i class="sr-only">Twitter</i></span
                            ></a
                        >
                        <a
                            href="#"
                            class="d-flex align-items-center justify-content-center"
                            ><span class="fa fa-instagram"
                               ><i class="sr-only">Instagram</i></span
                            ></a
                        >
                    </p>
                </div>
                <div class="reg">
                    <p class="mb-0">
                    <c:if test="${sessionScope.User == null}">
                        <a class="navbar-brand" href="http://localhost:9999/OSS_G1_SE1820_SWD392/Views/login.jsp">LOG IN/SIGN UP</a>
                    </c:if>
                    <c:if test="${sessionScope.User != null}">
                        <a class="navbar-brand" href="http://localhost:9999/OSS_G1_SE1820_SWD392/logout">LOG OUT</a>
                    </c:if>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<nav
    class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
    id="ftco-navbar"
    >
    <div class="container">
        <a class="navbar-brand" href="index.html">Liquor <span>store</span></a>
        <div class="order-lg-last btn-group">
            <a
                href="#"
                class="btn-cart dropdown-toggle dropdown-toggle-split"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
                >
                <span class="flaticon-shopping-bag"></span>
                <div id="cartLength" class="d-flex justify-content-center align-items-center">
                    <small>0</small>
                </div>
            </a>

            <a href="product-single.html">
                <div class="dropdown-menu dropdown-menu-right">
                    <div class="dropdown-item d-flex align-items-start" href="#">
                        <div
                            class="img"
                            style="background-image: url(<%=request.getContextPath()%>/Images/prod-1.jpg)"
                            ></div>
                        <div class="text pl-3">
                            <h4>Bacardi 151</h4>
                            <p class="mb-0">
                                <a href="#" class="price">$25.99</a>
                                <span class="quantity ml-3">Quantity: 01</span>
                            </p>
                        </div>
<%-- Document : header Created on : Mar 8, 2025, 1:12:12 AM Author : vdqvi --%>

    <div class="wrap">
        <div class="container">
            <div class="row">
                <div class="col-md-6 d-flex align-items-center">
                    <p class="mb-0 phone pl-md-2">
                        <a href="#" class="mr-2"><span class="fa fa-phone mr-1"></span> +00 1234 567</a>
                        <a href="#"><span class="fa fa-paper-plane mr-1"></span>
                            youremail@email.com</a>
                    </p>
                </div>
                <div class="col-md-6 d-flex justify-content-md-end">
                    <div class="social-media mr-4">
                        <p class="mb-0 d-flex">
                            <a href="#" class="d-flex align-items-center justify-content-center"><span
                                    class="fa fa-facebook"><i class="sr-only">Facebook</i></span></a>
                            <a href="#" class="d-flex align-items-center justify-content-center"><span
                                    class="fa fa-twitter"><i class="sr-only">Twitter</i></span></a>
                            <a href="#" class="d-flex align-items-center justify-content-center"><span
                                    class="fa fa-instagram"><i class="sr-only">Instagram</i></span></a>
                        </p>
                    </div>
                    <div class="reg">
                        <p class="mb-0">
                            <a href="#" class="mr-2">Sign Up</a> <a href="#">Log In</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
        <div class="container">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/home">OSS <span>Group1</span></a>
            <div class="order-lg-last btn-group">
                <a href="<%=request.getContextPath()%>/cart" class="btn-cart dropdown-toggle dropdown-toggle-split"
                    aria-haspopup="true" aria-expanded="false">
                    <span class="flaticon-shopping-bag"></span>
                    <div id="cartLength" class="d-flex justify-content-center align-items-center">
                        <small>0</small>
                    </div>
                </a>
            </div>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
                aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="oi oi-menu"></span> Menu
            </button>

            <div class="collapse navbar-collapse" id="ftco-nav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/home" class="nav-link">Home</a>
                    </li>
                    <li class="nav-item">
                        <a href="about.html" class="nav-link">About</a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/products" class="nav-link">Products</a>
                    </li>
                    <li class="nav-item">
                        <a href="preorder.html" class="nav-link">Your Orders</a>
                    </li>

                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/orderList" class="nav-link">Order List</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- END nav -->