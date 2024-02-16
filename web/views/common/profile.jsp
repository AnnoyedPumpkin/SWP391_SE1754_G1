<%-- 
    Document   : login
    Created on : Jan 11, 2024, 12:32:33 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">

        <title>Profile</title>
        <link rel="shortcut icon" href="assets/images/logo/favourite_icon_01.png">

        <!-- fraimwork - css include -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

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

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            body{
                background: #f7f7ff;
                margin-top:20px;
            }
            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0 solid transparent;
                border-radius: .25rem;
                margin-bottom: 1.5rem;
                box-shadow: 0 2px 6px 0 rgb(218 218 253 / 65%), 0 2px 6px 0 rgb(206 206 238 / 54%);
            }
            .me-2 {
                margin-right: .5rem!important;
            }
        </style>
    </head>


    <body>
        <!-- backtotop - start -->
        <div id="thetop"></div>
        <div class="backtotop bg_default_red">
            <a href="#" class="scroll">
                <i class="far fa-arrow-up"></i>
            </a>
        </div>
        <!-- backtotop - end -->

        <!-- preloader - start -->
        <!-- <div id="preloader"></div> -->
        <!-- preloader - end -->


        <!-- header_section - start
        ================================================== -->
        <header class="header_section default_header sticky_header clearfix">
            <div class="header_top text-white" data-bg-color="#000000">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-7">
                            <p class="mb-0">Free Shipping on Domestic Orders over $150</p>
                        </div>

                        <div class="col-lg-5">
                            <ul class="primary_social_links ul_li_right clearfix">
                                <li><a href="#!"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href="#!"><i class="fab fa-twitter"></i></a></li>
                                <li><a href="#!"><i class="fab fa-instagram"></i></a></li>
                                <li><a href="#!"><i class="fab fa-whatsapp"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="header_bottom clearfix">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-3">
                            <div class="brand_logo">
                                <a class="brand_link" href="index.html">
                                    <img src="${pageContext.request.contextPath}/assets/images/logo/logo_27_1x.png" srcset="assets/images/logo/logo_27_2x.png 2x" alt="logo_not_found">
                                </a>

                                <ul class="mh_action_btns ul_li clearfix">
                                    <li>
                                        <button type="button" class="search_btn" data-toggle="collapse" data-target="#search_body_collapse" aria-expanded="false" aria-controls="search_body_collapse">
                                            <i class="fal fa-search"></i>
                                        </button>
                                    </li>
                                    <li>
                                        <button type="button" class="cart_btn">
                                            <i class="fal fa-shopping-cart"></i>
                                            <span class="btn_badge">2</span>
                                        </button>
                                    </li>
                                    <li><button type="button" class="mobile_menu_btn"><i class="far fa-bars"></i></button></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <nav class="main_menu clearfix">
                                <ul class="ul_li_center clearfix">
                                    <li class="menu_item_has_child">
                                        <a href="#!">Home</a>
                                        <div class="mega_menu text-center">
                                            <div class="background" data-bg-color="#ffffff">
                                                <div class="container">
                                                    <ul class="home_pages ul_li clearfix">
                                                        <li>
                                                            <a href="home_carparts.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/carparts.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Car Parts</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_classic_ecommerce.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/classic_ecommarce.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Classic Ecommerce</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_creative_onelook.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/creative_onelook.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Creative Onelook</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_electronic.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/electronic.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Electronic</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_fashion.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/fashion.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Fashion</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_fashion_minimal.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/fashion_minimal.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Fashion Minimal</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_furniture.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/furniture.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Furniture</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_gadget.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/gadget.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Gadget</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_lookbook_creative.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/lookbook_creative.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Lookbook Creative</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_lookbook_slide.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/lookbook_slide.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Lookbook Slide</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_medical.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/medical.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Medical</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_modern.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/modern.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Modern</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_modern_minimal.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/modern_minimal.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Modern Minimal</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_motorcycle.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/motorcycle.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Motorcycle</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_parallax_shop.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/parallax_shop.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Parallax Shop</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_simple_shop.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/simple_shop.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Simple Shop</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_single_story_black.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/single_story_black.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Single Story Black</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_single_story_white.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/single_story_white.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Single Story White</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_sports.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/sports.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Sports Shop</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_supermarket.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/supermarket.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Supermarket</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="home_watch.html">
                                                                <span class="item_image">
                                                                    <img src="${pageContext.request.contextPath}/assets/images/home_pages/watch.png" alt="image_not_found">
                                                                </span>
                                                                <span class="item_title">Watch</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="menu_item_has_child">
                                        <a href="#!">Shop</a>
                                        <div class="mega_menu">
                                            <div class="background" data-bg-color="#ffffff">
                                                <div class="container">
                                                    <div class="row mt__30">
                                                        <div class="col-lg-3">
                                                            <div class="page_links">
                                                                <h3 class="title_text">Carparts</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="carparts_shop.html">Shop Page</a></li>
                                                                    <li><a href="carparts_shop_grid.html">Shop Grid</a></li>
                                                                    <li><a href="carparts_shop_list.html">Shop List</a></li>
                                                                    <li><a href="carparts_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Classic Ecommerce</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="classic_ecommerce_shop.html">Shop Page</a></li>
                                                                    <li><a href="classic_ecommerce_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Electronic</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="electronic_shop.html">Shop Page</a></li>
                                                                    <li><a href="electronic_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Fashion</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="fashion_shop.html">Shop Page</a></li>
                                                                    <li><a href="fashion_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-3">
                                                            <div class="page_links">
                                                                <h3 class="title_text">Fashion Minimal</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="fashion_minimal_shop.html">Shop Page</a></li>
                                                                    <li><a href="fashion_minimal_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Fashion Minimal</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="fashion_minimal_shop.html">Shop Page</a></li>
                                                                    <li><a href="fashion_minimal_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Furniture</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="furniture_shop.html">Shop Page</a></li>
                                                                    <li><a href="furniture_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Gadget</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="gadget_shop.html">Shop Page</a></li>
                                                                    <li><a href="gadget_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-3">
                                                            <div class="page_links">
                                                                <h3 class="title_text">Medical</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="medical_shop.html">Shop Page</a></li>
                                                                    <li><a href="medical_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Modern Minimal</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="modern_minimal_shop.html">Shop Page</a></li>
                                                                    <li><a href="modern_minimal_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Modern</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="modern_shop.html">Shop Page</a></li>
                                                                    <li><a href="modern_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Motorcycle</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="motorcycle_shop_grid.html">Shop Grid</a></li>
                                                                    <li><a href="motorcycle_shop_list.html">Shop List</a></li>
                                                                    <li><a href="motorcycle_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-3">
                                                            <div class="page_links">
                                                                <h3 class="title_text">Simple Shop</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="simple_shop.html">Shop Page</a></li>
                                                                    <li><a href="simple_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Sports</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="sports_shop.html">Shop Page</a></li>
                                                                    <li><a href="sports_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Lookbook</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="lookbook_creative_shop.html">Shop Page</a></li>
                                                                    <li><a href="lookbook_creative_shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>

                                                            <div class="page_links">
                                                                <h3 class="title_text">Shop Other Pages</h3>
                                                                <ul class="ul_li_block">
                                                                    <li><a href="#!"><del>Shop Page</del></a></li>
                                                                    <li><a href="shop_details.html">Shop Details</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="menu_item_has_child">
                                        <a href="#!">Pages</a>
                                        <ul class="submenu">
                                            <li class="menu_item_has_child">
                                                <a href="#!">Shop Inner Pages</a>
                                                <ul class="submenu">
                                                    <li><a href="shop_cart.html">Shopping Cart</a></li>
                                                    <li><a href="shop_checkout.html">Checkout Step 1</a></li>
                                                    <li><a href="shop_checkout_step2.html">Checkout Step 2</a></li>
                                                    <li><a href="shop_checkout_step3.html">Checkout Step 3</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="404.html">404 Page</a></li>
                                            <li class="menu_item_has_child">
                                                <a href="#!">Blogs</a>
                                                <ul class="submenu">
                                                    <li><a href="blog.html">Blog Page</a></li>
                                                    <li><a href="blog_details.html">Blog Details</a></li>
                                                </ul>
                                            </li>
                                            <li class="menu_item_has_child">
                                                <a href="#!">Compare</a>
                                                <ul class="submenu">
                                                    <li><a href="compare_1.html">Compare V.1</a></li>
                                                    <li><a href="compare_2.html">Compare V.2</a></li>
                                                </ul>
                                            </li>
                                            <li class="menu_item_has_child">
                                                <a href="#!">Register</a>
                                                <ul class="submenu">
                                                    <li><a href="login.html">Login</a></li>
                                                    <li><a href="signup.html">Sign Up</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a href="#!">About us</a></li>
                                    <li><a href="contact.html">Contact us</a></li>
                                </ul>
                            </nav>
                        </div>

                        <div class="col-lg-3">
                            <ul class="action_btns_group ul_li_right clearfix">
                                <li>
                                    <button type="button" class="search_btn" data-toggle="collapse" data-target="#search_body_collapse" aria-expanded="false" aria-controls="search_body_collapse">
                                        <i class="fal fa-search"></i>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="user_btn" data-toggle="collapse" data-target="#use_deropdown" aria-expanded="false" aria-controls="use_deropdown">
                                        <i class="fal fa-user"></i>
                                    </button>
                                    <div id="use_deropdown" class="collapse_dropdown collapse">
                                        <div class="dropdown_content">
                                            <div class="profile_info clearfix">
                                                <div class="user_thumbnail">
                                                    <img src="${pageContext.request.contextPath}/assets/images/meta/img_01.png" alt="thumbnail_not_found">
                                                </div>
                                                <div class="user_content">
                                                    <h4 class="user_name">Jone Doe</h4>
                                                    <span class="user_title">Seller</span>
                                                </div>
                                            </div>
                                            <ul class="settings_options ul_li_block clearfix">
                                                <li><a href="#!"><i class="fal fa-user-circle"></i> Profile</a></li>
                                                <li><a href="#!"><i class="fal fa-user-cog"></i> Settings</a></li>
                                                <li><a href="#!"><i class="fal fa-sign-out-alt"></i> Logout</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
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
                    <div class="container-fluid prl_90">
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
        <!-- header_section - end
        ================================================== -->


        <!-- main body - start
        ================================================== -->
        <main>


            <!-- sidebar mobile menu & sidebar cart - start
            ================================================== -->
            <div class="sidebar-menu-wrapper">
                <div class="cart_sidebar">
                    <button type="button" class="close_btn"><i class="fal fa-times"></i></button>

                    <ul class="cart_items_list ul_li_block mb_30 clearfix">
                        <li>
                            <div class="item_image">
                                <img src="assets/images/cart/img_01.jpg" alt="image_not_found">
                            </div>
                            <div class="item_content">
                                <h4 class="item_title">Yellow Blouse</h4>
                                <span class="item_price">$30.00</span>
                            </div>
                            <button type="button" class="remove_btn"><i class="fal fa-trash-alt"></i></button>
                        </li>
                        <li>
                            <div class="item_image">
                                <img src="${pageContext.request.contextPath}/assets/images/cart/img_01.jpg" alt="image_not_found">
                            </div>
                            <div class="item_content">
                                <h4 class="item_title">Yellow Blouse</h4>
                                <span class="item_price">$30.00</span>
                            </div>
                            <button type="button" class="remove_btn"><i class="fal fa-trash-alt"></i></button>
                        </li>
                        <li>
                            <div class="item_image">
                                <img src="${pageContext.request.contextPath}/assets/images/cart/img_01.jpg" alt="image_not_found">
                            </div>
                            <div class="item_content">
                                <h4 class="item_title">Yellow Blouse</h4>
                                <span class="item_price">$30.00</span>
                            </div>
                            <button type="button" class="remove_btn"><i class="fal fa-trash-alt"></i></button>
                        </li>
                    </ul>

                    <ul class="total_price ul_li_block mb_30 clearfix">
                        <li>
                            <span>Subtotal:</span>
                            <span>$90</span>
                        </li>
                        <li>
                            <span>Vat 5%:</span>
                            <span>$4.5</span>
                        </li>
                        <li>
                            <span>Discount 20%:</span>
                            <span>- $18.9</span>
                        </li>
                        <li>
                            <span>Total:</span>
                            <span>$75.6</span>
                        </li>
                    </ul>

                    <ul class="btns_group ul_li_block clearfix">
                        <li><a href="shop_cart.html">View Cart</a></li>
                        <li><a href="shop_checkout.html">Checkout</a></li>
                    </ul>
                </div>

                <div class="sidebar_mobile_menu">
                    <button type="button" class="close_btn"><i class="fal fa-times"></i></button>

                    <div class="msb_widget brand_logo text-center">
                        <a href="index.html">
                            <img src="${pageContext.request.contextPath}/assets/images/logo/logo_25_1x.png" srcset="${pageContext.request.contextPath}/assets/images/logo/logo_25_2x.png 2x" alt="logo_not_found">
                        </a>
                    </div>

                    <div class="msb_widget mobile_menu_list clearfix">
                        <h3 class="title_text mb_15 text-uppercase"><i class="far fa-bars mr-2"></i> Menu List</h3>
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
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Carparts</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="carparts_shop.html">Shop Page</a></li>
                                            <li><a href="carparts_shop_grid.html">Shop Grid</a></li>
                                            <li><a href="carparts_shop_list.html">Shop List</a></li>
                                            <li><a href="carparts_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Classic Ecommerce</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="classic_ecommerce_shop.html">Shop Page</a></li>
                                            <li><a href="classic_ecommerce_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Electronic</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="electronic_shop.html">Shop Page</a></li>
                                            <li><a href="electronic_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Fashion</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="fashion_shop.html">Shop Page</a></li>
                                            <li><a href="fashion_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Fashion Minimal</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="fashion_minimal_shop.html">Shop Page</a></li>
                                            <li><a href="fashion_minimal_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Fashion Minimal</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="fashion_minimal_shop.html">Shop Page</a></li>
                                            <li><a href="fashion_minimal_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Furniture</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="furniture_shop.html">Shop Page</a></li>
                                            <li><a href="furniture_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gadget</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="gadget_shop.html">Shop Page</a></li>
                                            <li><a href="gadget_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Medical</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="medical_shop.html">Shop Page</a></li>
                                            <li><a href="medical_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Modern Minimal</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="modern_minimal_shop.html">Shop Page</a></li>
                                            <li><a href="modern_minimal_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Modern</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="modern_shop.html">Shop Page</a></li>
                                            <li><a href="modern_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Motorcycle</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="motorcycle_shop_grid.html">Shop Grid</a></li>
                                            <li><a href="motorcycle_shop_list.html">Shop List</a></li>
                                            <li><a href="motorcycle_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Simple Shop</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="simple_shop.html">Shop Page</a></li>
                                            <li><a href="simple_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Sports</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="sports_shop.html">Shop Page</a></li>
                                            <li><a href="sports_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Lookbook</a>
                                        <ul class="dropdown-menu ul_li_block">
                                            <li><a href="lookbook_creative_shop.html">Shop Page</a></li>
                                            <li><a href="lookbook_creative_shop_details.html">Shop Details</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop Other Pages</a>
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
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop Inner Pages</a>
                                        <ul class="dropdown-menu">
                                            <li><a href="shop_cart.html">Shopping Cart</a></li>
                                            <li><a href="shop_checkout.html">Checkout Step 1</a></li>
                                            <li><a href="shop_checkout_step2.html">Checkout Step 2</a></li>
                                            <li><a href="shop_checkout_step3.html">Checkout Step 3</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="404.html">404 Page</a></li>
                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Blogs</a>
                                        <ul class="dropdown-menu">
                                            <li><a href="blog.html">Blog Page</a></li>
                                            <li><a href="blog_details.html">Blog Details</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Compare</a>
                                        <ul class="dropdown-menu">
                                            <li><a href="compare_1.html">Compare V.1</a></li>
                                            <li><a href="compare_2.html">Compare V.2</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#!" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Register</a>
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
                        <h3 class="title_text mb_30 text-uppercase"><i class="fas fa-user mr-2"></i> User Info</h3>
                        <div class="profile_info clearfix">
                            <div class="user_thumbnail">
                                <img src="${pageContext.request.contextPath}/assets/images/meta/img_01.png" alt="thumbnail_not_found">
                            </div>
                            <div class="user_content">
                                <h4 class="user_name">Jone Doe</h4>
                                <span class="user_title">Seller</span>
                            </div>
                        </div>
                        <ul class="settings_options ul_li_block clearfix">
                            <li><a href="#!"><i class="fal fa-user-circle"></i> Profile</a></li>
                            <li><a href="#!"><i class="fal fa-user-cog"></i> Settings</a></li>
                            <li><a href="#!"><i class="fal fa-sign-out-alt"></i> Logout</a></li>
                        </ul>
                    </div>
                </div>

                <div class="overlay"></div>
            </div>
            <!-- sidebar mobile menu & sidebar cart - end
            ================================================== -->


            <!-- breadcrumb_section - start
            ================================================== -->
            <section class="breadcrumb_section text-white text-center text-uppercase d-flex align-items-end clearfix" data-background="assets/images/breadcrumb/bg_01.jpg">
                <div class="overlay" data-bg-color="#1d1d1d"></div>
                <div class="container">
                    <h1 class="page_title text-white">Profile</h1>
                    <ul class="breadcrumb_nav ul_li_center clearfix">
                        <li><a href="#!">Home</a></li>
                        <li>Pages</li>
                        <li>Profile</li>
                    </ul>
                </div>
            </section>
            <!-- breadcrumb_section - end
            ================================================== -->


            <!-- register_section - start
            ================================================== -->

            <section class="register_section sec_ptb_140 has_overlay parallaxie clearfix" data-background="assets/images/backgrounds/bg_22.jpg">
                <div class="overlay" data-bg-color="rgba(55, 55, 55, 0.75)"></div>
                <div class="container">
                    <div class="main-body">
                        <div class="row">
                            <div class="card">
                                <div class="col-lg-12">
                                    <div class="card-body">
                                        <div class="d-flex flex-column align-items-center text-center">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="Admin" class="rounded-circle p-1 bg-primary" width="110">
                                            <div class="mt-3">
                                                <h4>${username}</h4>
                                                <p class="text-secondary mb-1">${email}</p>
                                            </div>
                                        </div>
                                        <hr class="my-4">
                                        <form action="profile?action=changepass" method="POST">
                                            <div class="reg_form">
                                                <h5 class="form_title text-uppercase text-center">Change Pass</h5><br>
                                                <div class="form_item">
                                                    <input type="password" name="password" placeholder="Old Password">
                                                </div>
                                                <div class="form_item">
                                                    <input type="password" name="newPassword" placeholder="New Password">
                                                </div>
                                                <div class="form_item">
                                                    <input type="password" name="newPassword2" placeholder="Confirm New Password">
                                                </div>
                                                <div style = "color: red">
                                                    ${error1}
                                                </div>
                                                <button type="submit" class="custom_btn bg_default_red text-uppercase mb_50" style= margin-left: 30px>Change Pass</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-8">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Full Name</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${accountDetail.username}
                                            </div>
                                        </div>
