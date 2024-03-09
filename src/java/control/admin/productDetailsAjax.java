/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.admin;

import constant.Constant;
import dao.AdminDao;
import entity.Color;
import entity.Gender;
import entity.Pagination;
import entity.Product_Form;
import entity.Size;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "productDetailsAjax", urlPatterns = {"/productDetailsAjax"})
public class productDetailsAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Pagination pagination = new Pagination();
        AdminDao adminDAO = new AdminDao();
        int totalProductDetails = 0;
        String pageRaw = request.getParameter("pagination");
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        String productID = request.getParameter("productID");
        String colorId = request.getParameter("colorID");
        String sizeId = request.getParameter("sizeID");
        String genderID = request.getParameter("genderID");

        if (colorId != null && !colorId.isEmpty()) {
            List<Size> listSa = adminDAO.findSizeByColorId(productID, colorId);
            List<Gender> listGa = adminDAO.findGenderByColorId(productID, colorId);      
            request.setAttribute("listSa", listSa);
            request.setAttribute("listGa", listGa);
            request.setAttribute("colorIdA", colorId);
        }
        if (sizeId != null && !sizeId.isEmpty()) {
            List<Color> listC = adminDAO.findColorBySize(productID, sizeId);
            List<Gender> listG = adminDAO.findGenderBySize(productID, sizeId);
            pagination.setUrlPattern("manageproduct?page=view-product-details&productID=" + productID + "&" + "colorId=" + colorId + "&" + "sizeId=" + sizeId + "&" + "genderID=" + genderID + "&");
            int totalPage = (totalProductDetails % Constant.RECORD_PER_PAGE) == 0
                    ? (totalProductDetails / Constant.RECORD_PER_PAGE)
                    : (totalProductDetails / Constant.RECORD_PER_PAGE) + 1;
            pagination.setPage(page);
            pagination.setTotalPage(totalPage);
            pagination.setTotalRecord(totalProductDetails);
            request.setAttribute("pagination", pagination);
            request.setAttribute("listCa", listC);
            request.setAttribute("listGa", listG);
            request.setAttribute("sizeIdA", sizeId);
        }
        if (genderID != null && !genderID.isEmpty()) {
            List<Color> listC = adminDAO.findColorByGender(productID, genderID);
            List<Size> listS = adminDAO.findSizeByGender(productID, genderID);
            pagination.setUrlPattern("manageproduct?page=view-product-details&productID=" + productID + "&" + "colorId=" + colorId + "&" + "sizeId=" + sizeId + "&" + "genderID=" + genderID + "&");
            int totalPage = (totalProductDetails % Constant.RECORD_PER_PAGE) == 0
                    ? (totalProductDetails / Constant.RECORD_PER_PAGE)
                    : (totalProductDetails / Constant.RECORD_PER_PAGE) + 1;
            pagination.setPage(page);
            pagination.setTotalPage(totalPage);
            pagination.setTotalRecord(totalProductDetails);
            request.setAttribute("pagination", pagination);
            request.setAttribute("listCa", listC);
            request.setAttribute("listSa", listS);
            request.setAttribute("genderIdA", genderID);
        }
        if (colorId != null && !colorId.isEmpty() && sizeId != null && !sizeId.isEmpty()) {
            List<Gender> listG = adminDAO.findGenderByColorAndSize(productID, colorId, sizeId);
            pagination.setUrlPattern("manageproduct?page=view-product-details&productID=" + productID + "&" + "colorId=" + colorId + "&" + "sizeId=" + sizeId + "&" + "genderID=" + genderID + "&");
            int totalPage = (totalProductDetails % Constant.RECORD_PER_PAGE) == 0
                    ? (totalProductDetails / Constant.RECORD_PER_PAGE)
                    : (totalProductDetails / Constant.RECORD_PER_PAGE) + 1;
            pagination.setPage(page);
            pagination.setTotalPage(totalPage);
            pagination.setTotalRecord(totalProductDetails);
            request.setAttribute("pagination", pagination);
            request.setAttribute("listGa", listG);
            request.setAttribute("colorIdA", colorId);
            request.setAttribute("sizeIdA", sizeId);
        }
        if (colorId != null && !colorId.isEmpty() && genderID != null && !genderID.isEmpty()) {
            List<Size> listS = adminDAO.findSizeByColorAndGender(productID, colorId, genderID);
            pagination.setUrlPattern("manageproduct?page=view-product-details&productID=" + productID + "&" + "colorId=" + colorId + "&" + "sizeId=" + sizeId + "&" + "genderID=" + genderID + "&");
            int totalPage = (totalProductDetails % Constant.RECORD_PER_PAGE) == 0
                    ? (totalProductDetails / Constant.RECORD_PER_PAGE)
                    : (totalProductDetails / Constant.RECORD_PER_PAGE) + 1;
            pagination.setPage(page);
            pagination.setTotalPage(totalPage);
            pagination.setTotalRecord(totalProductDetails);
            request.setAttribute("pagination", pagination);
            request.setAttribute("listSa", listS);
            request.setAttribute("colorIdA", colorId);
            request.setAttribute("genderIdA", genderID);
        }
        if (genderID != null && !genderID.isEmpty() && sizeId != null && !sizeId.isEmpty()) {
            List<Color> listC = adminDAO.findColorByGenderAndSize(productID, genderID, sizeId);
            pagination.setUrlPattern("manageproduct?page=view-product-details&productID=" + productID + "&" + "colorId=" + colorId + "&" + "sizeId=" + sizeId + "&" + "genderID=" + genderID + "&");
            int totalPage = (totalProductDetails % Constant.RECORD_PER_PAGE) == 0
                    ? (totalProductDetails / Constant.RECORD_PER_PAGE)
                    : (totalProductDetails / Constant.RECORD_PER_PAGE) + 1;
            pagination.setPage(page);
            pagination.setTotalPage(totalPage);
            pagination.setTotalRecord(totalProductDetails);
            request.setAttribute("pagination", pagination);
            request.setAttribute("listCa", listC);
            request.setAttribute("genderIdA", genderID);
            request.setAttribute("sizeIdA", sizeId);
        }
        if (colorId != null && !colorId.isEmpty() && sizeId != null && !sizeId.isEmpty() && genderID != null && !genderID.isEmpty()) {
            int StockOfProductDetail = adminDAO.getStockProductDetailByAllField(productID, colorId, sizeId, genderID);
            pagination.setUrlPattern("manageproduct?page=view-product-details&productID=" + productID + "&" + "colorId=" + colorId + "&" + "sizeId=" + sizeId + "&" + "genderID=" + genderID + "&");
            int totalPage = (totalProductDetails % Constant.RECORD_PER_PAGE) == 0
                    ? (totalProductDetails / Constant.RECORD_PER_PAGE)
                    : (totalProductDetails / Constant.RECORD_PER_PAGE) + 1;
            pagination.setPage(page);
            pagination.setTotalPage(totalPage);
            pagination.setTotalRecord(totalProductDetails);
            request.setAttribute("pagination", pagination);
            request.setAttribute("StockOfProductDetail", StockOfProductDetail);
            request.setAttribute("colorIdA", colorId);
            request.setAttribute("sizeIdA", sizeId);
            request.setAttribute("genderIdA", genderID);
        }
        request.getRequestDispatcher("views/admin/productDetail.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
