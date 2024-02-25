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
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.ProductVM;

/**
 *
 * @author LENOVO
 */
public class HomeController extends HttpServlet {
    CommonDao commonDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        commonDAO = new CommonDao();
        HttpSession session = request.getSession(false); // Sử dụng tham số false để không tạo session mới nếu không tồn tại
        String action = request.getParameter("action") == null ? "profile" : request.getParameter("action");
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
            }
        }
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
