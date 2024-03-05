<%-- 
    Document   : manageProduct
    Created on : Feb 5, 2024, 8:59:19 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
    <!-- BEGIN: Head-->

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta name="description" content="Vuexy admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities.">
        <meta name="keywords" content="admin template, Vuexy admin template, dashboard template, flat admin template, responsive admin template, web app">
        <meta name="author" content="PIXINVENT">
        <title>Manage Product - Seller</title>
        <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">

        <!-- BEGIN: Vendor CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/vendors.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/extensions/nouislider.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/ui/prism.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/forms/select/select2.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/forms/spinner/jquery.bootstrap-touchspin.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/extensions/swiper.min.css">
        <!-- END: Vendor CSS-->

        <!-- BEGIN: Theme CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/bootstrap-extended.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/colors.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/components.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/themes/dark-layout.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/themes/semi-dark-layout.css">

        <!-- BEGIN: Page CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/core/menu/menu-types/vertical-menu.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/core/colors/palette-gradient.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/plugins/extensions/noui-slider.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/pages/app-ecommerce-shop.css">
        <!-- END: Page CSS-->

        <!-- BEGIN: Custom CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">
        <!-- END: Custom CSS-->
        <style>
            .color-circle {
                width: 20px;
                height: 20px;
                border-radius: 50%;
                display: inline-block;
                margin-right: 5px;
            }
            .error {
                color: red;
            }
            .item-img {
                position: relative;
                overflow: hidden;
            }
            .item-img img {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                object-fit: cover;
            }
        </style>
    </head>
    <!-- END: Head-->

    <!-- BEGIN: Body-->

    <body class="vertical-layout vertical-menu-modern dark-layout content-detached-left-sidebar ecommerce-application navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="content-detached-left-sidebar" data-layout="dark-layout">

        <!-- BEGIN: Header-->
        <nav class="header-navbar navbar-expand-lg navbar navbar-with-menu floating-nav navbar-dark navbar-shadow">
            <div class="navbar-wrapper">
                <div class="navbar-container content">
                    <div class="navbar-collapse" id="navbar-mobile">
                        <div class="mr-auto float-left bookmark-wrapper d-flex align-items-center">
                            <ul class="nav navbar-nav">
                                <li class="nav-item mobile-menu d-xl-none mr-auto"><a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#"><i class="ficon feather icon-menu"></i></a></li>
                            </ul>
                            <ul class="nav navbar-nav bookmark-icons">
                                <!-- li.nav-item.mobile-menu.d-xl-none.mr-auto-->
                                <!--   a.nav-link.nav-menu-main.menu-toggle.hidden-xs(href='#')-->
                                <!--     i.ficon.feather.icon-menu-->
                                <li class="nav-item d-none d-lg-block"></li>
                                <li class="nav-item d-none d-lg-block"></li>
                                <li class="nav-item d-none d-lg-block"></li>
                                <li class="nav-item d-none d-lg-block"></li>
                            </ul>
                        </div>
                        <ul class="nav navbar-nav float-right">
                            <li class="nav-item d-none d-lg-block"><a class="nav-link nav-link-expand"><i class="ficon feather icon-maximize"></i></a></li>
                                    <c:choose>
                                        <c:when test="${account.role_Id == 3}">
                                    <li class="dropdown dropdown-user nav-item"><a class="dropdown-toggle nav-link dropdown-user-link" href="#" data-toggle="dropdown">
                                            <div class="user-nav d-sm-flex d-none"><span class="user-name text-bold-600">Welcome</span><span class="user-status">${account.email}</span></div><span><img class="round" src="${pageContext.request.contextPath}/app-assets/images/portrait/small/avatar-s-11.jpg" alt="avatar" height="40" width="40"></span>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item" href="page-user-profile.html"><i class="feather icon-user"></i> Edit Profile</a>
                                            <div class="dropdown-divider"></div><a class="dropdown-item" data-toggle="modal" data-target="#logoutModalAdmin" href="#"><i class="feather icon-power"></i> Logout</a>
                                        </div>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="dropdown dropdown-user nav-item"><a class="dropdown-toggle nav-link dropdown-user-link" href="#" data-toggle="dropdown">
                                            <div class="user-nav d-sm-flex d-none"><span class="user-name text-bold-600">${account.email}</span><span class="user-status">${account.role_Id}</span></div><span><img class="round" src="${pageContext.request.contextPath}/app-assets/images/portrait/small/avatar-s-11.jpg" alt="avatar" height="40" width="40"></span>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item" href="page-user-profile.html"><i class="feather icon-user"></i> Edit Profile</a>
                                            <div class="dropdown-divider"></div><a class="dropdown-item" data-toggle="modal" data-target="#logoutModalAdmin" href="#"><i class="feather icon-power"></i> Logout</a>
                                        </div>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <ul class="main-search-list-defaultlist-other-list d-none">
            <li class="auto-suggestion d-flex align-items-center justify-content-between cursor-pointer"><a class="d-flex align-items-center justify-content-between w-100 py-50">
                    <div class="d-flex justify-content-start"><span class="mr-75 feather icon-alert-circle"></span><span>No results found.</span></div>
                </a></li>
        </ul>
        <!-- END: Header-->


        <!-- BEGIN: Main Menu-->
        <div class="main-menu menu-fixed menu-light menu-accordion menu-shadow" data-scroll-to-active="true">
            <div class="navbar-header">
                <ul class="nav navbar-nav flex-row">
                    <li class="nav-item mr-auto"><a class="navbar-brand" href="${pageContext.request.contextPath}/admin/dashboard">
                            <div class="brand-logo"></div>
                            <h2 class="brand-text mb-0">Admin</h2>
                        </a></li>
                    <li class="nav-item nav-toggle"><a class="nav-link modern-nav-toggle pr-0" data-toggle="collapse"><i class="feather icon-x d-block d-xl-none font-medium-4 primary toggle-icon"></i><i class="toggle-icon feather icon-disc font-medium-4 d-none d-xl-block collapse-toggle-icon primary" data-ticon="icon-disc"></i></a></li>
                </ul>
            </div>
            <div class="shadow-bottom"></div>
            <div class="main-menu-content">
                <ul class="navigation navigation-main" id="main-menu-navigation" data-menu="menu-navigation">
                    <li class=" nav-item"><a href="index.html"><i class="feather icon-home"></i><span class="menu-title" data-i18n="Dashboard">Dashboard</span><span class="badge badge badge-warning badge-pill float-right mr-2">1</span></a>
                        <ul class="menu-content">
                            <li class=""><a href="${pageContext.request.contextPath}/seller/dashboard"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Analytics">Analytics</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" navigation-header"><span>Apps</span>
                    </li>
                    <li class=" nav-item"><a href="#"><i class="feather icon-shopping-bag"></i><span class="menu-title" data-i18n="Ecommerce">Product</span></a>
                        <ul class="menu-content">
                            <li class="active"><a href="${pageContext.request.contextPath}/seller/manageInvoice"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Shop">Manage Products</span></a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/seller/dashboard?page=view-details"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Details">Product Details</span></a>
                            </li>
                            <li><a href="app-ecommerce-checkout.html"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Checkout">Checkout</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" nav-item"><a href="#"><i class="feather icon-file"></i><span class="menu-title" data-i18n="Invoice">Invoice</span></a>
                        <ul class="menu-content">
                            <li><a href="${pageContext.request.contextPath}/seller/manageproduct"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="List">List</span></a>
                            </li>
                            <li><a href="app-user-view.html"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="View">View</span></a>
                            </li>
                            <li><a href="app-user-edit.html"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Edit">Edit</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" navigation-header"><span>pages</span>
                    </li>
                    <li class=" nav-item"><a href="page-user-profile.html"><i class="feather icon-user"></i><span class="menu-title" data-i18n="Profile">Profile</span></a>
                    </li>
                    <li class=" nav-item"><a href="page-invoice.html"><i class="feather icon-file"></i><span class="menu-title" data-i18n="Invoice">Invoice</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- END: Main Menu-->

        <!-- BEGIN: Content-->
        <div class="app-content content">
            <div class="content-overlay"></div>
            <div class="header-navbar-shadow"></div>
            <div class="content-wrapper">
                <div class="content-header row">
                    <div class="content-header-left col-md-9 col-12 mb-2">
                        <div class="row breadcrumbs-top">
                            <div class="col-12">
                                <h2 class="content-header-title float-left mb-0">Manage Products</h2>
                                <div class="breadcrumb-wrapper col-12">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item active">Products List
                                        </li>
                                    </ol>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="content-detached content-right">
                    <div class="content-body">
                        <!-- Ecommerce Content Section Starts -->
                        <section id="ecommerce-header">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="ecommerce-header-items">
                                        <div class="result-toggler">
                                            <button class="navbar-toggler shop-sidebar-toggler" type="button" data-toggle="collapse">
                                                <span class="navbar-toggler-icon d-block d-lg-none"><i class="feather icon-menu"></i></span>
                                            </button>
                                            <div class="search-results">
                                                ${pagination.totalRecord} results found
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <!-- Ecommerce Content Section Starts -->
                        <!-- background Overlay when sidebar is shown  starts-->
                        <div class="shop-content-overlay"></div>
                        <!-- background Overlay when sidebar is shown  ends-->

                        <!-- Ecommerce Search Bar Starts -->
                        <form action="${pageContext.request.contextPath}/seller/manageproduct" method="GET">
                            <section id="ecommerce-searchbar">
                                <div class="row mt-1">
                                    <div class="col-sm-12">
                                        <fieldset class="form-group position-relative">
                                            <input type="hidden" name="action" value="search-products">
                                            <input type="hidden" name="sort" id="sortHidden1">
                                            <input name="keyword" type="text" class="form-control search-product" id="iconLeft5" placeholder="Search here">
                                            <div class="form-control-position">
                                                <button type="submit"><i class="feather icon-search"></i></button>
                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                            </section>
                        </form>
                        <!-- Ecommerce Search Bar Ends -->

                        <!-- Ecommerce Products Starts -->
                        <section id="ecommerce-products" class="grid-view">
                            <c:forEach items="${listPf}" var="pf">
                                <div class="card ecommerce-card">
                                    <div class="card-content">
                                        <div class="item-img text-center">
                                            <a href="${pageContext.request.contextPath}/seller/manageproduct?page=view-product-details&productID=${pf.id}&colorID=${pf.color_id}&categoryID=${pf.category_id}&sizeID=${pf.size_id}&brandID=${pf.brand_id}&genderID=${pf.gender_id}">
                                                <img class="img-fluid" src="${pf.image_path}" alt="img-placeholder">
                                            </a>
                                        </div>
                                        <div class="card-body">
                                            <div class="item-wrapper">
                                                <!--                                                <div class="item-rating">
                                                                                                    <div class="badge badge-primary badge-md">
                                                                                                        <span>4</span> <i class="feather icon-star"></i>
                                                                                                    </div>
                                                                                                </div>-->
                                                <div>
                                                    <h6 class="item-price">
                                                        $${pf.price}
                                                    </h6>
                                                </div>
                                            </div>
                                            <div class="item-name">
                                                <a href="${pageContext.request.contextPath}/seller/manageproduct?page=view-product-details&productID=${pf.id}&colorID=${pf.color_id}&categoryID=${pf.category_id}&sizeID=${pf.size_id}&brandID=${pf.brand_id}&genderID=${pf.gender_id}">${pf.name}</a>
                                                <!--                                                <p class="item-company">By <span class="company-name">Google</span></p>-->
                                            </div>
                                            <div>
                                                <p class="item-descriptionn" style="display: inline-block; margin-right: 10px;">
                                                    Size: ${pf.size}
                                                </p>
                                                <p class="item-color" style="display: inline-block; margin-right: 10px;">
                                                    Color:
                                                </p>
                                                <div class="color-circle" style="background-color: ${pf.color}; display: inline-block; width: 20px; height: 20px; border-radius: 50%; vertical-align: middle;"></div>
                                            </div>
                                        </div>
                                        <div class="item-options text-center">
                                            <a class="btn btn-primary btn-equal-size view mb-2 mb-md-0 flex-fill" href="${pageContext.request.contextPath}/seller/manageProduct?page=view-product-details&productID=${pf.id}&colorID=${pf.color_id}&categoryID=${pf.category_id}&sizeID=${pf.size_id}&brandID=${pf.brand_id}&genderID=${pf.gender_id}">
                                                <i class="feather icon-eye"></i> <span>View</span>
                                            </a>
                                            <button class="btn btn-success btn-equal-size wishlist mb-2 mb-md-0 flex-fill">
                                                <i class="feather icon-edit"></i> <span>Update</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </section>
                        <!-- Ecommerce Products Ends -->

                        <!-- Ecommerce Pagination Starts -->
                        <section id="ecommerce-pagination">
                            <div class="row">
                                <div class="col-sm-12">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center mt-2">
                                            <c:if test="${pagination.page > 1}">
                                                <li class="page-item prev-item"><a class="page-link" href="${pagination.urlPattern}pagination=1">Start</a></li>
                                                </c:if>
                                                <c:if test="${pagination.page > 1}">
                                                <li class="page-item active"><a class="page-link" href="${pagination.urlPattern}pagination=${pagination.page - 1}">Previous</a></li>
                                                </c:if>
                                            <!--PAGE - 2 (in case last page )-->
                                            <c:if test="${pagination.page == pagination.totalPage && pagination.page > 2}">
                                                <li class="page-item"><a class="page-link" href="${pagination.urlPattern}pagination=${pagination.page - 2}">${pagination.page - 2}</a></li>
                                                </c:if>
                                            <!--PAGE - 1 (in case last page )-->
                                            <c:if test="${pagination.page == pagination.totalPage && pagination.page > 1}">
                                                <li class="page-item"><a class="page-link" href="${pagination.urlPattern}pagination=${pagination.page - 1}">${pagination.page - 1}</a></li>
                                                </c:if>
                                            <!--PAGE-->
                                            <c:if test="${pagination.totalPage > 1}">
                                                <li class="page-item" aria-current="page"><a class="page-link" href="${pagination.urlPattern}pagination=${pagination.page}">${pagination.page}</a></li>
                                                </c:if>
                                            <!--PAGE + 1-->
                                            <c:if test="${pagination.page < pagination.totalPage}">
                                                <li class="page-item"><a class="page-link" href="${pagination.urlPattern}pagination=${pagination.page + 1}">${pagination.page + 1}</a></li>
                                                </c:if>
                                            <!--PAGE + 2-->
                                            <c:if test="${pagination.page + 1 < pagination.totalPage}">
                                                <li class="page-item"><a class="page-link" href="${pagination.urlPattern}pagination=${pagination.page + 2}">${pagination.page + 2}</a></li>
                                                </c:if>
                                            <!--NEXT-->
                                            <c:if test="${pagination.page != pagination.totalPage && pagination.totalPage > 0}">
                                                <li class="page-item active"><a class="page-link" href="${pagination.urlPattern}pagination=${pagination.page + 1}">Next</a></li>
                                                </c:if>
                                            <!--LAST-->
                                            <c:if test="${pagination.page != pagination.totalPage && pagination.totalPage > 0}">
                                                <li class="page-item next-item"><a class="page-link" href="${pagination.urlPattern}pagination=${pagination.totalPage}">Last</a></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </section>
                        <!-- Ecommerce Pagination Ends -->
                    </div>
                </div>
                <div class="sidebar-detached sidebar-left">
                    <div class="sidebar">
                        <!-- Ecommerce Sidebar Starts -->
                        <form id="filter-form2" action="${pageContext.request.contextPath}/seller/manageProduct" method="GET">
                            <input type="hidden" name="action" value="filter-products">
                            <input type="hidden" name="sort" id="sortHidden2">
                            <div class="sidebar-shop" id="ecommerce-sidebar-toggler">

                                <div class="row">
                                    <div class="col-sm-12">
                                        <h6 class="filter-heading d-none d-lg-block">Filters</h6>
                                    </div>
                                </div>
                                <span class="sidebar-close-icon d-block d-md-none">
                                    <i class="feather icon-x"></i>
                                </span>
                                <div class="card">
                                    <div class="card-body">
                                        <div class="multi-range-price">
                                            <div class="multi-range-title pb-75">
                                                <h6 class="filter-title mb-0">Multi Range</h6>
                                            </div>
                                            <ul class="list-unstyled price-range" id="price-range">
                                                <li>
                                                    <span class="vs-radio-con vs-radio-primary py-25">
                                                        <input type="radio" name="price-range" checked value="">
                                                        <span class="vs-radio">
                                                            <span class="vs-radio--border"></span>
                                                            <span class="vs-radio--circle"></span>
                                                        </span>
                                                        <span class="ml-50">All</span>
                                                    </span>
                                                </li>
                                                <li>
                                                    <span class="vs-radio-con vs-radio-primary py-25">
                                                        <input type="radio" name="price-range" value="0-50">
                                                        <span class="vs-radio">
                                                            <span class="vs-radio--border"></span>
                                                            <span class="vs-radio--circle"></span>
                                                        </span>
                                                        <span class="ml-50">&lt;=$50</span>
                                                    </span>
                                                </li>
                                                <li>
                                                    <span class="vs-radio-con vs-radio-primary py-25">
                                                        <input type="radio" name="price-range" value="50-100">
                                                        <span class="vs-radio">
                                                            <span class="vs-radio--border"></span>
                                                            <span class="vs-radio--circle"></span>
                                                        </span>
                                                        <span class="ml-50">$50 - $100</span>
                                                    </span>
                                                </li>
                                                <li>
                                                    <span class="vs-radio-con vs-radio-primary py-25">
                                                        <input type="radio" name="price-range" value="100-300">
                                                        <span class="vs-radio">
                                                            <span class="vs-radio--border"></span>
                                                            <span class="vs-radio--circle"></span>
                                                        </span>
                                                        <span class="ml-50">$100 - $300</span>
                                                    </span>
                                                </li>
                                                <li>
                                                    <span class="vs-radio-con vs-radio-primary py-25">
                                                        <input type="radio" name="price-range" value="300-1000">
                                                        <span class="vs-radio">
                                                            <span class="vs-radio--border"></span>
                                                            <span class="vs-radio--circle"></span>
                                                        </span>
                                                        <span class="ml-50">&gt;= $300</span>
                                                    </span>
                                                </li>
                                            </ul>
                                        </div>
                                        <!-- /Price Filter -->
                                        <hr>
                                        <!-- Categories Starts -->
                                        <div id="product-categories">
                                            <div class="product-category-title">
                                                <h6 class="filter-title mb-1">Categories</h6>
                                            </div>
                                            <ul class="list-unstyled categories-list">
                                                <c:forEach items="${listCate}" var="cate">
                                                    <li>
                                                        <span class="vs-checkbox-con vs-checkbox-primary py-25">
                                                            <input type="checkbox" name="category-filter" value="${cate.id}">
                                                            <span class="vs-checkbox">
                                                                <span class="vs-checkbox--check"></span>
                                                            </span>
                                                            <span class="ml-50">${cate.category}</span>
                                                        </span>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <!-- Categories Ends -->
                                        <hr>
                                        <!-- Brands -->
                                        <div class="brands">
                                            <div class="brand-title mt-1 pb-1">
                                                <h6 class="filter-title mb-0">Brands</h6>
                                            </div>
                                            <div class="brand-list" id="brands">
                                                <ul class="list-unstyled">
                                                    <c:forEach items="${brandCounts}" var="bc">
                                                        <li class="d-flex justify-content-between align-items-center py-25">
                                                            <span class="vs-checkbox-con vs-checkbox-primary">
                                                                <input type="checkbox" name="brand-filter" value="${bc.id}">
                                                                <span class="vs-checkbox">
                                                                    <span class="vs-checkbox--check"></span>
                                                                </span>
                                                                <span class="">${bc.brand}</span>
                                                            </span>
                                                            <span>${bc.countEachBrand}</span>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <!-- /Brand -->
                                        <hr>
                                        <!-- Color -->
                                        <div class="colors">
                                            <div class="brand-title mt-1 pb-1">
                                                <h6 class="filter-title mb-0">Color</h6>
                                            </div>
                                            <div class="colors-list" id="colors">
                                                <ul class="list-unstyled">
                                                    <c:forEach items="${listC}" var="c">
                                                        <li class="d-flex justify-content-between align-items-center py-25">
                                                            <span class="vs-checkbox-con vs-checkbox-primary">
                                                                <input type="checkbox" name="colors-filter" value="${c.id}">
                                                                <span class="vs-checkbox">
                                                                    <span class="vs-checkbox--check"></span>
                                                                </span>
                                                                <span class="">${c.color}</span>
                                                            </span>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <!-- /Brand -->
                                        <hr>
                                        <!-- Size -->
                                        <div class="size">
                                            <div class="brand-title mt-1 pb-1">
                                                <h6 class="filter-title mb-0">Size</h6>
                                            </div>
                                            <div class="size-list" id="size">
                                                <ul class="list-unstyled">
                                                    <c:forEach items="${listS}" var="s">
                                                        <li class="d-flex justify-content-between align-items-center py-25">
                                                            <span class="vs-checkbox-con vs-checkbox-primary">
                                                                <input type="checkbox" name="size-filter" value="${s.id}">
                                                                <span class="vs-checkbox">
                                                                    <span class="vs-checkbox--check"></span>
                                                                </span>
                                                                <span class="">${s.size}</span>
                                                            </span>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <!-- /Size -->
                                        <hr>
                                        <!-- Gender -->
                                        <div class="gender">
                                            <div class="brand-title mt-1 pb-1">
                                                <h6 class="filter-title mb-0">Gender</h6>
                                            </div>
                                            <div class="gender-list" id="gender">
                                                <ul class="list-unstyled">
                                                    <c:forEach items="${listG}" var="g">
                                                        <li class="d-flex justify-content-between align-items-center py-25">
                                                            <span class="vs-checkbox-con vs-checkbox-primary">
                                                                <input type="checkbox" name="gender-filter" value="${g.id}">
                                                                <span class="vs-checkbox">
                                                                    <span class="vs-checkbox--check"></span>
                                                                </span>
                                                                <span class="">${g.gender}</span>
                                                            </span>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <!-- /Brand -->

                                        <!--                                     Rating section starts 
                                                                            <div id="ratings">
                                                                                <div class="ratings-title mt-1 pb-75">
                                                                                    <h6 class="filter-title mb-0">Ratings</h6>
                                                                                </div>
                                                                                <div class="d-flex justify-content-between">
                                                                                    <ul class="unstyled-list list-inline ratings-list mb-0">
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li>& up</li>
                                                                                    </ul>
                                                                                    <div class="stars-received">(160)</div>
                                                                                </div>
                                                                                <div class="d-flex justify-content-between">
                                                                                    <ul class="unstyled-list list-inline ratings-list mb-0">
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li>& up</li>
                                                                                    </ul>
                                                                                    <div class="stars-received">(176)</div>
                                                                                </div>
                                                                                <div class="d-flex justify-content-between">
                                                                                    <ul class="unstyled-list list-inline ratings-list mb-0">
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li>& up</li>
                                                                                    </ul>
                                                                                    <div class="stars-received">(291)</div>
                                                                                </div>
                                                                                <div class="d-flex justify-content-between">
                                                                                    <ul class="unstyled-list list-inline ratings-list mb-0 ">
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-warning"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li class="ratings-list-item"><i class="feather icon-star text-light"></i></li>
                                                                                        <li>& up</li>
                                                                                    </ul>
                                                                                    <div class="stars-received">(190)</div>
                                                                                </div>
                                                                            </div>
                                                                             Rating section Ends -->
                                        <hr>
                                        <div id="submit-filters">
                                            <button type="submit" id="submit-filters-btn" class="btn btn-block btn-primary">SEARCH</button>
                                        </div>
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!-- Ecommerce Sidebar Ends -->
                    </div>
                </div>
            </div>
        </div>
        <!-- END: Content-->

        <div class="sidenav-overlay"></div>
        <div class="drag-target"></div>

        <!-- BEGIN: Footer-->
        <footer class="footer footer-static footer-light">
            <p class="clearfix blue-grey lighten-2 mb-0"><span class="float-md-left d-block d-md-inline-block mt-25">COPYRIGHT &copy; <script>document.write(new Date().getFullYear());</script><a class="text-bold-800 grey darken-2" href="" target="_blank">Brava,</a>All rights Reserved</span><span class="float-md-right d-none d-md-block">Hand-crafted & Made with<i class="feather icon-heart pink"></i></span>
                <button class="btn btn-primary btn-icon scroll-top" type="button"><i class="feather icon-arrow-up"></i></button>
            </p>
        </footer>
        <!-- END: Footer-->


        <!-- BEGIN: Vendor JS-->
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/vendors.min.js"></script>
        <!-- BEGIN Vendor JS-->

        <!-- BEGIN: Page Vendor JS-->
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/ui/prism.min.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/wNumb.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/nouislider.min.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/forms/select/select2.full.min.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/forms/spinner/jquery.bootstrap-touchspin.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/swiper.min.js"></script>
        <!-- END: Page Vendor JS-->

        <!-- BEGIN: Theme JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app-menu.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/components.js"></script>
        <!-- END: Theme JS-->

        <!-- BEGIN: Page JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/pages/app-ecommerce-shop.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/forms/number-input.js"></script>

        <!-- END: Page JS-->

        <script>
                function clearFilters() {
                    // Clear radio buttons for price range
                    document.querySelectorAll('input[name="price-range"]').forEach(function (element) {
                        element.checked = false;
                    });

                    // Clear radio buttons for category filter
                    document.querySelectorAll('input[name="category-filter"]').forEach(function (element) {
                        element.checked = false;
                    });

                    // Clear checkboxes for brands
                    document.querySelectorAll('input[name="brand-filter"]').forEach(function (element) {
                        element.checked = false;
                    });
                }

                function sortHiddenInput(selectElement) {
                    var selectedValue = selectElement.value;
                    if (selectedValue === 'Sorted') {
                        document.getElementById('sortHidden1').value = null;
                        document.getElementById('sortHidden2').value = null;
                    } else {
                        document.getElementById('sortHidden1').value = selectedValue;
                        document.getElementById('sortHidden2').value = selectedValue;
                    }
                }
        </script>    

        <jsp:include page="../admin/logOutModal.jsp"></jsp:include> 
    </body>
    <!-- END: Body-->

</html>