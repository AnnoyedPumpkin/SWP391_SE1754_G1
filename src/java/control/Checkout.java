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
        int accountId = (Integer) session.getAttribute("acc_id");

        if (isLoggedIn != null && isLoggedIn) {
            List<Cart> p = commonDao.getShoppingCartDetailsByAccountId(accountId);
            session.setAttribute("shopping_cart_details", p);
            List<Discount> dis = commonDao.getActiveDiscountList();
            session.setAttribute("disountList", dis);
            request.getRequestDispatcher("views/common/checkoutstep1.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("views/common/checkoutstep3.jsp").forward(request, response);
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
                String subtotal = request.getParameter("tps_va_in");
                String discount = request.getParameter("dis_va_in");
                String total = request.getParameter("tpf_va_in");
                int[] quantities = new int[quantityStrings.length];
                for (int i = 0; i < quantityStrings.length; i++) {
                    quantities[i] = Integer.parseInt(quantityStrings[i]);
                }

                int[] productDetailIds = new int[productDetailIdStrings.length];
                for (int i = 0; i < productDetailIdStrings.length; i++) {
                    productDetailIds[i] = Integer.parseInt(productDetailIdStrings[i]);
                }

                for (int i = 0; i < quantities.length; i++) {
                    commonDao.updateQuantityByProductDetailId(quantities[i], productDetailIds[i]);
                }

                Account acc_infor = commonDao.getAccountInformationByAccountId(accountId);
                List<Cart> p = commonDao.getShoppingCartDetailsByAccountId(accountId);
                session.setAttribute("shopping_cart_details", p);
                session.setAttribute("account_information", acc_infor);
                request.setAttribute("subtotal", subtotal);
                request.setAttribute("discount", discount);
                request.setAttribute("total", total);
                request.getRequestDispatcher("views/common/checkoutstep2.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

}
