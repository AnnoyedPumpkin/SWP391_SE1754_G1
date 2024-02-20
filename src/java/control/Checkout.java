/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import constant.Constant;
import dao.CommonDao;
import entity.Account;
import entity.Cart;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

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
            request.setAttribute("shopping_cart_details", p);
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
        switch (action) {
            case "calculateTotalPrice":
                int quantity = Integer.parseInt(request.getParameter("input_number"));
                int pdid = Integer.parseInt(request.getParameter("pdid"));
                commonDao.updateQuantity(quantity,pdid);
                request.getRequestDispatcher("/Checkout").forward(request, response);
                break;
            case "sendCode":
                
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
