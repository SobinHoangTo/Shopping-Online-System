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
            <div class="container mt-5">
                <h2>Order Detail</h2><br/>
                <div class="row mb-3">
                    <div class="col-md-5">
                        <h5 class="card-text">Customer: ${customer}</h5>
                        <p class="card-text"><strong>Address:</strong> ${orderInfor.address}</p>
                        <p class="card-text"><strong>Phone:</strong> ${orderInfor.phone}</p>
                    </div>
                    <div class="col-md-3">
                        <p class="card-text"><strong>Order date: </strong>${orderInfor.order_date}</p>
                        <p class="card-text"><strong>Paid date: </strong>${orderInfor.paid_date}</p>
                        <strong>Status: </strong>
                        <c:if test="${o.order_status == 1}">
                            <p class="badge bg-info">Pending</p>
                        </c:if>
                        <c:if test="${orderInfor.order_status == 2}">
                            <p class="badge bg-danger">Rejected</p>
                        </c:if>
                        <c:if test="${orderInfor.order_status == 3}">
                            <p class="badge bg-primary">Packaging</p>
                        </c:if>
                        <c:if test="${orderInfor.order_status == 4}">
                            <p class="badge bg-warning">Shipping</p>
                        </c:if>
                        <c:if test="${orderInfor.order_status == 5}">
                            <p class="badge bg-success">Received</p>
                        </c:if>
                        <c:if test="${orderInfor.order_status == 6}">
                            <p class="badge bg-dark">Return goods</p>
                        </c:if>
                        <c:if test="${orderInfor.order_status == 7}">
                            <p class="badge bg-secondary">Receive goods back</p>
                        </c:if>
                    </div>
                    <div class="col-md-4">
                        <p class="card-text"><strong>Note: </strong>${orderInfor.note}</p>
                    </div>
                </div>
                <c:forEach items="${listProductOrder}" var="po">
                    <c:forEach items="${listProduct}" var="p">
                        <c:if test="${po.product_id == p.id}">
                            <div class="card mb-3">

                                <div class="card-body">
                                    <div class="row mb-3">
                                        <div class="col-md-2">
                                            <a href="productdetails?id=${p.id}">
                                                <img src="<%=request.getContextPath()%>/Image/Products/${p.representImage}" class="img-fluid" alt="Product Image">
                                            </a>
                                        </div>
                                        <div class="col-md-6">
                                            <h5 class="card-title"><a href="productdetails?id=${p.id}">${p.name}</a></h5>
                                            <p class="card-text">ID : ${p.id}</p>
                                            <p class="card-text"><small class="text-muted">SERI: ${po.serial_number_id}</small></p>
                                        </div>
                                        <div class="col-md-2">
                                            <span class="badge bg-danger">15 days return</span>
                                        </div>
                                        <div class="col-md-2 text-end">
                                            <p class="card-text">₫<fmt:formatNumber value="${p.price}" type='number' groupingUsed='true'/></p>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
                <!--footer-->
                <%@ include file="../footer.jsp" %>
            </div>
        </section>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
