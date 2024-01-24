<%-- 
    Document   : productdetail
    Created on : Jan 23, 2024, 9:05:32 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">

        <title>Fashion Minimal Shop Details - Neoncart HTML5 Template</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo/favourite_icon_01.png">

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

        <!-- Site Icons -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

        <!-- Design fonts -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet"> 

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/assets/tech-blog/css/bootstrap.css" rel="stylesheet">

        <!-- FontAwesome Icons core CSS -->
        <link href="${pageContext.request.contextPath}/assets/tech-blog/css/font-awesome.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/assets/tech-blog/style.css" rel="stylesheet">

        <!-- Responsive styles for this template -->
        <link href="${pageContext.request.contextPath}/assets/tech-blog/css/responsive.css" rel="stylesheet">

        <!-- Colors for this template -->
        <link href="${pageContext.request.contextPath}/assets/tech-blog/css/colors.css" rel="stylesheet">

        <!-- Version Tech CSS for this template -->
        <link href="${pageContext.request.contextPath}/assets/tech-blog/css/version/tech.css" rel="stylesheet">


    </head>
    <style>
        .custombox {
            position: relative;
            padding: 3rem 2rem;
            border: 1px dashed #dadada;
        }
    </style>

    <body class="home_fashion_minimal">


        <!-- backtotop - start -->
        <div id="thetop"></div>
        <!-- <div class="backtotop bg_fashion2_red">
                <a href="#" class="scroll">
                        <i class="far fa-arrow-up"></i>
                </a>
        </div> -->
        <!-- backtotop - end -->

        <!-- preloader - start -->
        <!-- <div id="preloader"></div> -->
        <!-- preloader - end -->


        <!-- header_section - start
        ================================================== -->
        <header class="header_section fashion_minimal_header sticky_header clearfix">
            <div class="header_top clearfix">
                <div class="container-fluid prl_100">
                    <div class="row align-items-center">
                        <div class="col-lg-7">
                            <ul class="contact_info ul_li clearfix">
                                <li>
                                    <ul class="primary_social_links ul_li clearfix">
                                        <li><a href="#!"><i class="fab fa-facebook-f"></i></a></li>
                                        <li><a href="#!"><i class="fab fa-twitter"></i></a></li>
                                        <li><a href="#!"><i class="fab fa-youtube"></i></a></li>
                                        <li><a href="#!"><i class="fab fa-instagram"></i></a></li>
                                    </ul>
                                </li>
                                <li>023-856-7896</li>
                                <li>Support@domain.com</li>
                            </ul>
                        </div>

                        <div class="col-lg-5">
                            <form action="#">
                                <ul class="select_group ul_li_right clearfix">
                                    <li>
                                        <div class="option_select mb-0">
                                            <select>
                                                <option data-display="Select Your Language">Select A Option</option>
                                                <option value="1" selected>English</option>
                                                <option value="2">Another option</option>
                                                <option value="3" disabled>A disabled option</option>
                                                <option value="4">Potato</option>
                                            </select>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="option_select mb-0">
                                            <select>
                                                <option data-display="Select your Currency">Select A Option</option>
                                                <option value="1" selected>$USD</option>
                                                <option value="2">Another option</option>
                                                <option value="3" disabled>A disabled option</option>
                                                <option value="4">Potato</option>
                                            </select>
                                        </div>
                                    </li>
                                </ul>
                            </form>
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
                                        <i class="fal fa-bars mr-1"></i>
                                        MENU
                                    </button>
                                </li>
                                <li><a href="#!">Women</a></li>
                                <li><a href="#!">Men</a></li>
                            </ul>
                        </div>

                        <div class="col-lg-4">
                            <div class="brand_logo text-center">
                                <a class="brand_link" href="index.html">
                                    <img src="${pageContext.request.contextPath}/assets/images/logo/logo_20_1x.png" srcset="${pageContext.request.contextPath}/assets/images/logo/logo_20_2x.png 2x" alt="logo_not_found">
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

                        <div class="col-lg-4">
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
                                                <li><a href="profile.jsp"><i class="fal fa-user-circle"></i> Profile</a></li>
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
            <section class="breadcrumb_section medical_breadcrumb text-center d-flex align-items-center clearfix" data-background="${pageContext.request.contextPath}/assets/images/breadcrumb/bg_10.jpg">
                <div class="container">
                    <h1 class="medical_page_title mb_15">Product Details</h1>
                    <ul class="medical_breadcrumb_nav ul_li_center clearfix">
                        <li><a href="#!">Home</a></li>
                        <li>Shop</li>
                        <li>Fashion Minimal</li>
                        <li>Shop Details</li>
                    </ul>
                </div>
            </section>
            <!-- breadcrumb_section - end
            ================================================== -->


            <!-- fm_details_section - start
            ================================================== -->
            <section class="fm_details_section sec_ptb_100 clearfix">
                <div class="container mb_100">
                    <div class="row align-items-center justify-content-lg-between">
                        <div class="col-lg-5 col-md-7">
                            <div class="details_image">
                                <div class="tab-content">
                                    <div id="di_tab_1" class="tab-pane active">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_01.jpg" alt="image_not_found">
                                    </div>
                                    <div id="di_tab_2" class="tab-pane fade">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_01.jpg" alt="image_not_found">
                                    </div>
                                    <div id="di_tab_3" class="tab-pane fade">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_01.jpg" alt="image_not_found">
                                    </div>
                                    <div id="di_tab_4" class="tab-pane fade">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_01.jpg" alt="image_not_found">
                                    </div>
                                    <div id="di_tab_5" class="tab-pane fade">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_01.jpg" alt="image_not_found">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-2 col-md-7">
                            <ul class="di_tab_nav ul_li" role="tablist">
                                <li>
                                    <a class="active" data-toggle="tab" href="#di_tab_1">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_02.jpg" alt="image_not_found">
                                    </a>
                                </li>
                                <li>
                                    <a data-toggle="tab" href="#di_tab_2">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_03.jpg" alt="image_not_found">
                                    </a>
                                </li>
                                <li>
                                    <a data-toggle="tab" href="#di_tab_3">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_04.jpg" alt="image_not_found">
                                    </a>
                                </li>
                                <li>
                                    <a data-toggle="tab" href="#di_tab_4">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_05.jpg" alt="image_not_found">
                                    </a>
                                </li>
                                <li>
                                    <a data-toggle="tab" href="#di_tab_5">
                                        <img src="${pageContext.request.contextPath}/assets/images/details/fashion_minimal/img_06.jpg" alt="image_not_found">
                                    </a>
                                </li>
                            </ul>
                        </div>

                        <div class="col-lg-5 col-md-7">
                            <div class="details_content">
                                <span class="item_type">Jackets</span>
                                <h2 class="item_title mb_15">Hyperion</h2>
                                <span class="item_price mb_15"><strong>$85.99</strong></span>
                                <hr>
                                <div class="item_size_list mb_30 d-flex align-items-center clearfix">
                                    <h4 class="list_title mb-0 text-uppercase">Color:</h4>
                                    <div class="option_select mb-0">
                                        <select>
                                            <option data-display="Select your color">Select A Option</option>
                                            <option value="1" selected>Black</option>
                                            <option value="2">Black</option>
                                            <option value="3">White</option>
                                            <option value="4">Pink</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="item_size_list mb_30 d-flex align-items-center clearfix">
                                    <h4 class="list_title mb-0 text-uppercase">Size:</h4>
                                    <div class="option_select mb-0">
                                        <select>
                                            <option data-display="Select your size">Select A Option</option>
                                            <option value="1" selected>48</option>
                                            <option value="2">48</option>
                                            <option value="3">48</option>
                                            <option value="4">48</option>
                                        </select>
                                    </div>
                                </div>

                                <ul class="btns_group ul_li clearfix">
                                    <li>
                                        <div class="quantity_input">
                                            <form action="#">
                                                <span class="input_number_decrement">–</span>
                                                <input class="input_number" type="text" value="1">
                                                <span class="input_number_increment">+</span>
                                            </form>
                                        </div>
                                    </li>
                                    <li>
                                        <a class="custom_btn btn_sm bg_fashion2_red text-uppercase" href="#!"><i class="fal fa-shopping-bag mr-2"></i> Add To Cart</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="fm_details_description">
                    <div class="container">
                        <ul class="nav ul_li_center mb_30" role="tablist">
                            <li>
                                <a class="active" data-toggle="tab" href="#description_tab">DESCRIPTION</a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#review_ta">REVIEWS (2)</a>
                            </li>
                        </ul>
                    </div>

                    <div class="tab-content">
                        <div id="description_tab" class="tab-pane active">
                            <div class="container">
                                <div class="table_wrap">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <img src="${pageContext.request.contextPath}/assets/images/img_01.png" alt="instructions">
                                                </td>
                                                <td>
                                                    <p class="mb-0">
                                                        Fusce vestibulum justo id varius tristique. Vivamus purus odio, <br> interdum id massa ullamcorper, tempus.
                                                    </p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><strong>Material:</strong></td>
                                                <td>Cotton</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Weight:</strong></td>
                                                <td>100 g</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Color:</strong></td>
                                                <td>Beige, white, blue</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Sizes:</strong></td>
                                                <td>44, 48, 50</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div id="review_ta" class="tab-pane fade">
                            <form class="container" action="#">
                                <div class="form_item">
                                    <textarea name="message" placeholder="Your Message"></textarea>
                                </div>
                                <button type="submit" class="custom_btn bg_default_red text-uppercase">Submit Review</button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
            <!-- fm_details_section - end
            ================================================== -->
            <hr class="invis1">
            <div class="custombox clearfix">
                <h4 class="small-title">Comments</h4>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="comments-list">
                            <div class="media">
                                <a class="media-left" href="#">
                                    <img src="upload/author.jpg" alt="" class="rounded-circle">
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading user_name">Amanda Martines <small>5 days ago</small></h4>
                                    <p>Exercitation photo booth stumptown tote bag Banksy, elit small batch freegan sed. Craft beer elit seitan exercitation, photo booth et 8-bit kale chips proident chillwave deep v laborum. Aliquip veniam delectus, Marfa eiusmod Pinterest in do umami readymade swag. Selfies iPhone Kickstarter, drinking vinegar jean.</p>
                                    <a href="#" class="btn btn-primary btn-sm">Reply</a>
                                </div>
                            </div>
                            <div class="media">
                                <a class="media-left" href="#">
                                    <img src="upload/author_01.jpg" alt="" class="rounded-circle">
                                </a>
                                <div class="media-body">

                                    <h4 class="media-heading user_name">Baltej Singh <small>5 days ago</small></h4>

                                    <p>Drinking vinegar stumptown yr pop-up artisan sunt. Deep v cliche lomo biodiesel Neutra selfies. Shorts fixie consequat flexitarian four loko tempor duis single-origin coffee. Banksy, elit small.</p>

                                    <a href="#" class="btn btn-primary btn-sm">Reply</a>
                                </div>
                            </div>
                            <div class="media last-child">
                                <a class="media-left" href="#">
                                    <img src="upload/author_02.jpg" alt="" class="rounded-circle">
                                </a>
                                <div class="media-body">

                                    <h4 class="media-heading user_name">Marie Johnson <small>5 days ago</small></h4>
                                    <p>Kickstarter seitan retro. Drinking vinegar stumptown yr pop-up artisan sunt. Deep v cliche lomo biodiesel Neutra selfies. Shorts fixie consequat flexitarian four loko tempor duis single-origin coffee. Banksy, elit small.</p>

                                    <a href="#" class="btn btn-primary btn-sm">Reply</a>
                                </div>
                            </div>
                        </div>
                    </div><!-- end col -->
                </div><!-- end row -->
            </div><!-- end custom-box -->
            <br><br>
            <!-- product_section - start
            ================================================== -->
            <section class="product_section clearfix">
                <div class="container-fluid prl_100">

                    <div class="fm_section_title text-center mb_15">
                        <h2 class="title_text">Related Product</h2>
                        <p class="mb-0">WooCommerce Theme</p>
                    </div>

                    <div class="row justify-content-center">
                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            <div class="fashion_minimal_product">
                                <ul class="product_label ul_li clearfix">
                                    <li data-bg-color="#fb5d5d">-20%</li>
                                    <li data-bg-color="#82ca9c">NEW</li>
                                </ul>
                                <div class="item_image">
                                    <a class="image_wrap" href="#!">
                                        <img src="${pageContext.request.contextPath}/assets/images/shop/fashion_minimal/img_01.jpg" alt="image_not_found">
                                    </a>
                                    <span class="coming_soon text-uppercase">Coming Soon</span>
                                    <a class="addto_wishlist tooltips" data-placement="top" title="Add To Wishlist" href="#!"><i class="fal fa-heart"></i></a>
                                    <ul class="product_action_btns ul_li_center clearfix">
                                        <li><a class="tooltips" data-placement="top" title="Quick View" href="#!" data-toggle="modal" data-target="#quickview_modal"><i class="fal fa-search"></i></a></li>
                                        <li><a class="addtocart_btn text-uppercase" href="#!">Add To Cart</a></li>
                                        <li><a class="tooltips" data-placement="top" title="Compare" href="#!"><i class="far fa-random"></i></a></li>
                                    </ul>
                                </div>
                                <div class="item_content">
                                    <h3 class="item_title">
                                        <a href="#!">Artwork Hawaii Shirt Brutus</a>
                                    </h3>
                                    <div class="d-flex align-items-center justify-content-between">
                                        <span class="item_price"><strong>$19.12</strong> <del>$19.12</del></span>
                                        <ul class="item_color ul_li clearfix">
                                            <li><a href="#!" data-bg-color="#739f7f"></a></li>
                                            <li><a href="#!" data-bg-color="#eede86"></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            <div class="fashion_minimal_product">
                                <ul class="product_label ul_li clearfix">
                                    <li data-bg-color="#fb5d5d">-20%</li>
                                    <li data-bg-color="#82ca9c">NEW</li>
                                </ul>
                                <div class="item_image">
                                    <a class="image_wrap" href="#!">
                                        <img src="${pageContext.request.contextPath}/assets/images/shop/fashion_minimal/img_02.jpg" alt="image_not_found">
                                    </a>
                                    <span class="coming_soon text-uppercase">Coming Soon</span>
                                    <a class="addto_wishlist tooltips" data-placement="top" title="Add To Wishlist" href="#!"><i class="fal fa-heart"></i></a>
                                    <ul class="product_action_btns ul_li_center clearfix">
                                        <li><a class="tooltips" data-placement="top" title="Quick View" href="#!" data-toggle="modal" data-target="#quickview_modal"><i class="fal fa-search"></i></a></li>
                                        <li><a class="addtocart_btn text-uppercase" href="#!">Add To Cart</a></li>
                                        <li><a class="tooltips" data-placement="top" title="Compare" href="#!"><i class="far fa-random"></i></a></li>
                                    </ul>
                                </div>
                                <div class="item_content">
                                    <h3 class="item_title">
                                        <a href="#!">Artwork Hawaii Shirt Brutus</a>
                                    </h3>
                                    <div class="d-flex align-items-center justify-content-between">
                                        <span class="item_price"><strong>$19.12</strong> <del>$19.12</del></span>
                                        <ul class="item_color ul_li clearfix">
                                            <li><a href="#!" data-bg-color="#739f7f"></a></li>
                                            <li><a href="#!" data-bg-color="#eede86"></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            <div class="fashion_minimal_product">
                                <ul class="product_label ul_li clearfix">
                                    <li data-bg-color="#fb5d5d">-20%</li>
                                    <li data-bg-color="#82ca9c">NEW</li>
                                </ul>
                                <div class="item_image">
                                    <a class="image_wrap" href="#!">
                                        <img src="${pageContext.request.contextPath}/assets/images/shop/fashion_minimal/img_03.jpg" alt="image_not_found">
                                    </a>
                                    <span class="coming_soon text-uppercase">Coming Soon</span>
                                    <a class="addto_wishlist tooltips" data-placement="top" title="Add To Wishlist" href="#!"><i class="fal fa-heart"></i></a>
                                    <ul class="product_action_btns ul_li_center clearfix">
                                        <li><a class="tooltips" data-placement="top" title="Quick View" href="#!" data-toggle="modal" data-target="#quickview_modal"><i class="fal fa-search"></i></a></li>
                                        <li><a class="addtocart_btn text-uppercase" href="#!">Add To Cart</a></li>
                                        <li><a class="tooltips" data-placement="top" title="Compare" href="#!"><i class="far fa-random"></i></a></li>
                                    </ul>
                                </div>
                                <div class="item_content">
                                    <h3 class="item_title">
                                        <a href="#!">Artwork Hawaii Shirt Brutus</a>
                                    </h3>
                                    <div class="d-flex align-items-center justify-content-between">
                                        <span class="item_price"><strong>$19.12</strong> <del>$19.12</del></span>
                                        <ul class="item_color ul_li clearfix">
                                            <li><a href="#!" data-bg-color="#739f7f"></a></li>
                                            <li><a href="#!" data-bg-color="#eede86"></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            <div class="fashion_minimal_product">
                                <ul class="product_label ul_li clearfix">
                                    <li data-bg-color="#fb5d5d">-20%</li>
                                    <li data-bg-color="#82ca9c">NEW</li>
                                </ul>
                                <div class="item_image">
                                    <a class="image_wrap" href="#!">
                                        <img src="${pageContext.request.contextPath}/assets/images/shop/fashion_minimal/img_04.jpg" alt="image_not_found">
                                    </a>
                                    <span class="coming_soon text-uppercase">Coming Soon</span>
                                    <a class="addto_wishlist tooltips" data-placement="top" title="Add To Wishlist" href="#!"><i class="fal fa-heart"></i></a>
                                    <ul class="product_action_btns ul_li_center clearfix">
                                        <li><a class="tooltips" data-placement="top" title="Quick View" href="#!" data-toggle="modal" data-target="#quickview_modal"><i class="fal fa-search"></i></a></li>
                                        <li><a class="addtocart_btn text-uppercase" href="#!">Add To Cart</a></li>
                                        <li><a class="tooltips" data-placement="top" title="Compare" href="#!"><i class="far fa-random"></i></a></li>
                                    </ul>
                                </div>
                                <div class="item_content">
                                    <h3 class="item_title">
                                        <a href="#!">Artwork Hawaii Shirt Brutus</a>
                                    </h3>
                                    <div class="d-flex align-items-center justify-content-between">
                                        <span class="item_price"><strong>$19.12</strong> <del>$19.12</del></span>
                                        <ul class="item_color ul_li clearfix">
                                            <li><a href="#!" data-bg-color="#739f7f"></a></li>
                                            <li><a href="#!" data-bg-color="#eede86"></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> <br><br>
                </div>
            </section>
            <!-- product_section - end
            ================================================== -->





        </main>
        <!-- main body - end
        ================================================== -->


        <!-- footer_section - start
        ================================================== -->
        <footer class="footer_section fashion_minimal_footer clearfix" data-bg-color="#222222">
            <div class="backtotop" data-background="assets/images/shape_01.png">
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
                                        <img src="${pageContext.request.contextPath}/assets/images/logo/logo_21_1x.png" srcset="${pageContext.request.contextPath}/assets/images/logo/logo_21_2x.png 2x" alt="logo_not_found">
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

            <div class="container">
                <div class="footer_bottom text-center">
                    <ul class="circle_social_links ul_li_center clearfix">
                        <li><a href="#!"><i class="fab fa-facebook-f"></i></a></li>
                        <li><a href="#!"><i class="fab fa-twitter"></i></a></li>
                        <li><a href="#!"><i class="fab fa-youtube"></i></a></li>
                    </ul>
                    <p class="copyright_text mb-0">
                        © Copyrights, 2021 <a href="#!" class="author_link text-white">Neoncart.com</a>
                    </p>
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


        <!-- product quick view - start -->
        <div class="quickview_modal modal fade" id="quickview_modal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content clearfix">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <div class="item_image">
                        <img src="${pageContext.request.contextPath}/assets/images/shop/fashion_minimal/img_07.jpg" alt="image_not_found">
                    </div>
                    <div class="item_content">
                        <h2 class="item_title mb_15">Digital Infrared Thermometer</h2>
                        <div class="rating_star mb_30 clearfix">
                            <ul class="float-left ul_li mr-2">
                                <li class="active"><i class="las la-star"></i></li>
                                <li class="active"><i class="las la-star"></i></li>
                                <li class="active"><i class="las la-star"></i></li>
                                <li class="active"><i class="las la-star"></i></li>
                                <li><i class="las la-star"></i></li>
                            </ul>
                            <span class="review_text">(12 Reviews)</span>
                        </div>
                        <span class="item_price mb_15">$49.50</span>
                        <p class="mb_30">
                            Best Electronic Digital Thermometer adipiscing elit, sed do eiusmod teincididunt ut labore et dolore magna aliqua. Quis ipsum suspendisse us ultrices gravidaes.
                        </p>
                        <div class="quantity_form mb_30 clearfix">
                            <strong class="list_title">Quantity:</strong>
                            <div class="quantity_input">
                                <form action="#">
                                    <span class="input_number_decrement">–</span>
                                    <input class="input_number" type="text" value="1">
                                    <span class="input_number_increment">+</span>
                                </form>
                            </div>
                        </div>
                        <ul class="btns_group ul_li mb_30 clearfix">
                            <li><a href="#!" class="custom_btn bg_carparts_red">Add to Cart</a></li>
                            <li><a href="#!" data-toggle="tooltip" data-placement="top" title="" data-original-title="Compare Product"><i class="fal fa-sync"></i></a></li>
                            <li><a href="#!" data-toggle="tooltip" data-placement="top" title="" data-original-title="Add To Wishlist"><i class="fal fa-heart"></i></a></li>
                        </ul>
                        <ul class="info_list ul_li_block clearfix">
                            <li><strong class="list_title">Category:</strong> <a href="#!">Medical Equipment</a></li>
                            <li class="social_icon">
                                <strong class="list_title">Share:</strong>
                                <ul class="ul_li clearfix">
                                    <li><a href="#!"><i class="fab fa-facebook-f"></i></a></li>
                                    <li><a href="#!"><i class="fab fa-twitter"></i></a></li>
                                    <li><a href="#!"><i class="fab fa-instagram"></i></a></li>
                                    <li><a href="#!"><i class="fab fa-pinterest-p"></i></a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- product quick view - end -->
        <!-- shop_section - end
                ================================================== -->


    </body>
</html>
