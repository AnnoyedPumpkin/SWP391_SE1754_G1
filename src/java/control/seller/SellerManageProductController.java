/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.seller;

import constant.Constant;
import dao.SellerDao;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Gender;
import entity.Image;
import entity.Pagination;
import entity.Product_Form;
import entity.Size;
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
 * @author Win 10
 */
public class SellerManageProductController extends HttpServlet {

    SellerDao sellerDao = new SellerDao();
    
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
        sellerDao = new SellerDao();
        Pagination pagination = new Pagination();
        Product_Form productForm;
        HttpSession session = request.getSession();
        List<Brand> listB = sellerDao.findAllBrand();
        List<Category> listCate = sellerDao.findAllCategory();
        List<Color> listC = sellerDao.findAllColor();
        List<Size> listS = sellerDao.findAllSize();
        List<Gender> listG = sellerDao.findAllGender();
        List<Brand> brandCounts = sellerDao.countProductsByBrand();
        List<Product_Form> listPf;
        listPf = pagination(request, pagination);
        String page = request.getParameter("page") == null ? "" : request.getParameter("page");

        switch (page) {
            case "view-product-details":
                String productID = request.getParameter("productID");
                String colorID = request.getParameter("colorID");
                String categoryID = request.getParameter("categoryID");
                String sizeID = request.getParameter("sizeID");
                String brandID = request.getParameter("brandID");
                String genderID = request.getParameter("genderID");
                productForm = sellerDao.findProductByID(productID, colorID, categoryID, sizeID, brandID, genderID);
                List<Image> listImages = sellerDao.getImagesByProductID(productID);
                session.setAttribute("productForm", productForm);
                session.setAttribute("listImages", listImages);
                request.getRequestDispatcher("/views/admin/productDetails.jsp").forward(request, response);
                break;
            default:
                session.setAttribute("listB", listB);
                session.setAttribute("listCate", listCate);
                session.setAttribute("listC", listC);
                session.setAttribute("listS", listS);
                session.setAttribute("listG", listG);
                session.setAttribute("listPf", listPf);
                request.setAttribute("pagination", pagination);
                request.setAttribute("brandCounts", brandCounts);
                request.getRequestDispatcher("/views/admin/manageSellerProduct.jsp").forward(request, response);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Product_Form> pagination(HttpServletRequest request, Pagination pagination) {
        String pageRaw = request.getParameter("pagination");
        int page;
        String sorted;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalProducts = 0;
        List<Product_Form> listPf = null;
        String action = request.getParameter("action") == null ? "default" : request.getParameter("action");
        switch (action) {
            case "search-products":
                sorted = request.getParameter("sort");
                String keyword = request.getParameter("keyword");
                totalProducts = sellerDao.findTotalProducts(keyword);
                listPf = sellerDao.findPageByKeyword(page, sorted, keyword);
                pagination.setUrlPattern("manageProduct?action=search-products" + "&" + "sort=" + sorted + "&" + "keyword=" + keyword + "&");
                break;
            case "filter-products":
                sorted = request.getParameter("sort");
                String[] brandID = request.getParameterValues("brand-filter");
                String[] cateID = request.getParameterValues("category-filter");
                String[] colorID = request.getParameterValues("colors-filter");
                String[] sizeID = request.getParameterValues("size-filter");
                String[] genderID = request.getParameterValues("gender-filter");

                String priceRange = request.getParameter("price-range");
                String minPrice = "",
                 maxPrice = "";
                if (priceRange != null && !priceRange.isEmpty()) {
                    String[] range = priceRange.split("-");
                    minPrice = range[0];
                    maxPrice = range[1];
                }
                
                totalProducts = sellerDao.findTotalProducts(brandID, cateID, priceRange, minPrice, maxPrice, colorID, sizeID, genderID);
                listPf = sellerDao.findByPage(page, sorted, brandID, cateID, priceRange, minPrice, maxPrice, colorID, sizeID, genderID);

                StringBuilder url = new StringBuilder("manageProduct?action=filter-products");
                if (sorted != null && !sorted.isEmpty()) {
                    url.append("&sort=").append(sorted);
                }
                if (priceRange != null && !priceRange.isEmpty()) {
                    url.append("&price-range=").append(priceRange);
                }
                if (cateID != null && cateID.length > 0) {
                    for (String category : cateID) {
                        url.append("&category-filter=").append(category);
                    }
                }
                if (brandID != null && brandID.length > 0) {
                    for (String brand : brandID) {
                        url.append("&brand-filter=").append(brand);
                    }
                }
                if (colorID != null && colorID.length > 0) {
                    for (String color : colorID) {
                        url.append("&colors-filter=").append(color);
                    }
                }
                if (sizeID != null && sizeID.length > 0) {
                    for (String size : sizeID) {
                        url.append("&size-filter=").append(size);
                    }
                }
                if (genderID != null && genderID.length > 0) {
                    for (String gender : genderID) {
                        url.append("&gender-filter=").append(gender);
                    }
                }
                url.append("&");
                pagination.setUrlPattern(url.toString());
                break;
            default:
                totalProducts = sellerDao.findTotalProducts();
                listPf = sellerDao.findByPage(page);
                pagination.setUrlPattern("manageProduct?");
        }
        int totalPage = (totalProducts % Constant.RECORD_PER_PAGE) == 0
                ? (totalProducts / Constant.RECORD_PER_PAGE)
                : (totalProducts / Constant.RECORD_PER_PAGE) + 1;
        pagination.setPage(page);
        pagination.setTotalPage(totalPage);
        pagination.setTotalRecord(totalProducts);
        return listPf;
    }
    
}
