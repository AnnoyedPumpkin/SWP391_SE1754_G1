/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.CommonDao;
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
                int count = dao.getTotalProduct();
                int endPage = count / 8;
                if (count % 8 != 0) {
                    endPage++;
                }
                List<ProductVM> listProduct = dao.getListProductPaging(index);
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
