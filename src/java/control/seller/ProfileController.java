/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.seller;

import constant.Constant;
import dao.SellerDao;
import entity.Account;
import entity.Account_Detail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Win 10
 */
public class ProfileController extends HttpServlet {

    SellerDao sellerDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        sellerDao = new SellerDao();
        HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
        String action = request.getParameter("action") == null ? "profile" : request.getParameter("action");
        if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
            // Lấy thông tin tài khoản từ session
            Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
            // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
            Account_Detail accountDetail = sellerDao.getAccountDetailByAccountId(account.getId());
            // Đặt thuộc tính "accountDetail" vào request để hiển thị trên giao diện
            request.setAttribute("accountDetail", accountDetail);
            request.setAttribute("username", accountDetail.getUserName());
            request.setAttribute("email", account.getEmail());
            request.setAttribute("member_code", account.getMember_code());

            switch (action) {
                case "profile":
                    // Chuyển hướng sang trang hiển thị thông tin cá nhân
                    url = "views/common/sellerProfile.jsp";
                    break;
                case "editProfile":
                    url = "views/common/sellerEditProfile.jsp";
                    break;
            }
        } else {
            // Nếu không có session hoặc không có thông tin tài khoản trong session, có thể chuyển hướng người dùng đến trang đăng nhập hoặc xử lý một cách phù hợp.
            url = "views/common/login.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
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

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
