<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <style>
            body {
                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
                text-align: center;
                color: #777;
            }

            .invoice-box {
                max-width: 800px;
                margin: auto;
                padding: 30px;
                border: 1px solid #eee;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
                font-size: 16px;
                line-height: 24px;
                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
                color: #555;
            }
            .hh-grayBox {
                background-color: #F8F8F8;
                margin-bottom: 20px;
                padding: 35px;
                margin-top: 20px;
            }
            .pt45{
                padding-top:45px;
            }
            .order-tracking{
                text-align: center;
                width: 33.33%;
                position: relative;
                display: block;
            }
            .order-tracking .is-complete{
                display: block;
                position: relative;
                border-radius: 50%;
                height: 30px;
                width: 30px;
                border: 0px solid #AFAFAF;
                background-color: #f7be16;
                margin: 0 auto;
                transition: background 0.25s linear;
                -webkit-transition: background 0.25s linear;
                z-index: 2;
            }
            .order-tracking .is-complete:after {
                display: block;
                position: absolute;
                content: '';
                height: 14px;
                width: 7px;
                top: -2px;
                bottom: 0;
                left: 5px;
                margin: auto 0;
                border: 0px solid #AFAFAF;
                border-width: 0px 2px 2px 0;
                transform: rotate(45deg);
                opacity: 0;
            }
            .order-tracking.completed .is-complete{
                border-color: #27aa80;
                border-width: 0px;
                background-color: #27aa80;
            }
            .order-tracking.completed .is-complete:after {
                border-color: #fff;
                border-width: 0px 3px 3px 0;
                width: 7px;
                left: 11px;
                opacity: 1;
            }
            .order-tracking p {
                color: #A4A4A4;
                font-size: 16px;
                margin-top: 8px;
                margin-bottom: 0;
                line-height: 20px;
            }
            .order-tracking p span{
                font-size: 14px;
            }
            .order-tracking.completed p{
                color: #000;
            }
            .order-tracking::before {
                content: '';
                display: block;
                height: 3px;
                width: calc(100% - 40px);
                background-color: #f7be16;
                top: 13px;
                position: absolute;
                left: calc(-50% + 20px);
                z-index: 0;
            }
            .order-tracking:first-child:before{
                display: none;
            }
            .order-tracking.completed:before{
                background-color: #27aa80;
            }
        </style>
    </head>
    <body>
        <div class="invoice-box">
            <div>
                <img src="https://ci3.googleusercontent.com/meips/ADKq_NZcoI57e0bGskDmQdE3N58l0xHIT_ePVKIXpWyn8frtZlBF86XDRgA_r5vD99Ke4d9ZKK4XAgx6Q5UKFOSbRtHtPtAMg7aSFHxfvfpAx386ffzSpyweYhLj=s0-d-e1-ft#https://assets.unlayer.com/projects/218606/1708650172744-689422.png" alt="Company logo" style="width: 100%; max-width: 100px" />
            </div>
            <div class="row">
                <div class="col-12 col-md-12 hh-grayBox pt45 pb20">
                    <div class="row justify-content-between">
                        <div class="order-tracking completed">
                            <span class="is-complete"></span>
                            <p>Pending<br><span>Mon, June 24</span></p>
                        </div>
                        <div class="order-tracking ">
                            <span class="is-complete"></span>
                            <p>Out for delivery<br></p>
                        </div>
                        <div class="order-tracking">
                            <span class="is-complete"></span>
                            <p>Delivered<br></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>