<!--                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Email</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <a href="/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="0c6a657c4c667967617964226d60">[email&#160;protected]</a>
                                            </div>
                                        </div>-->
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Phone Number</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${accountDetail.phone_number}
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">gender</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${accountDetail.gender}
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">DOB</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${accountDetail.dob}
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Address</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${accountDetail.address}
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <a class="btn btn-info " target="__blank" href="views/common/editprofile.jsp">Edit</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="card">
                                            <div class="card-body">
                                                <h5 class="d-flex align-items-center mb-3">Project Status</h5>
                                                <p>Web Design</p>
                                                <div class="progress mb-3" style="height: 5px">
                                                    <div class="progress-bar bg-primary" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                                                </div>
                                                <p>Website Markup</p>
                                                <div class="progress mb-3" style="height: 5px">
                                                    <div class="progress-bar bg-danger" role="progressbar" style="width: 72%" aria-valuenow="72" aria-valuemin="0" aria-valuemax="100"></div>
                                                </div>
                                                <p>One Page</p>
                                                <div class="progress mb-3" style="height: 5px">
                                                    <div class="progress-bar bg-success" role="progressbar" style="width: 89%" aria-valuenow="89" aria-valuemin="0" aria-valuemax="100"></div>
                                                </div>
                                                <p>Mobile Template</p>
                                                <div class="progress mb-3" style="height: 5px">
                                                    <div class="progress-bar bg-warning" role="progressbar" style="width: 55%" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100"></div>
                                                </div>
                                                <p>Backend API</p>
                                                <div class="progress" style="height: 5px">
                                                    <div class="progress-bar bg-info" role="progressbar" style="width: 66%" aria-valuenow="66" aria-valuemin="0" aria-valuemax="100"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
                    <script type="text/javascript"></script>
                    </body>
                </div>
            </section>
            <!-- register_section - end
            ================================================== -->


        </main>
        <!-- main body - end
        ================================================== -->


        <!-- footer_section - start
        ================================================== -->
        <footer class="footer_section default_footer clearfix">
            <div class="footer_widget_area sec_ptb_100 clearfix" data-bg-color="#1f1f1f">
                <div class="container">
                    <div class="row justify-content-lg-between">
                        <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
                            <div class="footer_widget df_about_area">
                                <div class="brand_logo mb_30">
                                    <a href="index.html">
                                        <img src="${pageContext.request.contextPath}/assets/images/logo/logo_28_1x.png" srcset="${pageContext.request.contextPath}/assets/images/logo/logo_28_2x.png 2x" alt="logo_not_found">
                                    </a>
                                </div>

                                <p class="mb_15">
                                    We’ve completed 1000+ demo site for more than one million of our customers. 
                                </p>

                                <ul class="df_contact_info ul_li_block clearfix">
                                    <li><i class="fas fa-phone-alt"></i> 001-6688-9999</li>
                                    <li><i class="fas fa-envelope"></i> contact@site.com</li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
                            <div class="footer_widget product_list clearfix">
                                <h3 class="df_widget_title text-white text-uppercase">Hot Products</h3>
                                <ul class="ul_li_block">
                                    <li>
                                        <div class="small_product">
                                            <div class="item_image">
                                                <img src="${pageContext.request.contextPath}/assets/images/shop/minimal/img_10.jpg" alt="image_not_found">
                                            </div>
                                            <div class="item_content">
                                                <h3 class="item_title">
                                                    <a class="text-white" href="#!">
                                                        Lobortis Laculis ut Condimentum
                                                    </a>
                                                </h3>
                                                <span class="item_price">$110.00</span>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="small_product">
                                            <div class="item_image">
                                                <img src="${pageContext.request.contextPath}/assets/images/shop/minimal/img_11.jpg" alt="image_not_found">
                                            </div>
                                            <div class="item_content">
                                                <h3 class="item_title">
                                                    <a class="text-white" href="#!">
                                                        Lobortis Laculis ut Condimentum
                                                    </a>
                                                </h3>
                                                <span class="item_price">$110.00</span>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
                            <div class="footer_widget product_list clearfix">
                                <h3 class="df_widget_title text-white text-uppercase">Sale Products</h3>
                                <ul class="ul_li_block">
                                    <li>
                                        <div class="small_product">
                                            <div class="item_image">
                                                <img src="${pageContext.request.contextPath}/assets/images/shop/minimal/img_12.jpg" alt="image_not_found">
                                            </div>
                                            <div class="item_content">
                                                <h3 class="item_title">
                                                    <a class="text-white" href="#!">
                                                        Lobortis Laculis ut Condimentum
                                                    </a>
                                                </h3>
                                                <span class="item_price">$110.00</span>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="small_product">
                                            <div class="item_image">
                                                <img src="${pageContext.request.contextPath}/assets/images/shop/minimal/img_13.jpg" alt="image_not_found">
                                            </div>
                                            <div class="item_content">
                                                <h3 class="item_title">
                                                    <a class="text-white" href="#!">
                                                        Lobortis Laculis ut Condimentum
                                                    </a>
                                                </h3>
                                                <span class="item_price">$110.00</span>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
                            <div class="footer_widget df_newsletter">
                                <h3 class="df_widget_title text-white text-uppercase">Newsletter</h3>
                                <p class="mb_30">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ex tortor
                                </p>
                                <form action="#">
                                    <div class="form_item mb-0">
                                        <input type="email" name="email" placeholder="Email Address">
                                        <button type="submit" class="submit_btn"><i class="fal fa-paper-plane"></i></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="footer_bottom clearfix" data-bg-color="#1a1a1a">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <p class="copyright_text mb-0">
                                @2021 Designed By <a href="#!" class="author_link text-white">jthemes</a>.
                            </p>
                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <ul class="circle_social_links ul_li_right clearfix">
                                <li><a href="#!"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href="#!"><i class="fab fa-twitter"></i></a></li>
                                <li><a href="#!"><i class="fab fa-instagram"></i></a></li>
                                <li><a href="#!"><i class="fab fa-whatsapp"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- footer_section - end
        ================================================== -->


        <!-- fraimwork - jquery include -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

        <!-- mobile menu - jquery include -->
        <script src="${pageContext.request.contextPath}/assets/js/mCustomScrollbar.js"></script>

        <!-- google map - jquery include -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDk2HrmqE4sWSei0XdKGbOMOHN3Mm2Bf-M&ver=2.1.6"></script>
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


    </body>
</html>