/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.CommonDao;
import dao.ProductDao;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Gender;
import entity.Size;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ProductVM;

/**
 *
 * @author LENOVO
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        String page = request.getParameter("page") == null ? "default" : request.getParameter("page");
        switch (page) {
            case "":
                break;
            default:
                CommonDao dao = new CommonDao();
                ProductDao productDao = new ProductDao();
                int count = dao.getTotalProduct();
                int endPage = count / 8;
                if (count % 8 != 0) {
                    endPage++;
                }
                List<ProductVM> listProduct = dao.getListProductPaging(index);
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
                request.setAttribute("endPage", endPage);            

                request.setAttribute("listProduct", listProduct);
                request.setAttribute("pageSelected", indexPage);
                url = "views/common/homepage.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
