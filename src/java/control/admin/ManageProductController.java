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
import entity.Image;
import entity.Pagination;
import entity.Product;
import entity.Product_Detail;
import entity.Product_Form;
import entity.Size;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ManageProductController", urlPatterns = {"/admin/manageproduct"})
@MultipartConfig
public class ManageProductController extends HttpServlet {

    AdminDao adminDAO = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        adminDAO = new AdminDao();
        Pagination pagination = new Pagination();
        Product_Form productForm;
        HttpSession session = request.getSession();
        List<Brand> listB = adminDAO.findAllBrand();;
        List<Category> listCate = adminDAO.findAllCategory();
        List<Color> listC = adminDAO.findAllColor();
        List<Size> listS = adminDAO.findAllSize();
        List<Gender> listG = adminDAO.findAllGender();
        List<Brand> brandCounts = adminDAO.countProductsByBrand();
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
                productForm = adminDAO.findProductByID(productID, colorID, categoryID, sizeID, brandID, genderID);
                int characterStock = adminDAO.findStockByCharacter(productID, colorID, categoryID, sizeID, brandID, genderID);
                Color productColors = adminDAO.getColorsForProduct(productID, colorID, categoryID, sizeID, brandID, genderID);
                Size productSize = adminDAO.getSizeForProduct(productID, colorID, categoryID, sizeID, brandID, genderID);
                Brand productBrand = adminDAO.getBrandForProduct(productID, colorID, categoryID, sizeID, brandID, genderID);
                Category productCategory = adminDAO.getCategoryForProduct(productID, colorID, categoryID, sizeID, brandID, genderID);
                Gender productGender = adminDAO.getGenderForProduct(productID, colorID, categoryID, sizeID, brandID, genderID);
                List<Image> listImages = adminDAO.getImagesByProductID(productID);
                session.setAttribute("characterStock", characterStock);
                session.setAttribute("productForm", productForm);
                session.setAttribute("productColors", productColors);
                session.setAttribute("productSize", productSize);
                session.setAttribute("productBrand", productBrand);
                session.setAttribute("productCategory", productCategory);
                session.setAttribute("productGender", productGender);
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
                request.getRequestDispatcher("/views/admin/manageProduct.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        adminDAO = new AdminDao();
        Pagination pagination = new Pagination();
        HttpSession session = request.getSession();
        List<Brand> listB = adminDAO.findAllBrand();;
        List<Category> listCate = adminDAO.findAllCategory();
        List<Color> listC = adminDAO.findAllColor();
        List<Size> listS = adminDAO.findAllSize();
        List<Gender> listG = adminDAO.findAllGender();
        List<Brand> brandCounts = adminDAO.countProductsByBrand();
        List<Product_Form> listPf;
        listPf = pagination(request, pagination);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "";
        switch (action) {
            case "add-product":
                addProduct(request, response);
                session.setAttribute("listB", listB);
                session.setAttribute("listCate", listCate);
                session.setAttribute("listC", listC);
                session.setAttribute("listS", listS);
                session.setAttribute("listG", listG);
                session.setAttribute("listPf", listPf);
                request.setAttribute("pagination", pagination);
                request.setAttribute("brandCounts", brandCounts);
                url = "/views/admin/manageProduct.jsp";
                break;
            case "delete-product":
                deleteProduct(request, response);
                session.setAttribute("listB", listB);
                session.setAttribute("listCate", listCate);
                session.setAttribute("listC", listC);
                session.setAttribute("listS", listS);
                session.setAttribute("listG", listG);
                session.setAttribute("listPf", listPf);
                request.setAttribute("pagination", pagination);
                request.setAttribute("brandCounts", brandCounts);
                url = "/views/admin/manageProduct.jsp";
                break;
            default:
                throw new AssertionError();
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

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
        String action = request.getParameter("action") == null ? "defaultFindAll" : request.getParameter("action");
        switch (action) {
            case "search-products":
                sorted = request.getParameter("sort");
                String keyword = request.getParameter("keyword");
                totalProducts = adminDAO.findTotalProducts(keyword);
                listPf = adminDAO.findPageByKeyword(page, sorted, keyword);
                pagination.setUrlPattern("manageproduct?action=search-products" + "&" + "sort=" + sorted + "&" + "keyword=" + keyword + "&");
                break;
            case "filter-products":
                sorted = request.getParameter("sort");
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
                listPf = adminDAO.findByPage(page, sorted, brandID, cateID, priceRange, minPrice, maxPrice, colorID, sizeID, genderID);
                pagination.setUrlPattern("manageproduct?action=filter-products" + "&" + "sort=" + sorted + "&" + "price-range=" + priceRange + "&" + "category-filter=" + cateID + "&" + "brand-filter=" + brandID + "&" + "colors-filter" + colorID + "&" + "size-filter" + sizeID + "&" + "gender-filter" + genderID + "&");
                break;
            default:
                totalProducts = adminDAO.findTotalProducts();
                listPf = adminDAO.findByPage(page);
                pagination.setUrlPattern("manageproduct?");
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
