<%-- 
    Document   : manageUser
    Created on : Mar 5, 2024, 10:01:29 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>User List - Vuexy - Bootstrap HTML admin template</title>
        <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">

        <!-- BEGIN: Vendor CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/vendors.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/tables/ag-grid/ag-grid.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/tables/ag-grid/ag-theme-material.css">
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/pages/app-user.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/css/pages/aggrid.css">
        <!-- END: Page CSS-->

        <!-- BEGIN: Custom CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">
        <!-- END: Custom CSS-->

    </head>
    <!-- END: Head-->

    <!-- BEGIN: Body-->

    <body class="vertical-layout vertical-menu-modern dark-layout 2-columns  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="2-columns" data-layout="dark-layout">

        <!-- BEGIN: Header-->
        <nav class="header-navbar navbar-expand-lg navbar navbar-with-menu floating-nav navbar-dark navbar-shadow">
            <div class="navbar-wrapper">
                <div class="navbar-container content">
                    <div class="navbar-collapse" id="navbar-mobile">
                        <div class="mr-auto float-left bookmark-wrapper d-flex align-items-center">
                            <ul class="nav navbar-nav">
                                <li class="nav-item mobile-menu d-xl-none mr-auto"><a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#"><i class="ficon feather icon-menu"></i></a></li>
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
                    <li class="nav-item mr-auto"><a class="navbar-brand" href="${pageContext.request.contextPath}/html/ltr/vertical-menu-template-dark/index.html">
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
                            <li class=""><a href="${pageContext.request.contextPath}/admin/dashboard"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Analytics">Analytics</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" navigation-header"><span>Apps</span>
                    </li>
                    <li class=" nav-item"><a href="#"><i class="feather icon-shopping-cart"></i><span class="menu-title" data-i18n="Ecommerce">Ecommerce</span></a>
                        <ul class="menu-content">
                            <li><a href="${pageContext.request.contextPath}/admin/manageproduct"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Shop">Manage Products</span></a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/admin/dashboard?page=view-details"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Details">Manage Product Characteristics</span></a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/admin/dashboard?page=manage-discount"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Details">Manage Discount</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" nav-item"><a href="#"><i class="feather icon-user"></i><span class="menu-title" data-i18n="User">User</span></a>
                        <ul class="menu-content">
                            <li class="active"><a href="${pageContext.request.contextPath}/admin/dashboard?page=manageUser"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="List">List</span></a>
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
            </div>
        </div>
        <!-- END: Main Menu-->

        <!-- BEGIN: Content-->
        <div class="app-content content">
            <div class="content-overlay"></div>
            <div class="header-navbar-shadow"></div>
            <div class="content-wrapper">
                <div class="content-header row">
                </div>
                <div class="content-body">
                    <!-- users list start -->
                    <section class="users-list-wrapper">
                        <!-- users filter start -->
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Filters</h4>
                                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li><a data-action="collapse"><i class="feather icon-chevron-down"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body">
                                    <div class="users-list-filter">
                                        <form>
                                            <div class="row">
                                                <div class="col-12 col-sm-6 col-lg-3">
                                                    <label for="users-list-role">Role</label>
                                                    <fieldset class="form-group">
                                                        <select class="form-control" id="users-list-role" onchange="location = this.value;">
                                                            <option value="">All</option>
                                                            <option value="dashboard?page=manageUser">User</option>
                                                            <option value="staff">Staff</option>
                                                        </select>
                                                    </fieldset>
                                                </div>
                                                <div class="col-12 col-sm-6 col-lg-3">
                                                    <label for="users-list-status">Status</label>
                                                    <fieldset class="form-group">
                                                        <select class="form-control" id="users-list-status">
                                                            <option value="">All</option>
                                                            <option value="Active">Active</option>
                                                            <option value="Blocked">Blocked</option>
                                                            <option value="deactivated">Deactivated</option>
                                                        </select>
                                                    </fieldset>
                                                </div>
                                                <div class="col-12 col-sm-6 col-lg-3">
                                                    <label for="users-list-verified">Verified</label>
                                                    <fieldset class="form-group">
                                                        <select class="form-control" id="users-list-verified">
                                                            <option value="">All</option>
                                                            <option value="true">Yes</option>
                                                            <option value="false">No</option>
                                                        </select>
                                                    </fieldset>
                                                </div>
                                                <div class="col-12 col-sm-6 col-lg-3">
                                                    <label for="users-list-department">Department</label>
                                                    <fieldset class="form-group">
                                                        <select class="form-control" id="users-list-department">
                                                            <option value="">All</option>
                                                            <option value="Sales">Sales</option>
                                                            <option value="Devlopment">Devlopment</option>
                                                            <option value="Management">Management</option>
                                                        </select>
                                                    </fieldset>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- users filter end -->
                        <!-- Ag Grid users list section start -->
                        <div id="basic-examples">
                            <div class="card">
                                <div class="card-content">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="ag-grid-btns d-flex justify-content-between flex-wrap mb-1">
                                                    <div class="dropdown sort-dropdown mb-1 mb-sm-0">
                                                        <select id="pageSize" class="btn btn-white filter-btn dropdown-toggle border text-dark" type="button" id="dropdownMenuButton6" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            1 - 10 of 20
                                                            <option class="dropdown-item" value="5">5</option>
                                                            <option class="dropdown-item" value="10">10</option>
                                                            <option class="dropdown-item" value="20">20</option>
                                                        </select>
                                                    </div>
                                                    <div class="ag-btns d-flex flex-wrap">
                                                        <input type="text" class="ag-grid-filter form-control w-50 mr-1 mb-1 mb-sm-0" id="filter-text-box" placeholder="Search...." />
                                                        <div class="btn-export">
                                                            <button class="btn btn-primary ag-grid-export-btn">
                                                                Export as CSV
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- List Users Starts -->
                                        <div class="table-responsive">
                                            <c:if test="${not empty status && status ne 'null' && status eq '0'}">
                                                <h4 style="color: red; font-style: italic;">***Warning: Any products after change hide status of product will be deleted after 15 days since the day you changed status.</h4> 
                                            </c:if>
                                            <table class="table custom-table table-striped ">
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th class="align-middle">Username</th>
                                                        <th class="align-middle">Email</th>
                                                        <th class="align-middle">Phone number</th>
                                                        <th class="align-middle">Gender</th>
                                                        <th class="align-middle">Date of birth</th>
                                                        <th class="align-middle">Address</th>
                                                        <th class="align-middle">Role</th>
                                                        <th class="align-middle">Status</th>
                                                        <th class="align-middle text-center">Options</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${listAf}" var="a">
                                                        <tr>
                                                            <td class="align-middle border-bottom">${a.username}</td>
                                                            <td class="align-middle px-2 py-2 border-bottom">${a.email}</td>
                                                            <td class="align-middle border-bottom">${a.phone_number}</td>
                                                            <td class="align-middle border-bottom">${a.gender}</td>
                                                            <td class="align-middle px-1 py-1 border-bottom">${a.dob}</td>
                                                            <td class="align-middle px-2 py-2 border-bottom">${a.address}</td>
                                                            <td class="align-middle px-2 py-2 border-bottom">${a.role}</td>
                                                            <td class="align-middle px-2 py-2 border-bottom">${a.status}</td>
                                                            <td class="align-middle item-options px-2 py-2 border-bottom  text-center">
                                                                <div class="btn-group" role="group" aria-label="Product Options">
                                                                    <a class="btn btn-success btn-custom-size view-options" href="${pageContext.request.contextPath}/admin/manageproduct?page=view-product-details&productID=${p.id}">
                                                                        <i class="feather icon-external-link"></i> <span class="ml-1" style="color:white">View</span>
                                                                    </a>
                                                                    <button class="btn btn-primary btn-custom-size delete" data-toggle="modal" data-target="#editRoleUserModal" title="Edit Role User" onclick="editRoleUserModal('${a.id}')">
                                                                        <i class="feather icon-edit"></i> <span class="ml-1" style="color:white">Update Role</span>
                                                                    </button>
