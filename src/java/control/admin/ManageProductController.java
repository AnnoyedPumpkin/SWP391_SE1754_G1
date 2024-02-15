/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.admin;

import constant.Constant;
import dao.AdminDao;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Gender;
import entity.Pagination;
import entity.Product;
import entity.Size;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ManageProductController", urlPatterns = {"/admin/manageproduct"})
public class ManageProductController extends HttpServlet {

    AdminDao adminDAO = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        adminDAO = new AdminDao();
        HttpSession session = request.getSession();
        List<Brand> listB = adminDAO.findAllBrand();;
        List<Category> listCate = adminDAO.findAllCategory();
        List<Color> listC = adminDAO.findAllColor();
        List<Size> listS = adminDAO.findAllSize();
        List<Gender> listG = adminDAO.findAllGender();
        List<Product> listP;
        String url = "";
        Pagination pagination = new Pagination();

        List<Brand> brandCounts = adminDAO.countProductsByBrand();
        listP = pagination(request, pagination);
        session.setAttribute("listB", listB);
        session.setAttribute("listCate", listCate);
        session.setAttribute("listC", listC);
        session.setAttribute("listS", listS);
        session.setAttribute("listG", listG);
        session.setAttribute("listP", listP);
        request.setAttribute("pagination", pagination);
        request.setAttribute("brandCounts", brandCounts);
        request.getRequestDispatcher("/views/admin/manageProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private List<Product> pagination(HttpServletRequest request, Pagination pagination) {
        String pageRaw = request.getParameter("pagination");
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalProducts = 0;
        List<Product> listP = null;
        String action = request.getParameter("action") == null ? "defaultFindAll" : request.getParameter("action");
        switch (action) {
            case "search-products":
                break;
            case "filter-products":
                String sorted = request.getParameter("sort");
                String brandID = request.getParameter("brand-filter");
                String cateID = request.getParameter("category-filter");
                String colorID = request.getParameter("colors-filter");
                String sizeID = request.getParameter("size-filter");
                String genderID = request.getParameter("gender-filter");
                String priceRange = request.getParameter("price-range");
                String minPrice = "",
                 maxPrice = "";
                if (priceRange != null && !priceRange.isEmpty()) {
                    String[] range = priceRange.split("-");
                    minPrice = range[0];
                    maxPrice = range[1];
                }
                totalProducts = adminDAO.findTotalProducts(brandID, cateID, priceRange, minPrice, maxPrice, colorID, sizeID, genderID);
                if (sorted == null || sorted.isEmpty()) {
                    listP = adminDAO.findByPage(page, sorted, brandID, cateID, priceRange, minPrice, maxPrice, colorID, sizeID, genderID);
                    pagination.setUrlPattern("manageproduct?action=filter-products" + "&" + "sort=" + sorted + "&" + "price-range=" + priceRange + "&" + "category-filter=" + cateID + "&" + "brand-filter=" + brandID + "&" + "colors-filter" + colorID + "&" + "size-filter" + sizeID + "&" + "gender-filter" + genderID + "&");
                } else if (sorted.equals("asc")) {
                    listP = adminDAO.findByPage(page, sorted, brandID, cateID, priceRange, minPrice, maxPrice, colorID, sizeID, genderID);
                    pagination.setUrlPattern("manageproduct?action=filter-products" + "&" + "sort=" + sorted + "&" + "price-range=" + priceRange + "&" + "category-filter=" + cateID + "&" + "brand-filter=" + brandID + "&" + "colors-filter" + colorID + "&" + "size-filter" + sizeID + "&" + "gender-filter" + genderID + "&");
                } else if (sorted.equals("desc")) {
                    listP = adminDAO.findByPage(page, sorted, brandID, cateID, priceRange, minPrice, maxPrice, colorID, sizeID, genderID);
                    pagination.setUrlPattern("manageproduct?action=filter-products" + "&" + "sort=" + sorted + "&" + "price-range=" + priceRange + "&" + "category-filter=" + cateID + "&" + "brand-filter=" + brandID + "&" + "colors-filter" + colorID + "&" + "size-filter" + sizeID + "&" + "gender-filter" + genderID + "&");
                }
                break;
            default:
                totalProducts = adminDAO.findTotalProducts();
                listP = adminDAO.findByPage(page);
                pagination.setUrlPattern("manageproduct?");
        }
        int totalPage = (totalProducts % Constant.RECORD_PER_PAGE) == 0
                ? (totalProducts / Constant.RECORD_PER_PAGE)
                : (totalProducts / Constant.RECORD_PER_PAGE) + 1;
        pagination.setPage(page);
        pagination.setTotalPage(totalPage);
        pagination.setTotalRecord(totalProducts);
        return listP;
    }

}
