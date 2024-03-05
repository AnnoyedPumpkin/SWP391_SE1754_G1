/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.seller;

import constant.Constant;
import dao.SellerDao;
import entity.Account;
import entity.Account_Detail;
import entity.Invoice;
import entity.Invoice_Form;
import entity.Pagination;
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
 * @author admin
 */
public class SellerManageInvoiceController extends HttpServlet {

    SellerDao sellerDao = new SellerDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sellerDao = new SellerDao();
        Pagination pagination = new Pagination();
        Invoice_Form invoiceForm;
        HttpSession session = request.getSession();
        List<Invoice_Form> listIf;
        listIf = pagination(request, pagination);
        String page = request.getParameter("page") == null ? "" : request.getParameter("page");
        switch (page) {
            case "view-invoice-details":
                int invoiceID = Integer.parseInt(request.getParameter("invoiceID"));
                List<Invoice_Form> listIfd = sellerDao.getListInvoiceDetailByID(invoiceID);
                invoiceForm = sellerDao.getInvoiceDetailByID(invoiceID);
                request.setAttribute("invoiceForm", invoiceForm);
                request.setAttribute("listIfd", listIfd);
                request.getRequestDispatcher("/views/admin/page_Invoice.jsp").forward(request, response);
                break;
            default:
                session.setAttribute("listIf", listIf);
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("/views/admin/invoiceList.jsp").forward(request, response);
        }
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

    private List<Invoice_Form> pagination(HttpServletRequest request, Pagination pagination) {
        String pageRaw = request.getParameter("pagination");
        int page;
        String sorted;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalInvoice = 0;
        List<Invoice_Form> listIf = null;
        String action = request.getParameter("action") == null ? "default" : request.getParameter("action");
        switch (action) {
            case "search-Invoice":
                break;
            default:
                listIf = sellerDao.findTotalInvoice();
                totalInvoice = sellerDao.countTotalInvoice();
                pagination.setUrlPattern("manageinvoice?");
        }
        int totalPage = (totalInvoice % Constant.RECORD_PER_PAGE) == 0
                ? (totalInvoice / Constant.RECORD_PER_PAGE)
                : (totalInvoice / Constant.RECORD_PER_PAGE) + 1;
        pagination.setPage(page);
        pagination.setTotalPage(totalPage);
        pagination.setTotalRecord(totalInvoice);
        return listIf;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
