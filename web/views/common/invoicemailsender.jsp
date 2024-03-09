<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        fraimwork - css include 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

        icon - css include 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/fontawesome.css">

        animation - css include 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/animate.css">

        nice select - css include 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/nice-select.css">

        carousel - css include 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/slick.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/slick-theme.css">

        popup images & videos - css include 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">

        jquery ui - css include 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/jquery-ui.css">

        custom - css include 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">
        <style>
            body {
                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
                text-align: center;
                color: #777;
            }

            body h1 {
                font-weight: 300;
                margin-bottom: 0px;
                padding-bottom: 0px;
                color: #000;
            }

            body h3 {
                font-weight: 300;
                margin-top: 10px;
                margin-bottom: 20px;
                font-style: italic;
                color: #555;
            }

            body a {
                color: #06f;
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

            .invoice-box table {
                width: 100%;
                line-height: inherit;
                text-align: left;
                border-collapse: collapse;
            }

            .invoice-box table td {
                padding: 5px;
                vertical-align: top;
            }

            .invoice-box table tr td:nth-child(2) {
                text-align: right;
            }

            .invoice-box table tr.top table td {
                padding-bottom: 20px;
            }

            .invoice-box table tr.top table td.title {
                font-size: 45px;
                line-height: 45px;
                color: #333;
            }

            .invoice-box table tr.information table td {
                padding-bottom: 40px;
            }

            .invoice-box table tr.heading td {
                background: #eee;
                border-bottom: 1px solid #ddd;
                font-weight: bold;
                text-align: right;
            }
            .invoice-box table tr.heading td:first-child {
                background: #eee;
                border-bottom: 1px solid #ddd;
                font-weight: bold;
                text-align: left;
            }

            .invoice-box table tr.heading td:nth-child(2) {
                padding-right: 120px;
            }
            .invoice-box table tr.details td {
                padding-bottom: 20px;
            }

            .invoice-box table tr.item td {
                border-bottom: 1px solid #eee;
                text-align: right;
            }
            .invoice-box table tr.item td:first-child {
                border-bottom: 1px solid #eee;
                text-align: left;
            }
            .invoice-box table tr.item td:nth-child(2) {
                text-align: center;
            }
            .invoice-box table tr.item.last td {
                border-bottom: none;
            }

            .invoice-box table tr.total td:nth-child(3) {
                border-top: 2px solid #eee;
                font-weight: bold;
                text-align: right;
            }

            @media only screen and (max-width: 600px) {
                .invoice-box table tr.top table td {
                    width: 100%;
                    display: block;
                    text-align: center;
                }

                .invoice-box table tr.information table td {
                    width: 100%;
                    display: block;
                    text-align: center;
                }
            }
        </style>
    </head>
    <body>
        <div class="invoice-box">
            <table>
                <tr class="top">
                    <td colspan="3">
                        <table>
                            <tr>
                                <td class="title">
                                    <img src="${pageContext.request.contextPath}\assets\images\Hieuimagetest\test.jpg" alt="Company logo" style="width: 100%; max-width: 100px" />
                                </td>

                                <td>
                                    <c:if test="${not empty sessionScope.invoice_list_detail}">
                                        <c:set var="firstInvoice" value="${sessionScope.invoice_list_detail[0]}" />
                                        Invoice #: ${firstInvoice.cartCode}
                                        <br />
                                        Created at: <script>const months = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];
                                            const monthIndex = new Date().getMonth();
                                            const monthString = months[monthIndex];
                                            document.write(new Date().getHours() + ":" + new Date().getMinutes() + ", " + new Date().getDate() + "/" + monthString + "/" + new Date().getFullYear());</script><br />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr class="information">
                        <td colspan="3">
                            <table>
                                <tr>
                                    <c:if test="${not empty account_information}">
                                        <td>
                                            Recipient: ${account_information.getAcc_det().getUsername()}<br />
                                            Delivery address: ${firstInvoice.address}<br />
                                            Contact number: ${account_information.getAcc_det().getPhone_number()}
                                        </td>
                                        <td>
                                            Sender: Brava Shop<br />
                                            Branch: Group 1, Ha Noi
                                        </td>

                                    </c:if>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr class="heading">
                        <td>Item</td>
                        <td>Unit Price x (Quantity)</td>
                        <td>Price</td>
                    </tr>
                    <c:forEach items="${invoice_list_detail}" var="in">
                        <tr class="item">
                            <td>${in.pro.name}</td>
                            <td>${in.pro.price}VND x(${in.in_de.quantity})</td>
                            <td>${in.in_de.total_price}VND</td>
                        </tr>

                    </c:forEach>

                    <tr class="item">
                        <td></td>
                        <td></td>
                        <td>Subtotal: ${firstInvoice.total_price}VND</td>
                    </tr>
                    <tr class="item">
                        <td></td>
                        <td></td>
                        <td>Discount(%): ${firstInvoice.discount_percent}</td>
                    </tr>
                    <tr class="total">
                        <td>Total: </td>
                        <td></td>
                        <td>${firstInvoice.total_price-(firstInvoice.total_price*firstInvoice.discount_percent)}VND</td>
                    </tr>
                </c:if>
            </table>
            <c:if test="${not empty hhh}">
                ${hhh}
            </c:if>
        </div>
    </body>
</html>
<%--<c:if test="${not empty hhh}">
    ${hhh}
</c:if>--%>