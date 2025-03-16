<%-- 
    Document   : order
    Created on : Mar 16, 2025, 12:06:40 AM
    Author     : vdqvi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.Enums.PaymentMethod"%>
<%@page import="config.GHNConfig"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <body>
        <%@include file="header.jsp" %>

        <section class="hero-wrap hero-wrap-2" style="background-image: url('<%=request.getContextPath()%>/Images/bg_2.jpg');" data-stellar-background-ratio="0.5">
            <div class="overlay"></div>
            <div class="container">
                <div class="row no-gutters slider-text align-items-end justify-content-center">
                    <div class="col-md-9 ftco-animate mb-5 text-center">
                        <p class="breadcrumbs mb-0"><span class="mr-2"><a href="index.html">Home <i class="fa fa-chevron-right"></i></a></span> <span>Checkout <i class="fa fa-chevron-right"></i></span></p>
                        <h2 class="mb-0 bread">Checkout</h2>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-10 ftco-animate">
                        <form action="order" method="post" class="billing-form">
                            <c:forEach var="pId" items="${productIds}">
                                <input type="hidden" value="${pId}" name="productIds"/>
                            </c:forEach>
                            <h3 class="mb-4 billing-heading">Billing Details</h3>
                            <div class="row align-items-end">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="fullname">Full Name</label>
                                        <input type="text" required name="name" class="form-control" placeholder="">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="phone">Phone</label>
                                        <input type="text" name="phone" required class="form-control" placeholder="">
                                    </div>
                                </div>
                                <!--                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <label for="email">Email Address</label>
                                                                        <input type="text" class="form-control" placeholder="">
                                                                    </div>
                                                                </div>-->

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="province">Province</label>
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                            <select required id="province" class="form-control" onchange="updateDistrictOption()">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="district">District</label>
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                            <select name="district" required id="district" class="form-control" onchange="updateWardOption()">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="ward">Ward</label>
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                            <select name="ward" required id="ward" class="form-control" onchange="onWardChange()">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="streetaddress">Street Address</label>
                                        <input type="text" id="subAddress" class="form-control" onblur="updateAddress()" placeholder="House number and street name">
                                        <input type="hidden" name="address" id="address" value="">
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-5 pt-3 d-flex">
                                <div class="col-md-6 d-flex">
                                    <div class="cart-detail cart-total p-3 p-md-4">
                                        <h3 class="billing-heading mb-4">Cart Total</h3>
                                        <p class="d-flex">
                                            <span>Subtotal</span>
                                            <span>${totalPrice} VNĐ</span>
                                        </p>
                                        <p class="d-flex">
                                            <span>Delivery</span>
                                            <span id="shippingFee">0 VNĐ</span>
                                        </p>
                                        <hr>
                                        <p class="d-flex total-price">
                                            <span>Total</span>
                                            <span id="totalFee">${totalPrice} VNĐ</span>
                                            <input type="hidden" value="" name="totalPrice" id="totalPrice">
                                        </p>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="cart-detail p-3 p-md-4">
                                        <h3 class="billing-heading mb-4">Payment Method</h3>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="radio">
                                                    <label><input type="radio" name="paymentMethod" value="<%=PaymentMethod.BANK_TRANSFER%>" class="mr-2"> Direct Bank Tranfer</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="radio">
                                                    <label><input type="radio" name="paymentMethod" value="<%=PaymentMethod.ONLINE_PAYMENT%>" class="mr-2"> Online Payment</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="radio">
                                                    <label><input type="radio" name="paymentMethod" value="<%=PaymentMethod.COD%>" class="mr-2"> COD</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <div class="checkbox">
                                                    <label><input type="checkbox" required="" value="" class="mr-2"> I have read and accept the terms and conditions</label>
                                                </div>
                                            </div>
                                        </div>
                                        <p><input type="submit" class="btn btn-primary py-3 px-4" value="Place an order"/></p>
                                    </div>
                                </div>
                            </div>
                        </form><!-- END -->

                    </div> <!-- .col-md-8 -->
                </div>
            </div>


            <!-- loader -->
            <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>

            <%@include file="footer.jsp" %>
            <script>
                fetch("<%=GHNConfig.GET_PROVINCE_URL%>", {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Token": "<%=GHNConfig.TOKEN_API%>"
                    }
                })
                        .then(response => response.json())
                        .then(data => {
                            let provinceSelect = document.getElementById("province");
                            provinceSelect.innerHTML = "";

                            if (data.code === 200 && Array.isArray(data.data)) {
                                data.data.forEach(province => {
                                    let option = document.createElement("option");
                                    option.value = province.ProvinceID;
                                    option.textContent = province.ProvinceName;
                                    provinceSelect.appendChild(option);
                                });
                            } else {
                                console.error("Invalid data format:", data);
                            }
                        })
                        .catch(error => console.error("Error:", error));
                function updateDistrictOption() {
                    let selectedProvince = document.getElementById("province").value;
                    fetch("<%=GHNConfig.GET_DISTRICT_URL%>?province_id=" + selectedProvince, {
                        method: "GET",
                        headers: {
                            "Content-Type": "application/json",
                            "Token": "<%=GHNConfig.TOKEN_API%>"
                        }
                    })
                            .then(response => response.json())
                            .then(data => {
                                let districtSelect = document.getElementById("district");
                                districtSelect.innerHTML = "";
                                document.getElementById("ward").innerHTML = "";

                                if (data.code === 200 && Array.isArray(data.data)) {
                                    data.data.forEach(district => {
                                        let option = document.createElement("option");
                                        option.value = district.DistrictID;
                                        option.textContent = district.DistrictName;
                                        districtSelect.appendChild(option);
                                    });
                                } else {
                                    console.error("Invalid data format:", data);
                                }
                            })
                            .catch(error => console.error("Error:", error));
                }
                function updateWardOption() {
                    let selectedDistrict = document.getElementById("district").value;
                    fetch("<%=GHNConfig.GET_WARD_URL%>?district_id=" + selectedDistrict, {
                        method: "GET",
                        headers: {
                            "Content-Type": "application/json",
                            "Token": "<%=GHNConfig.TOKEN_API%>"
                        }
                    })
                            .then(response => response.json())
                            .then(data => {
                                let wardSelect = document.getElementById("ward");
                                wardSelect.innerHTML = "";

                                if (data.code === 200 && Array.isArray(data.data)) {
                                    data.data.forEach(ward => {
                                        let option = document.createElement("option");
                                        option.value = ward.WardCode;
                                        option.textContent = ward.WardName;
                                        wardSelect.appendChild(option);
                                    });
                                } else {
                                    console.error("Invalid data format:", data);
                                }
                            })
                            .catch(error => console.error("Error:", error));
                }
                function updateShippingFee() {
                    let selectedDistrict = document.getElementById("district").value;
                    let selectedWard = document.getElementById("ward").value;
                    fetch("<%=GHNConfig.CALCULATE_SHIPPING_FEE_URL%>", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                            "Token": "<%=GHNConfig.TOKEN_API%>",
                            "ShopId": "<%=GHNConfig.SHOP_ID%>"
                        },
                        body: JSON.stringify({
                            "to_district_id": +selectedDistrict,
                            "to_ward_code": selectedWard,
                            "weight": 1000,
                            "service_type_id": 2,
                            "service_id": 53321,
                            "items": [
                                {
                                    "name": "TEST1",
                                    "quantity": 1,
                                    "length": 200,
                                    "width": 200,
                                    "height": 200,
                                    "weight": 1000
                                }]
                        })
                    })
                            .then(response => response.json())
                            .then(data => {
                                if (data.code === 200) {
                                    let shippingFee = document.getElementById("shippingFee");
                                    shippingFee.innerHTML = "";
                                    let totalFee = document.getElementById("totalFee");
                                    totalFee.innerHTML = "";
                                    shippingFee.innerHTML = data.data.total + " VNĐ";
                                    totalFee.innerHTML = (${totalPrice} + data.data.total) + " VNĐ";
                                    document.getElementById("totalPrice").value = ${totalPrice} + data.data.total;

                                } else {
                                    console.error("Invalid data format:", data);
                                }
                            })
                            .catch(error => console.error("Error:", error));
                }
                function updateAddress() {
                    let selectedProvince = document.getElementById("province");
                    let selectedDistrict = document.getElementById("district");
                    let selectedWard = document.getElementById("ward");
                    let subAddress = document.getElementById("subAddress").value;

                    let selectedProvinceText = selectedProvince.options[selectedProvince.selectedIndex].text;
                    let selectedDistrictText = selectedDistrict.options[selectedDistrict.selectedIndex].text;
                    let selectedWardText = selectedWard.options[selectedWard.selectedIndex].text;

                    document.getElementById("address").value = selectedProvinceText + ", " + selectedDistrictText + ", " + selectedWardText + ", " + subAddress;
                }
                function onWardChange() {
                    updateAddress();
                    updateShippingFee();
                }
            </script>
    </body>
</html>
