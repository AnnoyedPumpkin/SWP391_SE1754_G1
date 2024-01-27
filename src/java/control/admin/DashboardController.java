/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.admin;

import dao.AdminDao;
import entity.Brand;
import entity.Category;
import entity.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/admin/dashboard"})
public class DashboardController extends HttpServlet {

    AdminDao adminDAO = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Color> listC;
        List<Brand> listB;
        List<Category> listCate;
        String page = request.getParameter("page") == null ? "" : request.getParameter("page");
        String url = "";
        switch (page) {
            case "dashboard":
                url = "../views/admin/dashboard.jsp";
                break;
            case "view-details":
                listC = adminDAO.findAllColor();
                listB = adminDAO.findAllBrand();
                listCate = adminDAO.findAllCategory();
                session.setAttribute("listC", listC);
                session.setAttribute("listB", listB);
                session.setAttribute("listCate", listCate);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            default:
                url = "../views/admin/dashboard.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Color> listC;
        List<Brand> listB;
        List<Category> listCate;
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        String url = "";
        switch (action) {
            case "add-color":
                addColor(request, response);
                listC = adminDAO.findAllColor();
                session.setAttribute("listC", listC);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "delete-color":
                deleteColor(request, response);
                listC = adminDAO.findAllColor();
                session.setAttribute("listC", listC);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "edit-color":
                editColor(request, response);
                listC = adminDAO.findAllColor();
                session.setAttribute("listC", listC);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "add-brand":
                addBrand(request, response);
                listB = adminDAO.findAllBrand();
                session.setAttribute("listB", listB);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "delete-brand":
                deleteBrand(request, response);
                listB = adminDAO.findAllBrand();
                session.setAttribute("listB", listB);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "edit-brand":
                editBrand(request, response);
                listB = adminDAO.findAllBrand();
                session.setAttribute("listB", listB);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "add-category":
                addCategory(request, response);
                listCate = adminDAO.findAllCategory();
                session.setAttribute("listCate", listCate);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "delete-category":
                deleteCategory(request, response);
                listCate = adminDAO.findAllCategory();
                session.setAttribute("listCate", listCate);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "edit-category":
                editCategory(request, response);
                listCate = adminDAO.findAllCategory();
                session.setAttribute("listCate", listCate);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            default:
                throw new AssertionError();
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void addColor(HttpServletRequest request, HttpServletResponse response) {
        adminDAO = new AdminDao();
        String color = request.getParameter("color");

        boolean colorExist = adminDAO.findColorExist(color);

        if (!colorExist) {
            adminDAO.addColor(color);
            request.setAttribute("msgcoa", "Create new color success");
        } else {
            request.setAttribute("errorcoa", "This color is exist already, please create other color!");
        }
    }

    private void deleteColor(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("colorId"));
        adminDAO.deleteColorById(id);
        request.setAttribute("msgcod", "Delete color successfully!");
    }

    private void editColor(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("colorIdToUpdate"));
        String color = request.getParameter("colorToUpdate");
        String oldColorName = request.getParameter("oldNameColor");
        Color c = Color.builder()
                .id(id)
                .color(color)
                .build();
        boolean colorExist = adminDAO.findColorExist(color);
        
        if (adminDAO.findColorByOldColorName(oldColorName)) {
            request.setAttribute("errorce", "This color edit is duplicate with the newest of this !!");
        } else {
            if (!colorExist) {
                adminDAO.editColor(c);
                request.setAttribute("msgcoe", "Edit color successfully !!");
            } else {
                request.setAttribute("errorcoe", "This color exist already, please edit different from remaining color !!");
            }
        }

    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) {
        String category = request.getParameter("cate");

        boolean cateExist = adminDAO.findCateExist(category);

        if (!cateExist) {
            adminDAO.addCategory(category);
            request.setAttribute("msgca", "Create new category success");
        } else {
            request.setAttribute("errorca", "This category is exist already, please create other category!");
        }
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("categoryIdToUpdate"));
        String cate = request.getParameter("categoryToUpdate");
        String oldCategoryName = request.getParameter("oldNameCategory");
        Category c = Category.builder()
                .id(id)
                .category(cate)
                .build();
        boolean cateExist = adminDAO.findCateExist(cate);
        if (adminDAO.findCategoryByOldCategoryName(oldCategoryName)) {
            request.setAttribute("errorce", "This category edit is duplicate with the newest of this !!");
        } else {
            if (!cateExist) {
                adminDAO.editCate(c);
                request.setAttribute("msgce", "Edit category successfully !!");
            } else {
                request.setAttribute("errorce", "This category exist already, please edit different from remaining category !!");
            }
        }
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("categoryId"));
        adminDAO.deleteCateById(id);
        request.setAttribute("msgcd", "Delete category successfully!");
    }

    private void addBrand(HttpServletRequest request, HttpServletResponse response) {
        String brand = request.getParameter("brand");

        boolean cateExist = adminDAO.findBrandExist(brand);

        if (!cateExist) {
            adminDAO.addBrand(brand);
            request.setAttribute("msgba", "Create new brand success");
        } else {
            request.setAttribute("errorba", "This brand is exist already, please create other brand!");
        }
    }

    private void editBrand(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("brandIdToUpdate"));
        String brand = request.getParameter("brandToUpdate");
        String oldBrandName = request.getParameter("oldBrandName");
        Brand b = Brand.builder()
                .id(id)
                .brand(brand)
                .build();
        boolean brandExist = adminDAO.findBrandExist(brand);
        if (adminDAO.findBrandByOldBrandName(oldBrandName)) {
            request.setAttribute("errorbe", "This brand name is duplicate with the newest of this !!");
        } else {
            if (!brandExist) {
                adminDAO.editBrand(b, oldBrandName);
                request.setAttribute("msgbe", "Edit brand successfully !!");
            } else {
                request.setAttribute("errorbe", "This brand exist already, please edit different from remaining brand !!");
            }
        }
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("brandId"));
        adminDAO.deleteBrandById(id);
        request.setAttribute("msgbd", "Delete brand successfully!");
    }
}
