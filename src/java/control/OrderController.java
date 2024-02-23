/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import constant.Constant;
import dao.CommonDao;
import dao.OrderDao;
import entity.Account;
import entity.Account_Detail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;



public class OrderController extends HttpServlet {
    CommonDao commonDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String url = "";
        commonDAO = new CommonDao();
        HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
        String action = request.getParameter("action") == null ? "order" : request.getParameter("action");
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
                    case "order":
                        // Chuyển hướng sang trang hiển thị thông tin cá nhân
//                        viewOrderHistory(request, response);
                        break;
                    case "viewOrderDetail":
//                        viewOrderDetail(request, response);
                        break;
                }

            } else {
                System.out.println("Error cannot get account detail in profile controller");
            }
        } else {
            // Nếu không có session hoặc không có thông tin tài khoản trong session, có thể chuyển hướng người dùng đến trang đăng nhập hoặc xử lý một cách phù hợp.
            url = "views/common/login.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

//    private void viewOrderHistory(HttpServletRequest request, HttpServletResponse response) {
//         HttpSession session = request.getSession(false);
//            String indexPage = request.getParameter("index");
//            if(indexPage == null) {
//                indexPage = "1";
//            }
//            int index = Integer.parseInt(indexPage);
//        try {
//            if (session != null) {
//                Account account = (Account) session.getAttribute("account");
//                int userId = account.getId();
//                OrderDao dao = new OrderDao();
//                int count = dao.getOrderHistoryCount(userId);
//                if (count > 0) {
//                    List<Invoice> Orders = dao.getOrderHistory(userId, index);
//                    int lastPage = count / 4;
//                    if (count % 4 != 0) {
//                        lastPage++;
//                    }
//                    request.setAttribute("lastPage", lastPage);
//                    request.setAttribute("OrdersList", Orders);
//                    request.getRequestDispatcher("historyOrder.jsp").forward(request, response);
//                } else {
//                    String msg = "Ban chua co don hang";
//                    request.setAttribute("errorMsg", msg);
//                    request.getRequestDispatcher("profile.jsp").forward(request, response);
//                }
//                       
//            } else {
//                response.sendRedirect("login.jsp");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession(false);
//        String orderId = request.getParameter("orderId");
//        String indexPage = request.getParameter("index");
//        if (indexPage == null) {
//            indexPage = "1";
//        }
//        int index = Integer.parseInt(indexPage);
//        if (orderId != null) {
//            try {
//                int order_id = Integer.parseInt(orderId);
//                if (session != null) {
//                    Account account = (Account) session.getAttribute("account");
//                    int userId = account.getId();
//                    OrderDetailDAO dao = new OrderDetailDAO();
//                    int count = dao.getOrderDetailCount(order_id);
//                    if (count > 0) {
//                        List<OrderDetail> OrderDetails = dao.getOrderDetailList(order_id, index);
//                        int lastPage = count / 4;
//                        if (count % 4 != 0) {
//                            lastPage++;
//                        }
//                        request.setAttribute("orderId", orderId);
//                        request.setAttribute("lastPage", lastPage);
//                        request.setAttribute("OrderDetailList", OrderDetails);
//                        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
//                    }
//                } else {
//                    response.sendRedirect("login.jsp");
//                }
//
//        }catch (Exception e) {
//                e.printStackTrace();
//            }
//    }

}
