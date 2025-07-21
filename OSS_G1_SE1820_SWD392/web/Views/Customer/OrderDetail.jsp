<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Order List</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <link href="https://fonts.googleapis.com/css2?family=Spectral:wght@200;300;400;500;700;800&display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/animate.css" />

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/owl.carousel.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/owl.theme.default.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/magnific-popup.css" />

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/flaticon.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/style.css" />
    </head>
    <body>

        <!-- Header -->
        <%@ include file="../header.jsp" %>

        <!-- Hero Section -->
        <section class="hero-wrap hero-wrap-2" style="background-image: url('<%=request.getContextPath()%>/Images/bg_2.jpg');" data-stellar-background-ratio="0.5">
            <div class="overlay"></div>
            <div class="container">
                <div class="row no-gutters slider-text align-items-end justify-content-center">
                    <div class="col-md-9 ftco-animate mb-5 text-center">
                        <p class="breadcrumbs mb-0">
                            <span class="mr-2"><a href="<%=request.getContextPath()%>/home">Home <i class="fa fa-chevron-right"></i></a></span>
                            <span>Order List <i class="fa fa-chevron-right"></i></span>
                        </p>
                        <h2 class="mb-0 bread">Order List</h2>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-section">
            <div class="container mt-5 p-4 bg-white rounded shadow">

                <!-- Back + Order Code -->
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <a href="orderList" class="text-muted"><i class="fa fa-chevron-left"></i> BACK</a>
                    <div>
                        <span class="text-primary">GHN EXPRESS: ${orderInfor.trackingNumber}</span> |
                        <span class="text-danger">${orderInfor.shippingStatus}</span>
                    </div>
                </div>

                <!-- Timeline -->
                <div class="d-flex justify-content-between text-center align-items-center mb-4">
                    <c:set var="timelineSteps" value="${[
                                                        {'icon':'fa-file-text-o','label':'Đơn Hàng Đã Đặt','time':'01:08 09-03-2025'},
                                                        {'icon':'fa-money','label':'Đã Thanh Toán','time':'01:08 09-03-2025','price':'₫49.480'},
                                                        {'icon':'fa-truck','label':'Đã Giao Cho ĐVVC','time':'17:00 09-03-2025'},
                                                        {'icon':'fa-check-square','label':'Đã Nhận Hàng','time':'03:11 11-03-2025'},
                                                        {'icon':'fa-star','label':'Đã Đánh Giá','time':'03:16 11-03-2025'}
                                                        ]}"/>
                    <c:forEach items="${timelineSteps}" var="step">
                        <div style="flex:1;">
                            <div class="rounded-circle bg-success mx-auto mb-2 d-flex justify-content-center align-items-center" style="width:60px; height:60px;">
                                <i class="fa ${step.icon} text-white" style="font-size: 24px;"></i>
                            </div>
                            <div>${step.label}<br/><small>${step.time}</small>
                                <c:if test="${not empty step.price}"><br/><strong>${step.price}</strong></c:if>
                                </div>
                            </div>
                    </c:forEach>
                </div>

                <hr/>

                <!-- Order Detail -->
                <h2>Order Detail</h2><br/>
                <div class="row mb-3">
                    <div class="col-md-5">
                        <h5 class="card-text">Customer Name: ${receiverName}</h5>
                        <p class="card-text"><strong>Address:</strong> ${receiverAddress}</p>
                        <p class="card-text"><strong>Phone:</strong> ${receiverPhone}</p>
                    </div>
                    <div class="col-md-3">
                        <p class="card-text"><strong>Payment Method: </strong>${orderInfor.paymentMethod}</p>
                        <p class="card-text"><strong>Payment Status: </strong>${orderInfor.paymentStatus}</p>
                        <p class="card-text" ><strong>Status: </strong>${deliveryStatus}</p>
                    </div>
                    <div class="col-md-4">

                        <h3 class="text-end text-danger ">Total: ${orderInfor.amount}VND</h3>
                    </div>
                </div>

                <!-- Product List -->
                <c:forEach items="${listProduct}" var="p">
                    <div class="card mb-3">

                        <div class="card-body">
                            <div class="row mb-3">
                                <div class="col-md-2">
                                    <a href="#">
                                        <img src="${p.image}" class="img-fluid" alt="${p.name}">
                                    </a>
                                </div>
                                <div class="col-md-6">
                                    <h5 class="card-title"><a href="#">${p.name}</a></h5>
                                    <p class="card-text"><small class="text-muted">ID : ${p.id}</small></p>
                                </div>
                                <div class="col-md-2 text-end">
                                    <h5 class="card-text text-danger"><fmt:formatNumber value="${p.price}" type='number' groupingUsed='true'/> VND</h5>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <!-- Footer -->
        <%@ include file="../footer.jsp" %>

    </body>
</html>
