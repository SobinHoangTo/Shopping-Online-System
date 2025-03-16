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