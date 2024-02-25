/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import constant.Constant;
import dao.CommonDao;
import dao.OrderDao;
import dao.ProductDao;
import entity.Account;
import entity.Account_Detail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.InvoiceDetailVM;
import model.InvoiceVM;

/**
 *
 * @author Admin
 */
public class OrderController extends HttpServlet {

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
                    case "invoiceHistory":
                        getListInvoiceHistory(request, response, accountDetail);
                        break;
                    case "invoiceDetail":
                        getListInvoiceDetailBuyOrderId(request, response);
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

    private void getListInvoiceHistory(HttpServletRequest request, HttpServletResponse response, Account_Detail accountDetail) {
        try {
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            String url = "";
            OrderDao orderDao = new OrderDao();
            int count = orderDao.getUserInvoiceTotal(accountDetail.getAccount_id());
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            List<InvoiceVM> listInvoices = orderDao.getUserInvoiceHistory(accountDetail.getAccount_id(), index);
            if (!listInvoices.isEmpty() || listInvoices.size() > 0) {
                request.setAttribute("list", listInvoices);
                request.setAttribute("endPage", endPage);
                request.setAttribute("pageSelected", indexPage);
                url = "views/common/orderHistory.jsp";
            } else {
                System.out.println("Error");
                request.setAttribute("msg", "Ban chua co don hang nao tren he thong");
                url = "profile";
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getListInvoiceDetailBuyOrderId(HttpServletRequest request, HttpServletResponse response) {
        try {
            String indexPage = request.getParameter("index");
            String invoiceIdS = request.getParameter("invoiceId");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int invoiceId = Integer.parseInt(invoiceIdS);
            String url = "";
            
              OrderDao orderDao = new OrderDao();
            int count = orderDao.getUserInvoiceDetailTotal(invoiceId);
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            List<InvoiceDetailVM> listInvoicesDetail = orderDao.getListInvoiceDetailById(invoiceId, index);
            if (!listInvoicesDetail.isEmpty() || listInvoicesDetail.size() > 0) {
                request.setAttribute("list", listInvoicesDetail);
                request.setAttribute("endPage", endPage);
                request.setAttribute("pageSelected", indexPage);
                request.setAttribute("invoiceId", invoiceId);
                url = "views/common/orderDetail.jsp";
            } else {
                url = "profile";
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
