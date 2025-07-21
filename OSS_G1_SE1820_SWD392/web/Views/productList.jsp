<%-- 
    Document   : productList
    Created on : Mar 15, 2025, 1:12:29 AM
    Author     : vdqvi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Product List</title>
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
                            <span>Products <i class="fa fa-chevron-right"></i></span>
                        </p>
                        <h2 class="mb-0 bread">Products</h2>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <!-- Search Bar -->
                        <div class="row mb-4">
                            <div class="col-md-9">
                                <input type="text" value="${param.search}" oninput="debounce(changeFilter, 500)" id="productSearch" class="form-control" placeholder="Search for products..." />
                            </div>
                            <div class="col-md-3">
                                <select id="sortProducts" class="form-control" onchange="changeFilter()">
                                    <option value="">Sort</option>
                                    <option value="price_asc" ${(param.sortBy == "price" && param.sortOrder == "asc") ? "selected" : ""}>Price Ascending</option>
                                    <option value="price_desc" ${(param.sortBy == "price" && param.sortOrder == "desc") ? "selected" : ""}>Price Descending</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <c:forEach var="product" items="${products}">
                                <div class="col-md-4 d-flex">
                                    <div class="product ftco-animate">
                                        <div
                                            class="img d-flex align-items-center justify-content-center"
                                            style="background-image: url(${product.getImage()})"
                                            >
                                            <div class="desc">
                                                <p class="meta-prod d-flex">
                                                    <a
                                                        href="product/${product.getId()}"
                                                        class="d-flex align-items-center justify-content-center"
                                                        ><span class="flaticon-visibility"></span
                                                        ></a>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="text text-center">
                                            <span class="sale">Sale</span>
                                            <!--                                        <span class="seller">Best Seller</span>
                                                                                    <span class="new">New Arrival</span> -->

                                            <h2>${product.getName()}</h2>
                                            <span class="category">${product.getDescription()}</span>
                                            <p class="mb-0">
                                                <!--<span class="price price-sale">$69.00</span>-->
                                                <span class="price">${product.getPrice()} VNĐ</span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="d-flex justify-content-end">
                            <div class="text-center">
                                <label for="pageSize" class="mr-2">Show:</label>
                                <select id="pageSize" class="custom-select w-auto" onchange="changeFilter()">
                                    <option value="10" ${param.pageSize == 10 ? "selected" : ""}>10 per page</option>
                                    <option value="20" ${param.pageSize == 20 ? "selected" : ""}>20 per page</option>
                                    <option value="50"${param.pageSize == 50 ? "selected" : ""}>50 per page</option>
                                </select>
                            </div>
                            <div class="col-md-4 text-center">
                                <nav>
                                    <ul id="pagination" class="pagination justify-content-center"></ul>
                                </nav>
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

        <script>
            function debounce(func, delay) {
                let timer;
                return function (...args) {
                    clearTimeout(timer);
                    timer = setTimeout(() => func.apply(this, args), delay);
                };
            }

            function changeFilter() {
                let search = document.getElementById("productSearch").value;
                let pageSize = document.getElementById("pageSize").value;
                let sort = document.getElementById("sortProducts").value;

                let href = "products?search=" + encodeURIComponent(search) + "&pageSize=" + pageSize;

                if (sort) {
                    let sortParts = sort.split("_");
                    let sortBy = sortParts[0];
                    let sortOrder = sortParts[1];
                    href += "&sortOrder=" + sortOrder + "&sortBy=" + sortBy;
                }

                window.location.href = href;
            }

            function changePageIndex(value) {
                let search = document.getElementById("productSearch").value;
                let pageSize = document.getElementById("pageSize").value;
                let sort = document.getElementById("sortProducts").value;

                let href = "products?search=" + encodeURIComponent(search) + "&pageSize=" + pageSize;

                if (sort) {
                    let sortParts = sort.split("_");
                    let sortBy = sortParts[0];
                    let sortOrder = sortParts[1];
                    href += "&sortOrder=" + sortOrder + "&sortBy=" + sortBy;
                }

                window.location.href = href + "&pageIndex=" + value;
            }

            const totalCount = ${count};
            const currentPage = ${param.pageIndex != null ? param.pageIndex : 1};
            const pageSize = parseInt(document.getElementById("pageSize").value) || 10;
            const totalPages = Math.ceil(totalCount / pageSize);

            function generatePagination() {
                let paginationContainer = document.getElementById("pagination");
                paginationContainer.innerHTML = "";

                let prevClass = currentPage === 1 ? "disabled" : "";
                paginationContainer.innerHTML += "<li class=\"page-item " + prevClass + "\"> <a class=\"page-link\" onclick=\"changePageIndex(" + (currentPage - 1) + ")\">&laquo;</a></li>";

                for (let i = 1; i <= totalPages; i++) {
                    let activeClass = currentPage === i ? "active" : "";
                    paginationContainer.innerHTML += "<li class=\"page-item " + activeClass + "\"><a class=\"page-link\" onclick=\"changePageIndex(" + (i - 1) + ")\">" + i + "</a></li>";
                }

                let nextClass = currentPage === totalPages ? "disabled" : "";
                paginationContainer.innerHTML += "<li class=\"page-item " + nextClass + "\"> <a class=\"page-link\" onclick=\"changePageIndex(" + (currentPage + 1) + ")\">&raquo;</a></li>";
            }

            function changePageIndex(value) {
                if (value < 1 || value > totalPages)
                    return;

                let search = document.getElementById("productSearch").value;
                let pageSize = document.getElementById("pageSize").value;
                let sort = document.getElementById("sortProducts").value;

                let href = "products?search= " + encodeURIComponent(search) + "&pageSize=" + pageSize + "&pageIndex=" + value;

                if (sort) {
                    let sortParts = sort.split("_");
                    href += "&sortBy=" + sortParts[0] + "&sortOrder=" + sortParts[1];
                }

                window.location.href = href;
            }
            generatePagination();
        </script>
        <%@include file="footer.jsp" %>
    </body>
</html>

