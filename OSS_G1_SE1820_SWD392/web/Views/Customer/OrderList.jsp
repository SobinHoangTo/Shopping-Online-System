<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Order List</title>
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

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/flaticon.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/style.css" />
    </head>
    <body>

        <!--header-->
        <%@ include file="../header.jsp" %>

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
                            <span>Order List <i class="fa fa-chevron-right"></i></span>
                        </p>
                        <h2 class="mb-0 bread">Order List</h2>
                    </div>
                </div>
            </div>
        </section>
        <section class="ftco-section">
            <div class="container">
                <h2>Order List</h2>
                <div class="row">
                    <div class="table-wrap">
                        <table class="table table-hover">
                            <thead class="thead-primary">
                                <tr>
                                    <th>Tracking Number</th>
                                    <th>Address</th>
                                    <th>Payment Method</th>
                                    <th>Payment Status</th>
                                    <th>Shipping Status</th>
                                    <th>Status</th>
                                    <th>Total Price</th>
                                    <th>View Detail</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty listOrder}">
                                    <tr>
                                        <td colspan="8" class="text-center">No Orders Found</td>
                                    </tr>
                                </c:if>
                                <c:forEach items="${listOrder}" var="o">
                                    <tr class="alert cursor-pointer" role="alert" onclick="moveToDetail(${o.id})">
                                        <td>${o.trackingNumber}</td>
                                        <td>${o.address}</td>
                                        <td>${o.paymentMethod}</td>
                                        <td>${o.paymentStatus}</td>
                                        <td>${o.shippingStatus}</td>
                                        <td>${o.status}</td>
                                        <td><fmt:formatNumber value="${o.amount}" type='number' groupingUsed='true'/> VND</td>
                                        <td>
                                            <a href="orderDetail?id=${o.id}" class="btn btn-primary py-2 px-3">
                                                View Detail <i class="bi bi-info-circle-fill"></i>
                                            </a>
                                        </td>
                                    </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>

        <!--footer-->
        <%@include file="../footer.jsp" %>
        <script>
            function moveToDetail(id) {
                window.location.href = "orderDetail?id=" + id;
            }
        </script>
    </body>
</html>
