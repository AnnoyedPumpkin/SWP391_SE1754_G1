<%-- 
    Document   : cart.jsp
    Created on : Jan 23, 2024, 2:47:24 PM
    Author     : datnt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cart | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/fontawesome.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/order.css" rel="stylesheet"> 

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo/favourite_icon_01.png">

        <!-- fraimwork - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!-- icon - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/fontawesome.css">

        <!-- animation - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/animate.css">

        <!-- nice select - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/nice-select.css">

        <!-- carousel - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/slick.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/slick-theme.css">

        <!-- popup images & videos - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">

        <!-- jquery ui - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/jquery-ui.css">

        <!-- custom - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">

        <style>
            .paged-content {
                width: 100%;
                justify-content: center;
                display: flex;
            }
            .active-paged {
                background-color: #fd7e14 !important;
                font-weight: bold;
            }
        </style>
    </head><!--/head-->

    <body>
        <header class="header_section fashion_minimal_header fixed-header clearfix">
            <div class="header_top clearfix">
                <div class="container-fluid prl_100">
                    <div class="row align-items-center">
                        <div class="col-lg-7">
                            <ul class="contact_info ul_li clearfix">
                                <li>081-719-3222</li>
                                <li>Support@domain.com</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="header_bottom clearfix">
                <div class="container-fluid prl_100">
                    <div class="row align-items-center justify-content-lg-between">

                        <div class="col-lg-4">
                            <ul class="btns_group ul_li text-uppercase clearfix">
                                <li>
                                    <button type="button" class="mobile_menu_btn">
                                        <i class="fal fa-bars me-1"></i>
                                        MENU
                                    </button>
                                </li>
                                <li><a href="#!">Women</a></li>
                                <li><a href="#!">Men</a></li>
                            </ul>
                        </div>

                        <div class="col-lg-4">
                            <div class="brand_logo text-center">
                                <a class="brand_link" href="${pageContext.request.contextPath}/home">
                                    <img src="${pageContext.request.contextPath}/assets/images/logo/logo_20_1x.png"
                                         srcset="${pageContext.request.contextPath}/assets/images/logo/logo_20_2x.png 2x" alt="logo_not_found">
                                </a>

                                <ul class="mh_action_btns ul_li clearfix">
                                    <li>
                                        <button type="button" class="search_btn" data-toggle="collapse"
                                                data-target="#search_body_collapse" aria-expanded="false"
                                                aria-controls="search_body_collapse">
                                            <i class="fal fa-search"></i>
                                        </button>
                                    </li>
                                    <li>
                                        <button type="button" class="cart_btn">
                                            <i class="fal fa-shopping-cart"></i>
                                            <span class="btn_badge">2</span>
                                        </button>
                                    </li>
                                    <li><button type="button" class="mobile_menu_btn"><i class="far fa-bars"></i></button>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-4">
                            <ul class="action_btns_group ul_li_right clearfix">
                                <li>
                                    <button type="button" class="search_btn" data-toggle="collapse"
                                            data-target="#search_body_collapse" aria-expanded="false"
                                            aria-controls="search_body_collapse">
                                        <i class="fal fa-search"></i>
                                    </button>
                                </li>

                                <li>
                                    <button type="button" class="user_btn" data-toggle="collapse"
                                            data-target="#use_deropdown" aria-expanded="false" aria-controls="use_deropdown">
                                        <i class="fal fa-user"></i>
                                    </button>
                                    <div id="use_deropdown" class="collapse_dropdown collapse">
                                        <div class="dropdown_content">
                                            <c:choose>
                                                <c:when test="${user != null}" >
                                                    <div class="profile_info clearfix">
                                                        <div class="user_thumbnail">
                                                            <img src="assets/images/meta/img_01.png" alt="thumbnail_not_found">
                                                        </div>

                                                        <div class="user_content">
                                                            <h4 class="user_name">${user.userName}</h4>
                                                            <span class="user_title">${user.phone_number}</span>
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="profile_info clearfix">
                                                        <div class="user_thumbnail">
                                                            <img src="assets/images/meta/img_01.png" alt="thumbnail_not_found">
                                                        </div>
                                                        <div class="user_content">
                                                            <h4 class="user_name">
                                                                <a href="${pageContext.request.contextPath}/authen">Login</a>
                                                            </h4>
                                                        </div>
                                                    </div>
                                                </c:otherwise>

                                            </c:choose>
                                            <ul class="settings_options ul_li_block clearfix">
                                                <li><a href="profile"><i class="fal fa-user-circle"></i> Profile</a></li>
                                                <li><a href="#!"><i class="fal fa-user-cog"></i> Settings</a></li>
                                                <li><a href="#!"><i class="fal fa-sign-out-alt"></i> Logout</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>

                                <li><button type="button"><i class="fal fa-heart"></i></button></li>

                                <li>
                                    <button type="button" class="cart_btn">
                                        <i class="fal fa-shopping-cart"></i>
                                        <span class="btn_badge">2</span>
                                    </button>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>

            <div id="search_body_collapse" class="search_body_collapse collapse">
                <div class="search_body">
                    <div class="container-fluid prl_100">
                        <form action="#">
                            <div class="form_item mb-0">
                                <input type="search" name="search" placeholder="Type here...">
                                <button type="submit"><i class="fal fa-search"></i></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </header>
        <section style="margin-top: 100px" id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">History Order</li>
                    </ol>
                </div>
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="orderId">OrderId</td>
                                <td class="dateOrder">Date</td>
                                <td class="address">TotalPrice</td>
                                <td class="totalPrice">CartCode</td>                                
                                <td class="totalPrice">Status</td>

                                <td>Action</td>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="o">
                                <tr>
                                    <td class="cart_product">
                                        <a href="">${o.id}</a>
                                    </td>
                                    <td class="cart_description">
                                        <h4><a href="">${o.invoice_Date}</a></h4>
                                    </td>
                                    <td class="cart_price">
                                        <p><fmt:formatNumber type="currency" value="${o.total_price}" />vnd</p>
                                    </td>
                                    <td class="cart_total">
                                        <p class="cart_status">${o.cartCode}</p>
                                    </td>
                                    <td class="cart_total">
                                        <p class="cart_status">${o.status}</p>
                                    </td>
                                    <td class="cart_delete">
                                        <a class="cart_quantity_delete" href="order?action=invoiceDetail&invoiceId=${o.id}">View</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination_wrap text-center clearfix">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center paged-content">
                                <c:choose>
                                    <c:when test ="${pageSelected - 1 < 1}">
                                        <li class="page-item disabled">
                                            <a  class="page-link"  tabindex="-1" aria-disabled="true">Previous</a>
                                        </li>

                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a href="order?action=invoiceHistory&index=${pageSelected -1}" class="page-link">Previous</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach begin="1" end="${endPage}" var="i">
                                    <li class="page-item"><a class="${pageSelected == i ? "page-link active-paged" : "page-link"}" href="order?action=invoiceHistory&index=${i}">${i}</a></li>
                                    </c:forEach>

                                <c:choose>
                                    <c:when test ="${pageSelected >= endPage}">
                                        <li class="page-item disabled">
                                            <a  class="page-link"  tabindex="-1" aria-disabled="true">Next</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a href="order?action=invoiceHistory&index=${pageSelected + 1}" class="page-link">Next</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>

                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </section> <!--/#cart_items-->

        <section id="do_action">
            <div class="container">
                <div class="heading">
                    <h3>What would you like to do next?</h3>
                    <p>Choose if you have a discount code or reward points you want to use or would like to estimate your delivery cost.</p>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="chose_area">
                            <ul class="user_option">
                                <li>
                                    <input type="checkbox">
                                    <label>Use Coupon Code</label>
                                </li>
                                <li>
                                    <input type="checkbox">
                                    <label>Use Gift Voucher</label>
                                </li>
                                <li>
                                    <input type="checkbox">
                                    <label>Estimate Shipping & Taxes</label>
                                </li>
                            </ul>
                            <ul class="user_info">
                                <li class="single_field">
                                    <label>Country:</label>
                                    <select>
                                        <option>United States</option>
                                        <option>Bangladesh</option>
                                        <option>UK</option>
                                        <option>India</option>
                                        <option>Pakistan</option>
                                        <option>Ucrane</option>
                                        <option>Canada</option>
                                        <option>Dubai</option>
                                    </select>

                                </li>
                                <li class="single_field">
                                    <label>Region / State:</label>
                                    <select>
                                        <option>Select</option>
                                        <option>Dhaka</option>
                                        <option>London</option>
                                        <option>Dillih</option>
                                        <option>Lahore</option>
                                        <option>Alaska</option>
                                        <option>Canada</option>
                                        <option>Dubai</option>
                                    </select>

                                </li>
                                <li class="single_field zip-field">
                                    <label>Zip Code:</label>
                                    <input type="text">
                                </li>
                            </ul>
                            <a class="btn btn-default update" href="">Get Quotes</a>
                            <a class="btn btn-default check_out" href="">Continue</a>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="total_area">
                            <ul>
                                <li>Cart Sub Total <span>$59</span></li>
                                <li>Eco Tax <span>$2</span></li>
                                <li>Shipping Cost <span>Free</span></li>
                                <li>Total <span>$61</span></li>
                            </ul>
                            <a class="btn btn-default update" href="">Update</a>
                            <a class="btn btn-default check_out" href="">Check Out</a>
                        </div>
                    </div>
                </div>
            </div>
        </section><!--/#do_action-->

        <div class="sidebar_mobile_menu">
            <button type="button" class="close_btn"><i class="fal fa-times"></i></button>

            <div class="msb_widget brand_logo text-center">
                <a href="index.html">
                    <img src="${pageContext.request.contextPath}/assets/images/logo/logo_25_1x.png" srcset="${pageContext.request.contextPath}/assets/images/logo/logo_25_2x.png 2x"
                         alt="logo_not_found">
                </a>
            </div>

            <div class="msb_widget mobile_menu_list clearfix">
                <h3 class="title_text mb_15 text-uppercase"><i class="far fa-bars me-2"></i> Menu List</h3>
                <ul class="ul_li_block clearfix">
                    <li class="active dropdown">
                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Home</a>
                        <ul class="ul_li_block dropdown-menu">
                            <li><a href="home_carparts.html">Carparts</a></li>
                            <li><a href="home_classic_ecommerce.html">Classic Ecommerce</a></li>
                            <li><a href="home_creative_onelook.html">Creative Onelook</a></li>
                            <li><a href="home_electronic.html">Electronic</a></li>
                            <li><a href="home_fashion.html">Fashion</a></li>
                            <li><a href="home_fashion_minimal.html">Fashion Minimal</a></li>
                            <li><a href="home_furniture.html">Furniture</a></li>
                            <li><a href="home_gadget.html">Gadget</a></li>
                            <li><a href="home_lookbook_creative.html">Lookbook Creative</a></li>
                            <li><a href="home_lookbook_slide.html">Lookbook Slide</a></li>
                            <li><a href="home_medical.html">Medical</a></li>
                            <li><a href="home_modern.html">Modern</a></li>
                            <li><a href="home_modern_minimal.html">Modern Minimal</a></li>
                            <li><a href="home_motorcycle.html">Motorcycle</a></li>
                            <li><a href="home_parallax_shop.html">Parallax Shop</a></li>
                            <li><a href="home_simple_shop.html">Simple Shop</a></li>
                            <li><a href="home_single_story_black.html">Single Story Black</a></li>
                            <li><a href="home_single_story_white.html">Single Story White</a></li>
                            <li><a href="home_sports.html">Sports</a></li>
                            <li><a href="home_supermarket.html">Supermarket</a></li>
                            <li><a href="home_watch.html">Watch</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
                        <ul class="dropdown-menu">
                            <li class="dropdown ul_li_block">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Carparts</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="carparts_shop.html">Shop Page</a></li>
                                    <li><a href="carparts_shop_grid.html">Shop Grid</a></li>
                                    <li><a href="carparts_shop_list.html">Shop List</a></li>
                                    <li><a href="carparts_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Classic Ecommerce</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="classic_ecommerce_shop.html">Shop Page</a></li>
                                    <li><a href="classic_ecommerce_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Electronic</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="electronic_shop.html">Shop Page</a></li>
                                    <li><a href="electronic_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Fashion</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="fashion_shop.html">Shop Page</a></li>
                                    <li><a href="fashion_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Fashion Minimal</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="fashion_minimal_shop.html">Shop Page</a></li>
                                    <li><a href="fashion_minimal_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Fashion Minimal</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="fashion_minimal_shop.html">Shop Page</a></li>
                                    <li><a href="fashion_minimal_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Furniture</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="furniture_shop.html">Shop Page</a></li>
                                    <li><a href="furniture_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Gadget</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="gadget_shop.html">Shop Page</a></li>
                                    <li><a href="gadget_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Medical</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="medical_shop.html">Shop Page</a></li>
                                    <li><a href="medical_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Modern Minimal</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="modern_minimal_shop.html">Shop Page</a></li>
                                    <li><a href="modern_minimal_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Modern</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="modern_shop.html">Shop Page</a></li>
                                    <li><a href="modern_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Motorcycle</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="motorcycle_shop_grid.html">Shop Grid</a></li>
                                    <li><a href="motorcycle_shop_list.html">Shop List</a></li>
                                    <li><a href="motorcycle_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Simple Shop</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="simple_shop.html">Shop Page</a></li>
                                    <li><a href="simple_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Sports</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="sports_shop.html">Shop Page</a></li>
                                    <li><a href="sports_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Lookbook</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="lookbook_creative_shop.html">Shop Page</a></li>
                                    <li><a href="lookbook_creative_shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop
                                    Other Pages</a>
                                <ul class="dropdown-menu ul_li_block">
                                    <li><a href="#!"><del>Shop Page</del></a></li>
                                    <li><a href="shop_details.html">Shop Details</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Pages</a>
                        <ul class="dropdown-menu">
                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop
                                    Inner Pages</a>
                                <ul class="dropdown-menu">
                                    <li><a href="shop_cart.html">Shopping Cart</a></li>
                                    <li><a href="shop_checkout.html">Checkout Step 1</a></li>
                                    <li><a href="shop_checkout_step2.html">Checkout Step 2</a></li>
                                    <li><a href="shop_checkout_step3.html">Checkout Step 3</a></li>
                                </ul>
                            </li>
                            <li><a href="404.html">404 Page</a></li>
                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Blogs</a>
                                <ul class="dropdown-menu">
                                    <li><a href="blog.html">Blog Page</a></li>
                                    <li><a href="blog_details.html">Blog Details</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Compare</a>
                                <ul class="dropdown-menu">
                                    <li><a href="compare_1.html">Compare V.1</a></li>
                                    <li><a href="compare_2.html">Compare V.2</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#!" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Register</a>
                                <ul class="dropdown-menu">
                                    <li><a href="login.html">Login</a></li>
                                    <li><a href="signup.html">Sign Up</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li><a href="contact.html">Conatct</a></li>
                </ul>
            </div>

            <div class="user_info">
                <!--                        <h3 class="title_text mb_30 text-uppercase"><i class="fas fa-user me-2"></i> User Info</h3>
                                        <div class="profile_info clearfix">
                                            <div class="user_thumbnail">
                                                <img src="assets/images/meta/img_01.png" alt="thumbnail_not_found">
                                            </div>
                                            <div class="user_content">
                                                <h4 class="user_name">Jone Doe</h4>
                                                <span class="user_title">Seller</span>
                                            </div>
                                        </div>
                -->
                <c:choose>
                    <c:when test="${user != null}" >
                        <div class="profile_info clearfix">
                            <div class="user_thumbnail">
                                <img src="${pageContext.request.contextPath}/assets/images/meta/img_01.png" alt="thumbnail_not_found">
                            </div>

                            <div class="user_content">
                                <h4 class="user_name">${user.userName}</h4>
                                <span class="user_title">${user.phone_number}</span>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="profile_info clearfix">
                            <div class="user_thumbnail">
                                <img src="${pageContext.request.contextPath}/assets/images/meta/img_01.png" alt="thumbnail_not_found">
                            </div>

                            <div class="user_content">
                                <h4 class="user_name">
                                    <a href="${pageContext.request.contextPath}/authen">Login</a>
                                </h4>
                            </div>
                        </div>
                    </c:otherwise>

                </c:choose>
                <ul class="settings_options ul_li_block clearfix">
                    <li><a href="profile"><i class="fal fa-user-circle"></i> Profile</a></li>
                    <li><a href="#!"><i class="fal fa-user-cog"></i> Settings</a></li>
                    <li><a href="#!"><i class="fal fa-sign-out-alt"></i> Logout</a></li>
                </ul>
            </div>
        </div>

        <div class="overlay"></div>

        <footer class="footer_section fashion_minimal_footer clearfix" data-bg-color="#222222">
            <div class="backtotop" data-background="${pageContext.request.contextPath}/assets/images/shape_01.png">
                <a href="#" class="scroll">
                    <i class="far fa-arrow-up"></i>
                </a>
            </div>

            <div class="footer_widget_area sec_ptb_100 clearfix">
                <div class="container">
                    <div class="row justify-content-lg-between">

                        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                            <div class="footer_widget footer_about">
                                <div class="brand_logo mb_30">
                                    <a href="#!">
                                        <img src="${pageContext.request.contextPath}/assets/images/logo/logo_21_1x.png"
                                             srcset="${pageContext.request.contextPath}/assets/images/logo/logo_21_2x.png 2x" alt="logo_not_found">
                                    </a>
                                </div>
                                <p class="mb-0">
                                    Etiam rhoncus sit amet adip
                                    scing sed ipsum. Lorem ipsum
                                    dolor sit amet adipiscing
                                    sem neque.
                                </p>
                            </div>
                        </div>

                        <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
                            <div class="row justify-content-lg-between">
                                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                                    <div class="footer_widget footer_useful_links clearfix">
                                        <h3 class="footer_widget_title text-white">Contact</h3>
                                        <ul class="ul_li_block">
                                            <li><i class="fal fa-phone-square"></i> 666 888 0000</li>
                                            <li><i class="fal fa-envelope"></i> Jthemes@gmail.com</li>
                                            <li><i class="fal fa-map"></i> 66 top broklyn street new york</li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                                    <div class="footer_widget footer_useful_links clearfix">
                                        <h3 class="footer_widget_title text-white">Links</h3>
                                        <ul class="ul_li_block">
                                            <li><a href="#!">About</a></li>
                                            <li><a href="#!">Contact</a></li>
                                            <li><a href="#!">Our Gallery</a></li>
                                            <li><a href="#!">Programs</a></li>
                                            <li><a href="#!">Events</a></li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                                    <div class="footer_widget footer_useful_links clearfix">
                                        <h3 class="footer_widget_title text-white">Activities</h3>
                                        <ul class="ul_li_block">
                                            <li><a href="#!">Table/Floor Toys</a></li>
                                            <li><a href="#!">Outdoor Games</a></li>
                                            <li><a href="#!">Sand Play</a></li>
                                            <li><a href="#!">Play Dough</a></li>
                                            <li><a href="#!">Building Blocks</a></li>
                                            <li><a href="#!">Water Play</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                            <div class="footer_widget fm_footer_newsletter">
                                <h3 class="footer_widget_title text-white">Activities</h3>
                                <form action="#">
                                    <div class="form_item">
                                        <input type="email" name="email" placeholder="Email address">
                                    </div>
                                    <button type="submit" class="submit_btn text-uppercase">Subscribe</button>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>


    </body>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

    <!-- mobile menu - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/mCustomScrollbar.js"></script>

    <!-- google map - jquery include -->
    <script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDk2HrmqE4sWSei0XdKGbOMOHN3Mm2Bf-M&ver=2.1.6"></script>
    <script src="${pageContext.request.contextPath}/assets/js/gmaps.min.js"></script>

    <!-- animation - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/parallaxie.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/wow.min.js"></script>

    <!-- nice select - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/nice-select.min.js"></script>

    <!-- carousel - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/slick.min.js"></script>

    <!-- countdown timer - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/countdown.js"></script>

    <!-- popup images & videos - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/magnific-popup.min.js"></script>

    <!-- filtering & masonry layout - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/isotope.pkgd.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/masonry.pkgd.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/imagesloaded.pkgd.min.js"></script>

    <!-- jquery ui - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery-ui.js"></script>

    <!-- custom - jquery include -->
    <script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
</html>
