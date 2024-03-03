/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import constant.Constant;
import dao.CommonDao;
import entity.Account;
import entity.Cart;
import entity.Discount;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.net.*;
import java.util.Date;

/**
 *
 * @author FPT-LAPTOP
 */
public class Checkout extends HttpServlet {

    CommonDao commonDao = new CommonDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if (isLoggedIn != null && isLoggedIn) {
            int accountId = (Integer) session.getAttribute("acc_id");
            Account acc_infor = commonDao.getAccountInformationByAccountId(accountId);
            session.setAttribute("account_information", acc_infor);
            List<Cart> p = commonDao.getShoppingCartDetailsByAccountId(accountId);
            session.setAttribute("shopping_cart_details", p);
            List<Discount> dis = commonDao.getActiveDiscountList();
            session.setAttribute("disountList", dis);
            request.getRequestDispatcher("views/common/checkoutstep1.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("views/common/checkoutstep1.jsp").forward(request, response);
        }
        //       request.getRequestDispatcher("views/common/checkoutstep1.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        int accountId = (Integer) session.getAttribute("acc_id");
        switch (action) {
            case "placeOrder":
                //Update stock in product detail by product id.
                String[] remainingStockStrings = request.getParameterValues("remaining_stock");
                String[] productIdStrings = request.getParameterValues("product_id");
                int[] remainingStock = new int[remainingStockStrings.length];
                for (int i = 0; i < remainingStockStrings.length; i++) {
                    remainingStock[i] = Integer.parseInt(remainingStockStrings[i]);
                }
                int[] productId = new int[productIdStrings.length];
                for (int i = 0; i < productIdStrings.length; i++) {
                    productId[i] = Integer.parseInt(productIdStrings[i]);
                }
                for (int i = 0; i < remainingStock.length; i++) {
                    commonDao.updateProductDetailStock(remainingStock[i], productId[i]);
                }
                //Add invoice and add cart code in Cart.
                Date currentDate = new Date();
                java.sql.Date invoiceDate = new java.sql.Date(currentDate.getTime());
                Double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
                String address = request.getParameter("hiddenDeliveryAddress");
                String cartCode = commonDao.generateRandomCartCode();
                commonDao.addCartCodeForCartByAccountId(cartCode, accountId);
                commonDao.addInvoice(accountId, invoiceDate, totalPrice, cartCode, address);
                int invoiceId = commonDao.getInvoiceIdByCartCode(cartCode);
                //Add invoice detail.
                String[] unitPriceStrings = request.getParameterValues("unit_price");
                String[] totalPricePerProductStrings = request.getParameterValues("total_price_per_product");
                String[] quantityStrings2 = request.getParameterValues("quantity_per_product");
                double[] unitPrice = new double[unitPriceStrings.length];
                for (int i = 0; i < unitPriceStrings.length; i++) {
                    unitPrice[i] = Double.parseDouble(unitPriceStrings[i]);
                }
                double[] totalPricePerProduct = new double[totalPricePerProductStrings.length];
                for (int i = 0; i < totalPricePerProductStrings.length; i++) {
                    totalPricePerProduct[i] = Double.parseDouble(totalPricePerProductStrings[i]);
                }
                int[] quantities2 = new int[quantityStrings2.length];
                for (int i = 0; i < quantityStrings2.length; i++) {
                    quantities2[i] = Integer.parseInt(quantityStrings2[i]);
                }
                for (int i = 0; i < productIdStrings.length; i++) {
                    commonDao.addInvoiceDetail(invoiceId, productId[i], quantities2[i], unitPrice[i], totalPricePerProduct[i]);
                }
                //Delete Cart and CartDetail.
                int cartId = Integer.parseInt(request.getParameter("cart_id"));
//                commonDao.deleteCartDetailByCartId(cartId);
//                commonDao.deleteCartById(cartId);
                request.getRequestDispatcher("views/common/checkoutstep3.jsp").forward(request, response);
                break;
            case "deleteProduct":
                int p_id = Integer.parseInt(request.getParameter("p_id"));
                //commonDao.deleteProductInCartDetailByProductId(p_id);
                List<Cart> up = commonDao.getShoppingCartDetailsByAccountId(accountId);
                session.setAttribute("shopping_cart_details", up);
                request.getRequestDispatcher("views/common/checkoutstep1.jsp").forward(request, response);
                break;
            case "proceedCheckout":
                String[] quantityStrings = request.getParameterValues("input_number");
                String[] productDetailIdStrings = request.getParameterValues("pro_det_id");
                Double subtotal = Double.parseDouble(request.getParameter("tps_va_in"));
                Double discount = Double.parseDouble(request.getParameter("dis_va_in"));
                Double total = Double.parseDouble(request.getParameter("tpf_va_in"));
                int[] quantities = new int[quantityStrings.length];
                for (int i = 0; i < quantityStrings.length; i++) {
                    quantities[i] = Integer.parseInt(quantityStrings[i]);
                }
                int[] productDetailIds = new int[productDetailIdStrings.length];
                for (int i = 0; i < productDetailIdStrings.length; i++) {
                    productDetailIds[i] = Integer.parseInt(productDetailIdStrings[i]);
                }
                for (int i = 0; i < quantities.length; i++) {
                    commonDao.updateQuantityByProductId(quantities[i], productDetailIds[i]);
                }
                List<Cart> p = commonDao.getShoppingCartDetailsByAccountId(accountId);
                session.setAttribute("shopping_cart_details", p);
                request.setAttribute("subtotal", subtotal);
                request.setAttribute("discount", discount);
                request.setAttribute("total", total);
                request.getRequestDispatcher("views/common/checkoutstep2.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private String gethtmlTemplate() {
        String htmlContent = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "    <head>\n"
                + "        <meta charset=\"utf-8\" />\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n"
                + "\n"
                + "        <style>\n"
                + "            body {\n"
                + "                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n"
                + "                text-align: center;\n"
                + "                color: #777;\n"
                + "            }\n"
                + "\n"
                + "            body h1 {\n"
                + "                font-weight: 300;\n"
                + "                margin-bottom: 0px;\n"
                + "                padding-bottom: 0px;\n"
                + "                color: #000;\n"
                + "            }\n"
                + "\n"
                + "            body h3 {\n"
                + "                font-weight: 300;\n"
                + "                margin-top: 10px;\n"
                + "                margin-bottom: 20px;\n"
                + "                font-style: italic;\n"
                + "                color: #555;\n"
                + "            }\n"
                + "\n"
                + "            body a {\n"
                + "                color: #06f;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box {\n"
                + "                max-width: 800px;\n"
                + "                margin: auto;\n"
                + "                padding: 30px;\n"
                + "                border: 1px solid #eee;\n"
                + "                box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);\n"
                + "                font-size: 16px;\n"
                + "                line-height: 24px;\n"
                + "                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n"
                + "                color: #555;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table {\n"
                + "                width: 100%;\n"
                + "                line-height: inherit;\n"
                + "                text-align: left;\n"
                + "                border-collapse: collapse;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table td {\n"
                + "                padding: 5px;\n"
                + "                vertical-align: top;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr td:nth-child(2) {\n"
                + "                text-align: right;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.top table td {\n"
                + "                padding-bottom: 20px;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.top table td.title {\n"
                + "                font-size: 45px;\n"
                + "                line-height: 45px;\n"
                + "                color: #333;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.information table td {\n"
                + "                padding-bottom: 40px;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.heading td {\n"
                + "                background: #eee;\n"
                + "                border-bottom: 1px solid #ddd;\n"
                + "                font-weight: bold;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.details td {\n"
                + "                padding-bottom: 20px;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.item td {\n"
                + "                border-bottom: 1px solid #eee;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.item.last td {\n"
                + "                border-bottom: none;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.total td:nth-child(2) {\n"
                + "                border-top: 2px solid #eee;\n"
                + "                font-weight: bold;\n"
                + "            }\n"
                + "\n"
                + "            @media only screen and (max-width: 600px) {\n"
                + "                .invoice-box table tr.top table td {\n"
                + "                    width: 100%;\n"
                + "                    display: block;\n"
                + "                    text-align: center;\n"
                + "                }\n"
                + "\n"
                + "                .invoice-box table tr.information table td {\n"
                + "                    width: 100%;\n"
                + "                    display: block;\n"
                + "                    text-align: center;\n"
                + "                }\n"
                + "            }\n"
                + "        </style>\n"
                + "    </head>\n"
                + "\n"
                + "    <body>\n"
                + "        <div class=\"invoice-box\">\n"
                + "            <table>\n"
                + "                <tr class=\"top\">\n"
                + "                    <td colspan=\"2\">\n"
                + "                        <table>\n"
                + "                            <tr>\n"
                + "                                <td class=\"title\">\n"
                + "                                    <img src=\"test.jpg\" alt=\"Company logo\" style=\"width: 100%; max-width: 100px\" />\n"
                + "                                </td>\n"
                + "\n"
                + "                                <td>\n"
                + "                                    Invoice #: 123<br />\n"
                + "                                    Created: January 1, 2023<br />\n"
                + "                                    Due: February 1, 2023\n"
                + "                                </td>\n"
                + "                            </tr>\n"
                + "                        </table>\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr class=\"information\">\n"
                + "                    <td colspan=\"2\">\n"
                + "                        <table>\n"
                + "                            <tr>\n"
                + "                                <td>\n"
                + "                                    Sparksuite, Inc.<br />\n"
                + "                                    12345 Sunny Road<br />\n"
                + "                                    Sunnyville, TX 12345\n"
                + "                                </td>\n"
                + "\n"
                + "                                <td>\n"
                + "                                    Acme Corp.<br />\n"
                + "                                    John Doe<br />\n"
                + "                                    john@example.com\n"
                + "                                </td>\n"
                + "                            </tr>\n"
                + "                        </table>\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr class=\"heading\">\n"
                + "                    <td>Payment Method</td>\n"
                + "\n"
                + "                    <td>Check #</td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr class=\"details\">\n"
                + "                    <td>Check</td>\n"
                + "\n"
                + "                    <td>1000</td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr class=\"heading\">\n"
                + "                    <td>Item</td>\n"
                + "\n"
                + "                    <td>Price</td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr class=\"item\">\n"
                + "                    <td>Website design</td>\n"
                + "\n"
                + "                    <td>$300.00</td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr class=\"item\">\n"
                + "                    <td>Hosting (3 months)</td>\n"
                + "\n"
                + "                    <td>$75.00</td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr class=\"item last\">\n"
                + "                    <td>Domain name (1 year)</td>\n"
                + "\n"
                + "                    <td>$10.00</td>\n"
                + "                </tr>\n"
                + "\n"
                + "                <tr class=\"total\">\n"
                + "                    <td></td>\n"
                + "\n"
                + "                    <td>Total: $385.00</td>\n"
                + "                </tr>\n"
                + "            </table>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
        return htmlContent;
    }
}
