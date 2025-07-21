<%-- 
    Document   : productDetail
    Created on : Mar 15, 2025, 11:45:22 AM
    Author     : vdqvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Detail</title>
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
                            <span
                                ><a href="<%=request.getContextPath()%>/products"
                                >Products <i class="fa fa-chevron-right"></i></a
                                ></span>
                            <span>Products Single <i class="fa fa-chevron-right"></i></span>
                        </p>
                        <h2 class="mb-0 bread">Products Single</h2>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 mb-5 ftco-animate">
                        <a href="${product.getImage()}" class="image-popup prod-img-bg d-flex justify-content-centers"
                           ><img
                                src="${product.getImage()}"
                                class="img-fluid "
                                alt="Colorlib Template"
                                /></a>
                    </div>
                    <div class="col-lg-6 product-details pl-md-5 ftco-animate">
                        <h3>${product.getName()}</h3>
                        <div class="rating d-flex">
                            <p class="text-left mr-4">
                                <a href="#" class="mr-2">5.0</a>
                                <a href="#"><span class="fa fa-star"></span></a>
                                <a href="#"><span class="fa fa-star"></span></a>
                                <a href="#"><span class="fa fa-star"></span></a>
                                <a href="#"><span class="fa fa-star"></span></a>
                                <a href="#"><span class="fa fa-star"></span></a>
                            </p>
                            <p class="text-left mr-4">
                                <a href="#" class="mr-2" style="color: #000"
                                   >100 <span style="color: #bbb">Rating</span></a
                                >
                            </p>
                            <p class="text-left">
                                <a href="#" class="mr-2" style="color: #000"
                                   >500 <span style="color: #bbb">Sold</span></a
                                >
                            </p>
                        </div>
                        <p class="price"><span>${product.getPrice()} VNĐ</span></p>
                        <p>
                            ${product.getDescription()}
                        </p>

                        <div class="row mt-4">
                            <div class="input-group col-md-6 d-flex mb-3">
                                <span class="input-group-btn mr-2">
                                    <button
                                        type="button"
                                        class="quantity-left-minus btn"
                                        data-type="minus"
                                        data-field=""
                                        >
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </span>
                                <input
                                    type="text"
                                    id="quantity"
                                    name="quantity"
                                    class="quantity form-control input-number"
                                    value="1"
                                    min="1"
                                    max="100"
                                    />
                                <span class="input-group-btn ml-2">
                                    <button
                                        type="button"
                                        class="quantity-right-plus btn"
                                        data-type="plus"
                                        data-field=""
                                        >
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </span>
                            </div>
                            <div class="w-100"></div>
                            <div class="col-md-12">
                                <p style="color: #000">80 piece available</p>
                            </div>
                        </div>
                        <p>
                            <a id="addToCartBtn" class="btn btn-primary py-3 px-5 mr-2"
                               >Add to Cart</a
                            ><a href="cart.html" class="btn btn-primary py-3 px-5">Buy now</a>
                        </p>
                    </div>
                </div>

                <div class="row mt-5">
                    <div class="col-md-12 nav-link-wrap">
                        <div
                            class="nav nav-pills d-flex text-center"
                            id="v-pills-tab"
                            role="tablist"
                            aria-orientation="vertical"
                            >
                            <a
                                class="nav-link ftco-animate active mr-lg-1"
                                id="v-pills-1-tab"
                                data-toggle="pill"
                                href="#v-pills-1"
                                role="tab"
                                aria-controls="v-pills-1"
                                aria-selected="true"
                                >Description</a
                            >

                            <a
                                class="nav-link ftco-animate"
                                id="v-pills-3-tab"
                                data-toggle="pill"
                                href="#v-pills-3"
                                role="tab"
                                aria-controls="v-pills-3"
                                aria-selected="false"
                                >Reviews</a
                            >
                        </div>
                    </div>
                    <div class="col-md-12 tab-wrap">
                        <div class="tab-content bg-light" id="v-pills-tabContent">
                            <div
                                class="tab-pane fade show active"
                                id="v-pills-1"
                                role="tabpanel"
                                aria-labelledby="day-1-tab"
                                >
                                <div class="p-4">
                                    <h3 class="mb-4">${product.getName()}</h3>
                                    <p>${product.getDescription()}</p>
                                </div>
                            </div>

                            <div
                                class="tab-pane fade"
                                id="v-pills-3"
                                role="tabpanel"
                                aria-labelledby="v-pills-day-3-tab"
                                >
                                <div class="row p-4">
                                    <div class="col-md-7">
                                        <h3 class="mb-4">23 Reviews</h3>
                                        <div class="review">
                                            <div
                                                class="user-img"
                                                style="background-image: url(<%=request.getContextPath()%>/Images/person_1.jpg)"
                                                ></div>
                                            <div class="desc">
                                                <h4>
                                                    <span class="text-left">Jacob Webb</span>
                                                    <span class="text-right">25 April 2020</span>
                                                </h4>
                                                <p class="star">
                                                    <span>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                    </span>
                                                    <span class="text-right"
                                                          ><a href="#" class="reply"
                                                        ><i class="icon-reply"></i></a
                                                        ></span>
                                                </p>
                                                <p>
                                                    When she reached the first hills of the Italic
                                                    Mountains, she had a last view back on the skyline
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="rating-wrap">
                                            <h3 class="mb-4">Give a Review</h3>
                                            <p class="star">
                                                <span>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    (98%)
                                                </span>
                                                <span>20 Reviews</span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
            $(document).ready(function () {
                $(".quantity-right-plus").click(function (e) {
                    e.preventDefault();
                    var quantity = parseInt($("#quantity").val());
                    $("#quantity").val(quantity + 1);
                });
                $(".quantity-left-minus").click(function (e) {
                    e.preventDefault();
                    var quantity = parseInt($("#quantity").val());
                    if (quantity > 1) {
                        $("#quantity").val(quantity - 1);
                    }
                });
            });
        </script>

        <script>
            function addToCart() {
                let quantity = document.getElementById("quantity").value;
                let cartItems = getCookie("cartItems");
                let productId = ${product.getId()};
                if (cartItems) {
                    cartItems = JSON.parse(cartItems);
                } else {
                    cartItems = {};
                }

                if (cartItems[productId]) {
                    cartItems[productId] = parseInt(cartItems[productId]) + parseInt(quantity);
                } else {
                    cartItems[productId] = parseInt(quantity);
                }
                
                setCookie("cartItems", JSON.stringify(cartItems), 7);
                document.getElementById("quantity").value = 1;
                displayCartLength();
                window.alert("Added product name ${product.getName()} to cart.");
            }
            document.getElementById("addToCartBtn").addEventListener("click", addToCart);
        </script>
    </body>
</html>

