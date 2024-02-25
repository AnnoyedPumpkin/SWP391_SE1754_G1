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
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Gender;
import entity.Size;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.ProductVM;

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
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
            case "":
                url = "home";
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "add1tocart":
                // Chuyển hướng sang trang hiển thị thông tin cá nhân
//                    addProductToCart(request, response, accountDetail);   
                addProductToCart(request, response);
                break;
            case "filter":
                filter(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void addProductToCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "";
            HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
            if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
                // Lấy thông tin tài khoản từ session
                Account account = (Account) session.getAttribute(Constant.SESSION_ACCOUNT);
                // Gọi phương thức trong DAO để lấy chi tiết tài khoản dựa trên accountId
                Account_Detail accountDetail = commonDAO.getAccountDetailByAccountId(account.getId());
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
            } else {
                url = "authen?action=login";
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filter(HttpServletRequest request, HttpServletResponse response) {
        try {

            String url = "";
            int endPage = 0;
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            ProductDao productDao = new ProductDao();

            String colorId = request.getParameter("colorId");
            String categoryId = request.getParameter("categoryId");
            String brandId = request.getParameter("brandId");
            String genderId = request.getParameter("genderId");
            String sizeId = request.getParameter("sizeId");
            
            //handle for page product list filter
            if (colorId == null && categoryId == null && brandId == null && genderId == null && sizeId == null) {
                colorId = "";
                categoryId = "";
                brandId = "";
                genderId = "";
                sizeId = "";
            }
            
            List<Gender> listGender = productDao.getGender();
            List<Category> listCategory = productDao.getCategory();
            List<Color> listColor = productDao.getColor();
            List<Brand> listBrand = productDao.getBrand();
            List<Size> listSize = productDao.getSize();
            
            request.setAttribute("gender", listGender);
            request.setAttribute("category", listCategory);
            request.setAttribute("color", listColor);
            request.setAttribute("brand", listBrand);
            request.setAttribute("size", listSize);
            
            
            List<ProductVM> listProductFitlerTotal = productDao.getListProductFilterTotal(colorId, categoryId, brandId, sizeId, genderId);
            if (listProductFitlerTotal.size() > 0) {
                int count = listProductFitlerTotal.size();
                System.out.println("Count " + count);
                endPage = count / 8;
                if (count % 8 != 0) {
                    endPage++;
                }
            } else {
                request.setAttribute("msg", "No product found");
            }
            List<ProductVM> listProduct = productDao.getListProductPaging(index, colorId, categoryId, brandId, sizeId, genderId);
            request.setAttribute("listProduct", listProduct);
//            request.setAttribute("pageSelected", indexPage);
            url = "views/common/filterProduct.jsp";
            request.setAttribute("colorId", colorId);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("brandId", brandId);
            request.setAttribute("genderId", genderId);
            request.setAttribute("sizeId", sizeId);
            request.setAttribute("endPage", endPage);
            request.setAttribute("pageSelected", indexPage);
            request.getRequestDispatcher(url).forward(request, response);

//            System.out.println("color: " + colorId + " Cate: " + categoryId + " Brand: " + brandId + " Gender: " + genderId + " size: " + sizeId);
        } catch (Exception e) {
        }
    }

}
