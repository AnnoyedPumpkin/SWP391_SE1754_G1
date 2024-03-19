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
        <title>User View - Vuexy - Bootstrap HTML admin template</title>
        <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/app-assets/images/ico/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600" rel="stylesheet">

        <!-- BEGIN: Vendor CSS-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/app-assets/vendors/css/vendors.min.css">
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
                                                <div class="media-body"><span class="item-title text-truncate text-bold-500 d-block mb-50">Apple - Macbook� (Latest Model) - 12" Display - Intel Core M5 - 8GB Memory - 512GB Flash Storage - Space Gray</span><span class="item-desc font-small-2 text-truncate d-block"> MacBook delivers a full-size experience in the lightest and most compact Mac notebook ever. With a full-size keyboard, force-sensing trackpad, 12-inch Retina display,1 sixth-generation Intel Core M processor, multifunctional USB-C port, and now up to 10 hours of battery life,2 MacBook features big thinking in an impossibly compact form.</span>
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
                                    <div class="dropdown-divider"></div><a class="dropdown-item" data-toggle="modal" data-target="#logoutModalAdmin" href="#"><i class="feather icon-power"></i> Logout</a>
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
                            <li class="active"><a href="${pageContext.request.contextPath}/admin/dashboard"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Analytics">Analytics</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" navigation-header"><span>Apps</span>
                    </li>
                    <li class=" nav-item"><a href="#"><i class="feather icon-shopping-cart"></i><span class="menu-title" data-i18n="Ecommerce">Ecommerce</span></a>
                        <ul class="menu-content">
                            <li><a href="${pageContext.request.contextPath}/admin/manageproduct"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Shop">Shop</span></a>
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
                            <li><a href="profile?action=editProfile"><i class="feather icon-circle"></i><span class="menu-item" data-i18n="Edit">Edit</span></a>
                            </li>
                        </ul>
                    </li>
                    <li class=" navigation-header"><span>pages</span>
                    </li>
                    <li class=" nav-item"><a href="sellerProfile"><i class="feather icon-user"></i><span class="menu-title" data-i18n="Profile">Profile</span></a>
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
                </div>
                <div class="content-body">
                    <!-- page users view start -->
                    <section class="page-users-view">
                        <div class="row">
                            <!-- account start -->
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <div class="card-title">Account</div>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="users-view-image">
                                                <img src="${pageContext.request.contextPath}/app-assets/images/portrait/small/avatar-s-12.jpg" class="users-avatar-shadow w-100 rounded mb-2 pr-2 ml-1" alt="avatar">
                                            </div>
                                            <div class="col-12 col-sm-9 col-md-6 col-lg-5">
                                                <table>
                                                    <tr>
                                                        <td class="font-weight-bold">Username:</td>
                                                        <td>${username}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="font-weight-bold">Email:</td>
                                                        <td>${email}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="font-weight-bold">Member Code:</td>
                                                        <td>${member_code}</td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-12 col-md-12 col-lg-5">
                                                <table class="ml-0 ml-sm-0 ml-lg-0">
                                                    <tr>
                                                        <td class="font-weight-bold">Phone Number:</td>
                                                        <td>${accountDetail.phone_number}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="font-weight-bold">Address</td>
                                                        <td>${accountDetail.address}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="font-weight-bold">Date of Birth:</td>
                                                        <td>${accountDetail.dob}</td>
                                                    </tr><tr>
                                                        <td class="font-weight-bold">Gender</td>
                                                        <td>${accountDetail.gender == true ? 'Male' : 'Female'}</td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-12">
                                                <a href="sellerProfile?action=editProfile" class="btn btn-primary mr-1"><i class="feather icon-edit-1"></i> Edit</a>
                                                <button class="btn btn-outline-danger"><i class="feather icon-trash-2"></i> Delete</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- account end -->
                            <!-- Change password start -->
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header border-bottom mx-2 px-0">
                                        <h6 class="border-bottom py-1 mb-0 font-medium-2"><i class="feather icon-lock mr-50 "></i>Change Password
                                        </h6>
                                    </div>
                                    <div class="card-body px-75">
                                        <form action="sellerProfile?action=changePassword" method="POST">
                                            <div class="row">
                                                <div class="col-12 col-sm-6">
                                                    <div class="form-group">
                                                        <div class="controls">
                                                            <label>Old Password</label>
                                                            <input type="password" class="form-control" name="password">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="controls">
                                                            <label>New Password</label>
                                                            <input type="password" class="form-control" name="newPassword">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="controls">
                                                            <label>Confirm New Password</label>
                                                            <input type="password" class="form-control" name="newPassword2">
                                                        </div>
                                                    </div>
                                                    <div style="color: greenyellow">
                                                        ${message}
                                                    </div>
                                                    <div style = "color: red">
                                                        ${error1}
                                                    </div>
                                                </div>                                       
                                                    <div class="col-12 d-flex flex-sm-row flex-column justify-content-start mt-1">
                                                    <button type="submit" class="btn btn-primary glow mb-1 mb-sm-0 mr-0 mr-sm-1">Change Password</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- Change password end -->
                        </div>
                    </section>
                    <!-- page users view end -->

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
        <!-- END: Page Vendor JS-->

        <!-- BEGIN: Theme JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app-menu.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/core/app.js"></script>
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/components.js"></script>
        <!-- END: Theme JS-->

        <!-- BEGIN: Page JS-->
        <script src="${pageContext.request.contextPath}/app-assets/js/scripts/pages/app-user.js"></script>
        <!-- END: Page JS-->

    </body>
    <!-- END: Body-->

</html>