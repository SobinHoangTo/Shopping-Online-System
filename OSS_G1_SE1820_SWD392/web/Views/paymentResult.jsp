<%-- 
    Document   : paymentResult
    Created on : 16 thg 3, 2025, 18:56:45
    Author     : asus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Liquor Store - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css2?family=Spectral:ital,wght@0,200;0,300;0,400;0,500;0,700;0,800;1,200;1,300;1,400;1,500;1,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/animate.css">

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/magnific-popup.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">

        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/flaticon.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/style.css">
    </head>

<body class="bg-light">
    <%@include file="header.jsp" %>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center">
                <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:150:0/q:90/plain/https://cellphones.com.vn/media/wysiwyg/Review-empty.png" alt="Transaction Status" class="mb-4" style="width: 120px; height: 120px;">

                <c:choose>
                    <c:when test="${transResult}">
                        <h3 class="text-success font-weight-bold">Bạn đã giao dịch thành công! <i class="fas fa-check-circle"></i></h3>
                        <p class="mt-3">Cảm ơn quý khách đã dùng dịch vụ của chúng tôi.</p>
                    </c:when>

                    <c:when test="${transResult == false}">
                        <h3 class="text-danger font-weight-bold">Đơn hàng giao dịch thất bại!</h3>
                        
                        <p>Liên hệ tổng đài để được tư vấn:</p>
                        <strong class="text-danger" style="font-size: 24px;">0356879558</strong>
                    </c:when>

                    <c:otherwise>
                        <h3 class="text-warning font-weight-bold">Chúng tôi đã tiếp nhận đơn hàng, xin chờ quá trình xử lý!</h3>
                        <p class="mt-3">Vui lòng để ý số điện thoại của nhân viên tư vấn:</p>
                        <strong class="text-danger" style="font-size: 24px;">0356879558</strong>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>