/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import constant.Constant;
import dao.CommonDao;
import dao.ProductDao;
import entity.Account;
import entity.Account_Detail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class ProductController extends HttpServlet {
    CommonDao commonDAO;
    ProductDao productDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        commonDAO = new CommonDao();
        HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
            // Lấy thông tin tài khoản từ session
            Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
            // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
            Account_Detail accountDetail = commonDAO.getAccountDetailByAccountId(account.getId());
            // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
            if (accountDetail != null) {
                request.setAttribute("accountDetail", accountDetail);
                request.setAttribute("username", accountDetail.getUserName());
                request.setAttribute("email", account.getEmail());
                switch (action) {
                    case "":
                        url = "home";
                        break;
                    case "add1tocart":
                        // Chuyển hướng sang trang hiển thị thông tin cá nhân
                        addProductToCart(request, response, accountDetail);
                        break;
                }
            } else {
                System.out.println("Error cannot get account detail in profile controller");
            }
        } else {
            // Nếu không có session hoặc không có thông tin tài khoản trong session, có thể chuyển hướng người dùng đến trang đăng nhập hoặc xử lý một cách phù hợp.
            url = "views/common/login.jsp";
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addProductToCart(HttpServletRequest request, HttpServletResponse response, Account_Detail accountDetail) {
        try {
            String url = "";
            String pId = request.getParameter("id");
            int productId = 0;
            if (pId != null) {
                productId = Integer.parseInt(pId);
            }
            productDAO = new ProductDao();
            // handle cho nay de gui them address vao.
            // Address va Cart Code.
            // Cho nay se phai handle viec truyen vao tham so address, cartcode, va discoundId de truyen vao ham.
            int result = productDAO.addProductToCart(productId, 1, accountDetail, "Dia chi test", "CARTCODE", 1);
            if (result > 0) {
                url = "home";
                response.sendRedirect(url);
            } else {
                System.out.println("Bug in add to cart");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
