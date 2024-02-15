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
        <title>Shop - Vuexy - Bootstrap HTML admin template</title>
        <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">

        <!-- BEGIN: Vendor CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/vendors.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/extensions/nouislider.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/ui/prism.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/forms/select/select2.min.css">
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
                                <li class="nav-item d-none d-lg-block"><a class="nav-link" href="app-todo.html" data-toggle="tooltip" data-placement="top" title="Todo"><i class="ficon feather icon-check-square"></i></a></li>
                                <li class="nav-item d-none d-lg-block"><a class="nav-link" href="app-chat.html" data-toggle="tooltip" data-placement="top" title="Chat"><i class="ficon feather icon-message-square"></i></a></li>
                                <li class="nav-item d-none d-lg-block"><a class="nav-link" href="app-email.html" data-toggle="tooltip" data-placement="top" title="Email"><i class="ficon feather icon-mail"></i></a></li>
                                <li class="nav-item d-none d-lg-block"><a class="nav-link" href="app-calender.html" data-toggle="tooltip" data-placement="top" title="Calendar"><i class="ficon feather icon-calendar"></i></a></li>
                            </ul>
                            <ul class="nav navbar-nav">
                                <li class="nav-item d-none d-lg-block"><a class="nav-link bookmark-star"><i class="ficon feather icon-star warning"></i></a>
                                    <div class="bookmark-input search-input">
                                        <div class="bookmark-input-icon"><i class="feather icon-search primary"></i></div>
                                        <input class="form-control input" type="text" placeholder="Explore Vuexy..." tabindex="0" data-search="template-list">
                                        <ul class="search-list search-list-bookmark"></ul>
                                    </div>
                                    <!-- select.bookmark-select-->
                                    <!--   option Chat-->
                                    <!--   option email-->
                                    <!--   option todo-->
                                    <!--   option Calendar-->
                                </li>
                            </ul>
                        </div>
                        <ul class="nav navbar-nav float-right">
                            <li class="dropdown dropdown-language nav-item"><a class="dropdown-toggle nav-link" id="dropdown-flag" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="flag-icon flag-icon-us"></i><span class="selected-language">English</span></a>
                                <div class="dropdown-menu" aria-labelledby="dropdown-flag"><a class="dropdown-item" href="#" data-language="en"><i class="flag-icon flag-icon-us"></i> English</a><a class="dropdown-item" href="#" data-language="fr"><i class="flag-icon flag-icon-fr"></i> French</a><a class="dropdown-item" href="#" data-language="de"><i class="flag-icon flag-icon-de"></i> German</a><a class="dropdown-item" href="#" data-language="pt"><i class="flag-icon flag-icon-pt"></i> Portuguese</a></div>
                            </li>
                            <li class="nav-item d-none d-lg-block"><a class="nav-link nav-link-expand"><i class="ficon feather icon-maximize"></i></a></li>
                            <li class="nav-item nav-search"><a class="nav-link nav-link-search"><i class="ficon feather icon-search"></i></a>
                                <div class="search-input">
                                    <div class="search-input-icon"><i class="feather icon-search primary"></i></div>
                                    <input class="input" type="text" placeholder="Explore Vuexy..." tabindex="-1" data-search="template-list">
                                    <div class="search-input-close"><i class="feather icon-x"></i></div>
                                    <ul class="search-list search-list-main"></ul>
                                </div>
                            </li>
                            <li class="dropdown dropdown-notification nav-item"><a class="nav-link nav-link-label" href="#" data-toggle="dropdown"><i class="ficon feather icon-shopping-cart"></i><span class="badge badge-pill badge-primary badge-up cart-item-count">6</span></a>
                                <ul class="dropdown-menu dropdown-menu-media dropdown-cart dropdown-menu-right">
                                    <li class="dropdown-menu-header">
                                        <div class="dropdown-header m-0 p-2">
                                            <h3 class="white"><span class="cart-item-count">6</span><span class="mx-50">Items</span></h3><span class="notification-title">In Your Cart</span>
                                        </div>
                                    </li>
                                    <li class="scrollable-container media-list"><a class="cart-item" href="app-ecommerce-details.html">
                                            <div class="media">
                                                <div class="media-left d-flex justify-content-center align-items-center"><img src="${pageContext.request.contextPath}/app-assets/images/pages/eCommerce/4.png" width="75" alt="Cart Item"></div>
                                                <div class="media-body"><span class="item-title text-truncate text-bold-500 d-block mb-50">Apple - Apple Watch Series 1 42mm Space Gray Aluminum Case Black Sport Band - Space Gray Aluminum</span><span class="item-desc font-small-2 text-truncate d-block"> Durable, lightweight aluminum cases in silver, space gray,gold, and rose gold. Sport Band in a variety of colors. All the features of the original Apple Watch, plus a new dual-core processor for faster performance. All models run watchOS 3. Requires an iPhone 5 or later to run this device.</span>
                                                    <div class="d-flex justify-content-between align-items-center mt-1"><span class="align-middle d-block">1 x $299</span><i class="remove-cart-item feather icon-x danger font-medium-1"></i></div>
                                                </div>
                                            </div>
                                        </a><a class="cart-item" href="app-ecommerce-details.html">
                                            <div class="media">
                                                <div class="media-left d-flex justify-content-center align-items-center"><img class="mt-1 pl-50" src="${pageContext.request.contextPath}/app-assets/images/pages/eCommerce/dell-inspirion.jpg" width="100" alt="Cart Item"></div>
                                                <div class="media-body"><span class="item-title text-truncate text-bold-500 d-block mb-50">Apple - Macbook® (Latest Model) - 12" Display - Intel Core M5 - 8GB Memory - 512GB Flash Storage - Space Gray</span><span class="item-desc font-small-2 text-truncate d-block"> MacBook delivers a full-size experience in the lightest and most compact Mac notebook ever. With a full-size keyboard, force-sensing trackpad, 12-inch Retina display,1 sixth-generation Intel Core M processor, multifunctional USB-C port, and now up to 10 hours of battery life,2 MacBook features big thinking in an impossibly compact form.</span>
                                                    <div class="d-flex justify-content-between align-items-center mt-1"><span class="align-middle d-block">1 x $1599.99</span><i class="remove-cart-item feather icon-x danger font-medium-1"></i></div>
                                                </div>
                                            </div>
                                        </a><a class="cart-item" href="app-ecommerce-details.html">
                                            <div class="media">
                                                <div class="media-left d-flex justify-content-center align-items-center"><img src="${pageContext.request.contextPath}/app-assets/images/pages/eCommerce/7.png" width="88" alt="Cart Item"></div>
                                                <div class="media-body"><span class="item-title text-truncate text-bold-500 d-block mb-50">Sony - PlayStation 4 Pro Console</span><span class="item-desc font-small-2 text-truncate d-block"> PS4 Pro Dynamic 4K Gaming & 4K Entertainment* PS4 Pro gets you closer to your game. Heighten your experiences. Enrich your adventures. Let the super-charged PS4 Pro lead the way.** GREATNESS AWAITS</span>
                                                    <div class="d-flex justify-content-between align-items-center mt-1"><span class="align-middle d-block">1 x $399.99</span><i class="remove-cart-item feather icon-x danger font-medium-1"></i></div>
                                                </div>
                                            </div>
                                        </a><a class="cart-item" href="app-ecommerce-details.html">
                                            <div class="media">
                                                <div class="media-left d-flex justify-content-center align-items-center"><img src="${pageContext.request.contextPath}/app-assets/images/pages/eCommerce/10.png" width="75" alt="Cart Item"></div>
                                                <div class="media-body"><span class="item-title text-truncate text-bold-500 d-block mb-50">Beats by Dr. Dre - Geek Squad Certified Refurbished Beats Studio Wireless On-Ear Headphones - Red</span><span class="item-desc font-small-2 text-truncate d-block"> Rock out to your favorite songs with these Beats by Dr. Dre Beats Studio Wireless GS-MH8K2AM/A headphones that feature a Beats Acoustic Engine and DSP software for enhanced clarity. ANC (Adaptive Noise Cancellation) allows you to focus on your tunes.</span>
                                                    <div class="d-flex justify-content-between align-items-center mt-1"><span class="align-middle d-block">1 x $379.99</span><i class="remove-cart-item feather icon-x danger font-medium-1"></i></div>
                                                </div>
                                            </div>
                                        </a><a class="cart-item" href="app-ecommerce-details.html">
                                            <div class="media">
                                                <div class="media-left d-flex justify-content-center align-items-center"><img class="mt-1 pl-50" src="${pageContext.request.contextPath}/app-assets/images/pages/eCommerce/sony-75class-tv.jpg" width="100" alt="Cart Item"></div>
                                                <div class="media-body"><span class="item-title text-truncate text-bold-500 d-block mb-50">Sony - 75" Class (74.5" diag) - LED - 2160p - Smart - 3D - 4K Ultra HD TV with High Dynamic Range - Black</span><span class="item-desc font-small-2 text-truncate d-block"> This Sony 4K HDR TV boasts 4K technology for vibrant hues. Its X940D series features a bold 75-inch screen and slim design. Wires remain hidden, and the unit is easily wall mounted. This television has a 4K Processor X1 and 4K X-Reality PRO for crisp video. This Sony 4K HDR TV is easy to control via voice commands.</span>
                                                    <div class="d-flex justify-content-between align-items-center mt-1"><span class="align-middle d-block">1 x $4499.99</span><i class="remove-cart-item feather icon-x danger font-medium-1"></i></div>
                                                </div>
                                            </div>
                                        </a><a class="cart-item" href="app-ecommerce-details.html">
                                            <div class="media">
                                                <div class="media-left d-flex justify-content-center align-items-center"><img class="mt-1 pl-50" src="${pageContext.request.contextPath}/app-assets/images/pages/eCommerce/canon-camera.jpg" width="70" alt="Cart Item"></div>
                                                <div class="media-body"><span class="item-title text-truncate text-bold-500 d-block mb-50">Nikon - D810 DSLR Camera with AF-S NIKKOR 24-120mm f/4G ED VR Zoom Lens - Black</span><span class="item-desc font-small-2 text-truncate d-block"> Shoot arresting photos and 1080p high-definition videos with this Nikon D810 DSLR camera, which features a 36.3-megapixel CMOS sensor and a powerful EXPEED 4 processor for clear, detailed images. The AF-S NIKKOR 24-120mm lens offers shooting versatility. Memory card sold separately.</span>
                                                    <div class="d-flex justify-content-between align-items-center mt-1"><span class="align-middle d-block">1 x $4099.99</span><i class="remove-cart-item feather icon-x danger font-medium-1"></i></div>
                                                </div>
                                            </div>
                                        </a></li>
                                    <li class="dropdown-menu-footer"><a class="dropdown-item p-1 text-center text-primary" href="app-ecommerce-checkout.html"><i class="feather icon-shopping-cart align-middle"></i><span class="align-middle text-bold-600">Checkout</span></a></li>
                                    <li class="empty-cart d-none p-2">Your Cart Is Empty.</li>
                                </ul>
                            </li>
                            <li class="dropdown dropdown-notification nav-item"><a class="nav-link nav-link-label" href="#" data-toggle="dropdown"><i class="ficon feather icon-bell"></i><span class="badge badge-pill badge-primary badge-up">5</span></a>
                                <ul class="dropdown-menu dropdown-menu-media dropdown-menu-right">
                                    <li class="dropdown-menu-header">
                                        <div class="dropdown-header m-0 p-2">
                                            <h3 class="white">5 New</h3><span class="notification-title">App Notifications</span>
                                        </div>
                                    </li>
                                    <li class="scrollable-container media-list"><a class="d-flex justify-content-between" href="javascript:void(0)">
                                            <div class="media d-flex align-items-start">
                                                <div class="media-left"><i class="feather icon-plus-square font-medium-5 primary"></i></div>
                                                <div class="media-body">
                                                    <h6 class="primary media-heading">You have new order!</h6><small class="notification-text"> Are your going to meet me tonight?</small>
                                                </div><small>
                                                    <time class="media-meta" datetime="2015-06-11T18:29:20+08:00">9 hours ago</time></small>
                                            </div>
                                        </a><a class="d-flex justify-content-between" href="javascript:void(0)">
                                            <div class="media d-flex align-items-start">
                                                <div class="media-left"><i class="feather icon-download-cloud font-medium-5 success"></i></div>
                                                <div class="media-body">
                                                    <h6 class="success media-heading red darken-1">99% Server load</h6><small class="notification-text">You got new order of goods.</small>
                                                </div><small>
                                                    <time class="media-meta" datetime="2015-06-11T18:29:20+08:00">5 hour ago</time></small>
                                            </div>
                                        </a><a class="d-flex justify-content-between" href="javascript:void(0)">
                                            <div class="media d-flex align-items-start">
                                                <div class="media-left"><i class="feather icon-alert-triangle font-medium-5 danger"></i></div>
                                                <div class="media-body">
                                                    <h6 class="danger media-heading yellow darken-3">Warning notifixation</h6><small class="notification-text">Server have 99% CPU usage.</small>
                                                </div><small>
                                                    <time class="media-meta" datetime="2015-06-11T18:29:20+08:00">Today</time></small>
                                            </div>
                                        </a><a class="d-flex justify-content-between" href="javascript:void(0)">
                                            <div class="media d-flex align-items-start">
                                                <div class="media-left"><i class="feather icon-check-circle font-medium-5 info"></i></div>
                                                <div class="media-body">
                                                    <h6 class="info media-heading">Complete the task</h6><small class="notification-text">Cake sesame snaps cupcake</small>
                                                </div><small>
                                                    <time class="media-meta" datetime="2015-06-11T18:29:20+08:00">Last week</time></small>
                                            </div>
                                        </a><a class="d-flex justify-content-between" href="javascript:void(0)">
                                            <div class="media d-flex align-items-start">
                                                <div class="media-left"><i class="feather icon-file font-medium-5 warning"></i></div>
                                                <div class="media-body">
                                                    <h6 class="warning media-heading">Generate monthly report</h6><small class="notification-text">Chocolate cake oat cake tiramisu marzipan</small>
                                                </div><small>
                                                    <time class="media-meta" datetime="2015-06-11T18:29:20+08:00">Last month</time></small>
                                            </div>
                                        </a></li>
                                    <li class="dropdown-menu-footer"><a class="dropdown-item p-1 text-center" href="javascript:void(0)">View all notifications</a></li>
                                </ul>
                            </li>
                            <li class="dropdown dropdown-user nav-item"><a class="dropdown-toggle nav-link dropdown-user-link" href="#" data-toggle="dropdown">
                                    <div class="user-nav d-sm-flex d-none"><span class="user-name text-bold-600">John Doe</span><span class="user-status">Available</span></div><span><img class="round" src="${pageContext.request.contextPath}/app-assets/images/portrait/small/avatar-s-11.jpg" alt="avatar" height="40" width="40"></span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item" href="page-user-profile.html"><i class="feather icon-user"></i> Edit Profile</a><a class="dropdown-item" href="app-email.html"><i class="feather icon-mail"></i> My Inbox</a><a class="dropdown-item" href="app-todo.html"><i class="feather icon-check-square"></i> Task</a><a class="dropdown-item" href="app-chat.html"><i class="feather icon-message-square"></i> Chats</a>
                                    <div class="dropdown-divider"></div><a class="dropdown-item" href="auth-login.html"><i class="feather icon-power"></i> Logout</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <ul class="main-search-list-defaultlist d-none">
            <li class="d-flex align-items-center"><a class="pb-25" href="#">
                    <h6 class="text-primary mb-0">Files</h6>
                </a></li>
            <li class="auto-suggestion d-flex align-items-center cursor-pointer"><a class="d-flex align-items-center justify-content-between w-100" href="#">
                    <div class="d-flex">
                        <div class="mr-50"><img src="${pageContext.request.contextPath}/app-assets/images/icons/xls.png" alt="png" height="32"></div>
                        <div class="search-data">
                            <p class="search-data-title mb-0">Two new item submitted</p><small class="text-muted">Marketing Manager</small>
                        </div>
                    </div><small class="search-data-size mr-50 text-muted">&apos;17kb</small>
                </a></li>
            <li class="auto-suggestion d-flex align-items-center cursor-pointer"><a class="d-flex align-items-center justify-content-between w-100" href="#">
                    <div class="d-flex">
                        <div class="mr-50"><img src="${pageContext.request.contextPath}/app-assets/images/icons/jpg.png" alt="png" height="32"></div>
                        <div class="search-data">
                            <p class="search-data-title mb-0">52 JPG file Generated</p><small class="text-muted">FontEnd Developer</small>
                        </div>
                    </div><small class="search-data-size mr-50 text-muted">&apos;11kb</small>
                </a></li>
            <li class="auto-suggestion d-flex align-items-center cursor-pointer"><a class="d-flex align-items-center justify-content-between w-100" href="#">
                    <div class="d-flex">
                        <div class="mr-50"><img src="${pageContext.request.contextPath}/app-assets/images/icons/pdf.png" alt="png" height="32"></div>
                        <div class="search-data">
                            <p class="search-data-title mb-0">25 PDF File Uploaded</p><small class="text-muted">Digital Marketing Manager</small>
                        </div>
                    </div><small class="search-data-size mr-50 text-muted">&apos;150kb</small>
                </a></li>
            <li class="auto-suggestion d-flex align-items-center cursor-pointer"><a class="d-flex align-items-center justify-content-between w-100" href="#">
                    <div class="d-flex">
                        <div class="mr-50"><img src="${pageContext.request.contextPath}/app-assets/images/icons/doc.png" alt="png" height="32"></div>
                        <div class="search-data">
                            <p class="search-data-title mb-0">Anna_Strong.doc</p><small class="text-muted">Web Designer</small>
                        </div>
                    </div><small class="search-data-size mr-50 text-muted">&apos;256kb</small>
                </a></li>
            <li class="d-flex align-items-center"><a class="pb-25" href="#">
                    <h6 class="text-primary mb-0">Members</h6>
                </a></li>
            <li class="auto-suggestion d-flex align-items-center cursor-pointer"><a class="d-flex align-items-center justify-content-between py-50 w-100" href="#">
                    <div class="d-flex align-items-center">
                        <div class="avatar mr-50"><img src="${pageContext.request.contextPath}/app-assets/images/portrait/small/avatar-s-8.jpg" alt="png" height="32"></div>
                        <div class="search-data">
                            <p class="search-data-title mb-0">John Doe</p><small class="text-muted">UI designer</small>
                        </div>
                    </div>
                </a></li>
            <li class="auto-suggestion d-flex align-items-center cursor-pointer"><a class="d-flex align-items-center justify-content-between py-50 w-100" href="#">
                    <div class="d-flex align-items-center">
                        <div class="avatar mr-50"><img src="${pageContext.request.contextPath}/app-assets/images/portrait/small/avatar-s-1.jpg" alt="png" height="32"></div>
                        <div class="search-data">
                            <p class="search-data-title mb-0">Michal Clark</p><small class="text-muted">FontEnd Developer</small>
                        </div>
                    </div>
                </a></li>
            <li class="auto-suggestion d-flex align-items-center cursor-pointer"><a class="d-flex align-items-center justify-content-between py-50 w-100" href="#">
                    <div class="d-flex align-items-center">
                        <div class="avatar mr-50"><img src="${pageContext.request.contextPath}/app-assets/images/portrait/small/avatar-s-14.jpg" alt="png" height="32"></div>
                        <div class="search-data">
                            <p class="search-data-title mb-0">Milena Gibson</p><small class="text-muted">Digital Marketing Manager</small>
                        </div>
                    </div>
                </a></li>
            <li class="auto-suggestion d-flex align-items-center cursor-pointer"><a class="d-flex align-items-center justify-content-between py-50 w-100" href="#">
                    <div class="d-flex align-items-center">
                        <div class="avatar mr-50"><img src="${pageContext.request.contextPath}/app-assets/images/portrait/small/avatar-s-6.jpg" alt="png" height="32"></div>
                        <div class="search-data">
                            <p class="search-data-title mb-0">Anna Strong</p><small class="text-muted">Web Designer</small>
                        </div>
                    </div>
                </a></li>
        </ul>
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
                    <li class="nav-item mr-auto"><a class="navbar-brand" href="${pageContext.request.contextPath}/html/ltr/vertical-menu-template-dark/index.html">
                            <div class="brand-logo"></div>
                            <h2 class="brand-text mb-0">Vuexy</h2>
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
                            <li class="active"><a href="${pageContext.request.contextPath}/admin/dashboard?page=view-products"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Shop">Shop</span></a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/admin/dashboard?page=view-details"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Details">Details</span></a>
                            </li>
                            <li><a href="app-ecommerce-wishlist.html"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Wish List">Wish List</span></a>
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
                    <li class=" nav-item"><a href="page-search.html"><i class="feather icon-search"></i><span class="menu-title" data-i18n="Search">Search</span></a>
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
                                <h2 class="content-header-title float-left mb-0">Shop</h2>
                                <div class="breadcrumb-wrapper col-12">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="index.html">Home</a>
                                        </li>
                                        <li class="breadcrumb-item"><a href="#">eCommerce</a>
                                        </li>
                                        <li class="breadcrumb-item active">Shop
                                        </li>
                                    </ol>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="content-header-right text-md-right col-md-3 col-12 d-md-block d-none">
                        <div class="form-group breadcrum-right">
                            <div class="dropdown">
                                <button class="btn-icon btn btn-primary btn-round btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="feather icon-settings"></i></button>
                                <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item" href="#">Chat</a><a class="dropdown-item" href="#">Email</a><a class="dropdown-item" href="#">Calendar</a></div>
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
                                        <div class="view-options">
                                            <select name="sort" class="price-options form-control" id="ecommerce-price-options" onchange="sortHiddenInput(this)">
                                                <option selected>Sorted</option>
                                                <option value="asc">Lowest</option>
                                                <option value="desc">Highest</option>
                                            </select>
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
                        <section id="ecommerce-searchbar">
                            <div class="row mt-1">
                                <div class="col-sm-12">
                                    <fieldset class="form-group position-relative">
                                        <input type="text" class="form-control search-product" id="iconLeft5" placeholder="Search here">
                                        <div class="form-control-position">
                                            <i class="feather icon-search"></i>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                        </section>
                        <!-- Ecommerce Search Bar Ends -->

                        <!-- Ecommerce Products Starts -->
                        <section id="ecommerce-products" class="grid-view">
                            <c:forEach items="${listP}" var="p">
                                <div class="card ecommerce-card">
                                    <div class="card-content">
                                        <div class="item-img text-center">
                                            <a href="app-ecommerce-details.html">
                                                <img class="img-fluid" src="${pageContext.request.contextPath}/app-assets/images/pages/eCommerce/1.png" alt="img-placeholder">
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
                                                        ${p.price}$
                                                    </h6>
                                                </div>
                                            </div>
                                            <div class="item-name">
                                                <a href="app-ecommerce-details.html">${p.name}</a>
                                                <!--                                                <p class="item-company">By <span class="company-name">Google</span></p>-->
                                            </div>
                                            <div>
                                                <p class="item-description">
                                                    ${p.description}
                                                </p>
                                            </div>
                                        </div>
                                        <div class="item-options text-center">
                                            <div class="button-group d-flex justify-content-around mt-3">
                                                <a class="btn btn-primary btn-equal-size view-btn" href="${pageContext.request.contextPath}/admin/dashboard?page=view-product-details&productID=${p.id}" style="background-color: #3498db; color: #fff; transition: background-color 0.3s;">
                                                    <i class="feather icon-eye"></i> <span>View</span>
                                                </a>
                                                <button class="btn btn-success btn-equal-size wishlist" style="background-color: #27ae60; color: #fff; transition: background-color 0.3s;">
                                                    <i class="feather icon-edit"></i> <span>Update</span>
                                                </button>
                                                <button class="btn btn-danger btn-equal-size delete-btn" style="background-color: #e74c3c; color: #fff; transition: background-color 0.3s;">
                                                    <i class="feather icon-trash"></i> <span>Delete</span>
                                                </button>
                                            </div>
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
                        <form id="filter-form2" action="${pageContext.request.contextPath}/admin/manageproduct" method="GET">
                            <input type="hidden" name="action" value="filter-products">
                            <input type="hidden" name="sort" id="sortHidden">
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
                                                        <span class="ml-50" value="">&lt;=$50</span>
                                                    </span>
                                                </li>
                                                <li>
                                                    <span class="vs-radio-con vs-radio-primary py-25">
                                                        <input type="radio" name="price-range" value="50-100">
                                                        <span class="vs-radio">
                                                            <span class="vs-radio--border"></span>
                                                            <span class="vs-radio--circle"></span>
                                                        </span>
                                                        <span class="ml-50" value="">$50 - $100</span>
                                                    </span>
                                                </li>
                                                <li>
                                                    <span class="vs-radio-con vs-radio-primary py-25">
                                                        <input type="radio" name="price-range" value="100-300">
                                                        <span class="vs-radio">
                                                            <span class="vs-radio--border"></span>
                                                            <span class="vs-radio--circle"></span>
                                                        </span>
                                                        <span class="ml-50" value="">$100 - $300</span>
                                                    </span>
                                                </li>
                                                <li>
                                                    <span class="vs-radio-con vs-radio-primary py-25">
                                                        <input type="radio" name="price-range" value="300-1000">
                                                        <span class="vs-radio">
                                                            <span class="vs-radio--border"></span>
                                                            <span class="vs-radio--circle"></span>
                                                        </span>
                                                        <span class="ml-50" value="">&gt;= $300</span>
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
                                                        <span class="vs-radio-con vs-radio-primary py-25">
                                                            <input type="radio" name="category-filter" value="${cate.id}">
                                                            <span class="vs-radio">
                                                                <span class="vs-radio--border"></span>
                                                                <span class="vs-radio--circle"></span>
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
                                                                    <span class="vs-checkbox--check">
                                                                        <i class="vs-icon feather icon-check"></i>
                                                                    </span>
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
                                                                    <span class="vs-checkbox--check">
                                                                        <i class="vs-icon feather icon-check"></i>
                                                                    </span>
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
                                                                    <span class="vs-checkbox--check">
                                                                        <i class="vs-icon feather icon-check"></i>
                                                                    </span>
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
                                                                    <span class="vs-checkbox--check">
                                                                        <i class="vs-icon feather icon-check"></i>
                                                                    </span>
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
                                        <!-- Clear Filters Starts -->
                                        <div id="clear-filters">
                                            <button id="clear-filters-btn" class="btn btn-block btn-primary">CLEAR ALL FILTERS</button>
                                        </div>
                                        <!-- Clear Filters Ends -->
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
            <p class="clearfix blue-grey lighten-2 mb-0"><span class="float-md-left d-block d-md-inline-block mt-25">COPYRIGHT &copy; 2020<a class="text-bold-800 grey darken-2" href="https://1.envato.market/pixinvent_portfolio" target="_blank">Pixinvent,</a>All rights Reserved</span><span class="float-md-right d-none d-md-block">Hand-crafted & Made with<i class="feather icon-heart pink"></i></span>
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
        <!-- END: Page Vendor JS-->

        <!-- BEGIN: Theme JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app-menu.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/components.js"></script>
        <!-- END: Theme JS-->

        <!-- BEGIN: Page JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/pages/app-ecommerce-shop.js"></script>
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
                                                document.getElementById('clear-filters-btn').addEventListener('click', function () {
                                                    clearFilters();
                                                });

                                                function sortHiddenInput(selectElement) {
                                                    var selectedValue = selectElement.value;
                                                    if (selectedValue === 'Sorted') {
                                                        document.getElementById('sortHidden').value = null;
                                                    } else {
                                                        document.getElementById('sortHidden').value = selectedValue;
                                                    }
                                                }
        </script>    
    </body>
    <!-- END: Body-->

</html>