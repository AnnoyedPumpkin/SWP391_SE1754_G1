<%-- 
    Document   : productDetails
    Created on : Feb 5, 2024, 9:24:35 AM
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
        <title>Product Details - Admin</title>
        <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">

        <!-- BEGIN: Vendor CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/vendors.min.css">
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/pages/app-ecommerce-details.css">
        <!-- END: Page CSS-->

        <!-- BEGIN: Custom CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">
        <!-- END: Custom CSS-->
    </head>
    <!-- END: Head-->

    <!-- BEGIN: Body-->

    <body id="body" class="vertical-layout vertical-menu-modern dark-layout 2-columns ecommerce-application navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="2-columns" data-layout="dark-layout">

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
                                        <c:when test="${account.role_Id == 2}">
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
                    <li class=" nav-item"><a href="${pageContext.request.contextPath}/admin/dashboard"><i class="feather icon-home"></i><span class="menu-title" data-i18n="Dashboard">Dashboard</span><span class="badge badge badge-warning badge-pill float-right mr-2">1</span></a>
                        <ul class="menu-content">
                            <li><a href="dashboard-analytics.html"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Analytics">Analytics</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" navigation-header"><span>Apps</span>
                    </li>

                    <li class=" nav-item"><a href="#"><i class="feather icon-shopping-bag"></i><span class="menu-title" data-i18n="Ecommerce">Product</span></a>
                        <ul class="menu-content">
                            <li class="active"><a href="${pageContext.request.contextPath}/admin/manageproduct"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Shop">Manage Products</span></a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/admin/dashboard?page=view-details"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Details">Product Details</span></a>
                            </li>
                            <li><a href="app-ecommerce-checkout.html"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Checkout">Checkout</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" nav-item"><a href="#"><i class="feather icon-user"></i><span class="menu-title" data-i18n="User">User</span></a>
                        <ul class="menu-content">
                            <li><a href="app-user-list.html"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="List">List</span></a>
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
                    <li class=" nav-item"><a href="page-account-settings.html"><i class="feather icon-settings"></i><span class="menu-title" data-i18n="Account Settings">Account Settings</span></a>
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
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/manageproduct">Products List</a>
                                        </li>
                                        <li class="breadcrumb-item active">Details
                                        </li>
                                    </ol>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="content-body">
                    <!-- app ecommerce details start -->
                    <section class="app-ecommerce-details">
                        <div class="card">
                            <div class="card-body">
                                <div class="form-group breadcrum-right">
                                    <a style="float:right" class="btn btn-success btn-equal-size view-options mb-2 mb-md-0 flex-fill" href="${pageContext.request.contextPath}/admin/manageproduct?page=update-product&productID=${productForm.id}&colorID=${productForm.color_id}&categoryID=${productForm.category_id}&sizeID=${productForm.size_id}&brandID=${productForm.brand_id}&genderID=${productForm.gender_id}">
                                        <i class="feather icon-edit"></i> <span style="color: black">Update</span>
                                    </a> 
                                </div>
                                <div class="row mb-5 mt-2">
                                    <div class="col-12 col-md-5 d-flex flex-column align-items-center justify-content-center mb-2 mb-md-0">
                                        <!-- Product Images Carousel -->
                                        <div id="carouselIndicators" class="carousel slide" data-ride="carousel">
                                            <c:choose>
                                                <c:when test="${not empty productForm.image_path and not empty listImages}">
                                                    <div class="carousel-inner">
                                                        <div class="carousel-item active">
                                                            <img src="${productForm.image_path}" class="d-block w-100" alt="Default Image">
                                                        </div>
                                                        <c:forEach var="image" items="${listImages}" varStatus="loop">
                                                            <div class="carousel-item">
                                                                <img class="d-block w-100" src="${image.image}" alt="Slide ${loop.index + 1}">
                                                            </div>
                                                        </c:forEach>
                                                        <ol class="carousel-indicators">
                                                            <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
                                                                <c:forEach var="image" items="${listImages}" varStatus="loop">
                                                                <li data-target="#carouselIndicators" data-slide-to="${loop.index + 1}"></li>
                                                                </c:forEach>
                                                        </ol>
                                                    </div>
                                                </c:when>
                                                <c:when test="${empty listImages}">
                                                    <div class="carousel-inner">
                                                        <div class="carousel-item active">
                                                            <img src="${productForm.image_path}" class="d-block w-100" alt="Default Image">
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${empty productForm.image_path}">
                                                    <c:forEach var="image" items="${listImages}" varStatus="loop">
                                                        <div class="carousel-item">
                                                            <img class="d-block w-100" src="${image.image}" alt="Slide ${loop.index + 1}">
                                                        </div>
                                                    </c:forEach>
                                                    <ol class="carousel-indicators">
                                                        <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
                                                            <c:forEach var="image" items="${listImages}" varStatus="loop">
                                                            <li data-target="#carouselIndicators" data-slide-to="${loop.index + 1}"></li>
                                                            </c:forEach>
                                                    </ol>
                                                </c:when>
                                            </c:choose>
                                            <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
                                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                            <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
                                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </div>
                                    </div>

                                    <div class="col-12 col-md-6">
                                        <h5>${productForm.name}
                                        </h5>
                                        <p class="text-muted">${productForm.brand}</p>
                                        <div class="ecommerce-details-price d-flex flex-wrap">
                                            <p class="text-primary font-medium-3 mr-1 mb-0">$${productForm.price}</p>
                                        </div>
                                        <hr>
                                        <p>${productForm.description}</p>
                                        <p class="font-weight-bold" id="stockInfo"> <i class="feather icon-database mr-50 font-medium-2"></i>${StockOfProductDetail}</p>
                                        <hr>
                                        <div id="content">
                                            <div class="form-group">
                                                <div class="d-flex align-items-center"> 
                                                    <label class="font-weight-bold mr-1">Color:</label> 
                                                    <div class="product-color-options"> 
                                                        <ul class="list-inline mb-0">
                                                            <c:forEach items="${listC}" var="c">
                                                                <c:set var="ColorClass">
                                                                    <c:choose>
                                                                        <c:when test="${c.color.toLowerCase() == 'black'}">
                                                                            black
                                                                        </c:when>
                                                                        <c:when test="${c.color.toLowerCase() == 'white'}">
                                                                            white
                                                                        </c:when>
                                                                        <c:when test="${c.color.toLowerCase() == 'red'}">
                                                                            danger
                                                                        </c:when>
                                                                        <c:when test="${c.color.toLowerCase() == 'purple'}">
                                                                            primary
                                                                        </c:when>
                                                                        <c:when test="${c.color.toLowerCase() == 'yellow'}">
                                                                            warning
                                                                        </c:when>
                                                                    </c:choose>
                                                                </c:set>
                                                                <c:choose>
                                                                    <c:when test="${not empty listCa}">
                                                                        <c:choose>
                                                                            <c:when test="${!listCa.contains(c)}">
                                                                                <li class="d-inline-block">
                                                                                    <div class="color-option b-${ColorClass}">
                                                                                        <button disabled onclick="selectColor('${c.id}'); return false;" class="filloption" style="background-color: ${c.color}; color: white;">
                                                                                            <span>X</span>
                                                                                        </button>
                                                                                    </div> 
                                                                                </li>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <c:if test="${not empty colorIdA && colorIdA eq c.id}">
                                                                                    <li class="d-inline-block selected">
                                                                                        <div class="color-option b-${ColorClass} ">
                                                                                            <button onclick="selectColor('${c.id}'); return false;" class="filloption" style="background-color: ${c.color}"></button>
                                                                                        </div>
                                                                                    </li>
                                                                                </c:if>
                                                                                <c:if test="${empty colorIdA || colorIdA ne c.id}">
                                                                                    <li class="d-inline-block">
                                                                                        <div class="color-option b-${ColorClass}">
                                                                                            <button onclick="selectColor('${c.id}'); return false;" class="filloption" style="background-color: ${c.color}"></button>
                                                                                        </div>
                                                                                    </li>
                                                                                </c:if>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:if test="${not empty colorIdA && colorIdA eq c.id}">
                                                                            <li class="d-inline-block selected">
                                                                                <div class="color-option b-${ColorClass} ">
                                                                                    <button onclick="selectColor('${c.id}'); return false;" class="filloption" style="background-color: ${c.color}"></button>
                                                                                </div>
                                                                            </li>
                                                                        </c:if>
                                                                        <c:if test="${empty colorIdA || colorIdA ne c.id}">
                                                                            <li class="d-inline-block">
                                                                                <div class="color-option b-${ColorClass}">
                                                                                    <button onclick="selectColor('${c.id}'); return false;" class="filloption" style="background-color: ${c.color}"></button>
                                                                                </div>
                                                                            </li>
                                                                        </c:if>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="d-flex align-items-center"> 
                                                    <label class="font-weight-bold mr-3">Size:</label>
                                                    <ul class="list-unstyled mb-0 mt-0 product-size">
                                                        <div class="product-size" id="productSize">
                                                            <div id="content">
                                                                <c:forEach items="${listS}" var="s">
                                                                    <c:choose>
                                                                        <c:when test="${not empty listSa}">
                                                                            <c:choose>
                                                                                <c:when test="${!listSa.contains(s)}">
                                                                                    <button class="size-option disabled-size btn btn-primary btn-sm" disabled>
                                                                                        ${s.size}
                                                                                    </button>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <c:if test="${not empty sizeIdA && sizeIdA eq s.id}">
                                                                                        <button class="size-option active btn btn-success btn-sm" onclick="selectSize('${s.id}'); return false;">
                                                                                            ${s.size}
                                                                                        </button>
                                                                                    </c:if>
                                                                                    <c:if test="${empty sizeIdA || sizeIdA ne s.id}">
                                                                                        <button class="size-option btn btn-primary btn-sm" onclick="selectSize('${s.id}'); return false;">
                                                                                            ${s.size}
                                                                                        </button>
                                                                                    </c:if>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <c:if test="${not empty sizeIdA && sizeIdA eq s.id}">
                                                                                <button class="size-option active btn btn-success btn-sm" onclick="selectSize('${s.id}'); return false;">
                                                                                    ${s.size}
                                                                                </button>
                                                                            </c:if>
                                                                            <c:if test="${empty sizeIdA || sizeIdA ne s.id}">
                                                                                <button class="size-option btn btn-primary btn-sm" onclick="selectSize('${s.id}'); return false;">
                                                                                    ${s.size}
                                                                                </button>
                                                                            </c:if>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="d-flex align-items-center">
                                                    <label class="font-weight-bold mr-3">Gender:</label>
                                                    <ul class="list-unstyled mb-0 mt-0 product-gender">
                                                        <div class="product-gender">
                                                            <div id="content">
                                                                <c:forEach items="${listG}" var="g">
                                                                    <c:choose>
                                                                        <c:when test="${not empty listGa}">
                                                                            <c:choose>
                                                                                <c:when test="${!listGa.contains(g)}">
                                                                                    <a class="size-option disabled-size btn btn-primary btn-sm" disabled>
                                                                                        ${g.gender}
                                                                                    </a>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <c:if test="${not empty genderIdA && genderIdA eq g.id}">
                                                                                        <button class="size-option btn-success btn-sm" onclick="selectGender('${g.id}'); return false;">
                                                                                            ${g.gender}
                                                                                        </button>
                                                                                    </c:if>
                                                                                    <c:if test="${empty genderIdA || genderIdA ne g.id}">
                                                                                        <button class="size-option btn btn-primary btn-sm" onclick="selectGender('${g.id}'); return false;">
                                                                                            ${g.gender}
                                                                                        </button>
                                                                                    </c:if>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <c:if test="${not empty genderIdA && genderIdA eq g.id}">
                                                                                <button class="size-option btn-success btn-sm" onclick="selectGender('${g.id}'); return false;">
                                                                                    ${g.gender}
                                                                                </button>
                                                                            </c:if>
                                                                            <c:if test="${empty genderIdA || genderIdA ne g.id}">
                                                                                <button class="size-option btn btn-primary btn-sm" onclick="selectGender('${g.id}'); return false;">
                                                                                    ${g.gender}
                                                                                </button>
                                                                            </c:if>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </ul>
                                                </div>
                                            </div> 
                                        </div>
                                        <div class="form-group">
                                            <div class="d-flex align-items-center">
                                                <label class="font-weight-bold mr-3">Brand:</label>
                                                <ul class="list-unstyled mb-0 mt-0 product-brand">
                                                    <li class="d-inline-block">
                                                        <div class="size-option">
                                                            <div class="brand">${productForm.brand}</div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="d-flex align-items-center">
                                                <label class="font-weight-bold mr-3">Category:</label>
                                                <ul class="list-unstyled mb-0 mt-0 product-category">
                                                    <li class="d-inline-block">
                                                        <div class="size-option">
                                                            <div class="category">${productForm.category}</div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>

                                        <hr>
                                        <c:choose>
                                            <c:when test="${StockOfProductDetail != 0}">
                                                <p>Available - <span class="text-success">In stock</span></p>
                                            </c:when>
                                            <c:when test="${StockOfProductDetail == 0}">
                                                <p>Available - <span class="text-danger">Out of stock</span></p>
                                            </c:when>
                                        </c:choose>
                                        <hr>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!-- app ecommerce details end -->
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
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/forms/spinner/jquery.bootstrap-touchspin.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/swiper.min.js"></script>
        <!-- END: Page Vendor JS--> 

        <!-- BEGIN: Theme JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app-menu.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/components.js"></script>
        <!-- END: Theme JS-->

        <!-- BEGIN: Page JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/pages/app-ecommerce-details.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/forms/number-input.js"></script>
        <!-- END: Page JS-->



        <jsp:include page="../admin/logOutModal.jsp"></jsp:include> 

        </body>
        <!-- END: Body-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <<script>

                var selectedColorId;
                var selectedSizeId;
                var selectedGenderId;


                function selectColor(colorId) {
                    selectedColorId = colorId;
                    getProductDetailOfEachProperties('${productForm.id}', selectedColorId, selectedSizeId, selectedGenderId);
                }

                function selectSize(sizeId) {
                    selectedSizeId = sizeId;
                    getProductDetailOfEachProperties('${productForm.id}', selectedColorId, selectedSizeId, selectedGenderId);
                }

                function selectGender(genderId) {
                    selectedGenderId = genderId;
                    getProductDetailOfEachProperties('${productForm.id}', selectedColorId, selectedSizeId, selectedGenderId);
                }

                function getProductDetailOfEachProperties(productId, colorId, sizeId, genderId) {
                    var param1 = productId;
                    var param2 = colorId;
                    var param3 = sizeId;
                    var param4 = genderId;
                    $.ajax({
                        url: "/SWP391_SE1754_G1/productDetailsAjax",
                        type: "GET",
                        data: {
                            productID: param1,
                            colorID: param2,
                            sizeID: param3,
                            genderID: param4
                        },
                        success: function (data) {
                            var content = document.getElementById("body");
                            content.innerHTML = "";
                            content.innerHTML += data;
                        },
                        error: function (xhr) {
                            //
                        }
                    });
                }

    </script>
</html>