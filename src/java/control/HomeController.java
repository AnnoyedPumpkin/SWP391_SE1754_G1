/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.CommonDao;
import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class HomeController extends HttpServlet {

    CommonDao commonDao = new CommonDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = "";
        String page = request.getParameter("page") == null ? "default" : request.getParameter("page");
        switch (page) {
            case "":
                break;
            default:
                int accountId = (Integer) session.getAttribute("acc_id");
                List<Cart> p = commonDao.getShoppingCartDetailsByAccountId(accountId);
                int cartProductCount = p.size();
                session.setAttribute("cartProductCount", cartProductCount);
                session.setAttribute("shopping_cart_details", p);
                url = "views/common/homepage.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        int accountId = (Integer) session.getAttribute("acc_id");
        switch (action) {
            case "deleteProduct":
                int p_id = Integer.parseInt(request.getParameter("p_id"));
                //commonDao.deleteProductInCartDetailByProductId(p_id);
                List<Cart> up = commonDao.getShoppingCartDetailsByAccountId(accountId);
                session.setAttribute("shopping_cart_details", up);
                request.getRequestDispatcher("views/common/homepage.jsp").forward(request, response);
                break;
        }
    }

}