<!--                                                                    <button class="btn btn-primary btn-custom-size delete" data-toggle="modal" data-target="#editStatusProductModal" title="Edit Status Product" onclick="editStatusProductModal('${p.id}', '${p.status}')">
                                                                    <i class="feather icon-eye-off"></i> <span class="ml-1" style="color:white">Hide</span>
                                                                </button>
                                                                <button class="btn btn-primary btn-custom-size delete" data-toggle="modal" data-target="#editStatusProductModal" title="Edit Status Product" onclick="editStatusProductModal('${p.id}', '${p.status}')">
                                                                    <i class="feather icon-eye"></i> <span class="ml-1" style="color:white">Display</span>
                                                                </button>-->
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- List Users Ends -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Ag Grid users list section end -->
                    </section>
                    <!-- users list ends -->

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
        <script>


        </script>



        <!-- BEGIN: Vendor JS-->
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/vendors.min.js"></script>
        <!-- BEGIN Vendor JS-->

        <!-- BEGIN: Page Vendor JS-->
        <script src="${pageContext.request.contextPath}/app-assets/vendors/js/tables/ag-grid/ag-grid-community.min.noStyle.js"></script>
        <!-- END: Page Vendor JS-->

        <!-- BEGIN: Theme JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app-menu.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/components.js"></script>
        <!-- END: Theme JS-->

        <!-- BEGIN: Page JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/pages/app-user.js"></script>
        <!-- END: Page JS-->

        <jsp:include page="../admin/logOutModal.jsp"></jsp:include> 
    </body>
    <!-- END: Body-->

</html>
