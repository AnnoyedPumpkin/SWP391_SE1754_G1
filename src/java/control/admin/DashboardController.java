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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "DashboardController", urlPatterns = {"/admin/dashboard"})
public class DashboardController extends HttpServlet {

    AdminDao adminDAO = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        adminDAO = new AdminDao();
        HttpSession session = request.getSession();
        List<Color> listC;
        List<Brand> listB;
        List<Gender> listG;
        List<Category> listCate;
        List<Size> listS;
        String page = request.getParameter("page") == null ? "" : request.getParameter("page");
        String url = "";
        switch (page) {
            case "view-details":
                listC = adminDAO.findAllColor();
                listB = adminDAO.findAllBrand();
                listCate = adminDAO.findAllCategory();
                listG = adminDAO.findAllGender();
                listS = adminDAO.findAllSize();
                session.setAttribute("listC", listC);
                session.setAttribute("listB", listB);
                session.setAttribute("listCate", listCate);
                session.setAttribute("listG", listG);
                session.setAttribute("listS", listS);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "view-products":
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
        List<Gender> listG;
        List<Size> listS;
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
            case "add-gender":
                addGender(request, response);
                listG = adminDAO.findAllGender();
                session.setAttribute("listG", listG);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "delete-gender":
                deleteGender(request, response);
                listG = adminDAO.findAllGender();
                session.setAttribute("listG", listG);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "edit-gender":
                editGender(request, response);
                listG = adminDAO.findAllGender();
                session.setAttribute("listG", listG);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "add-size":
                addSize(request, response);
                listS = adminDAO.findAllSize();
                session.setAttribute("listS", listS);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "delete-size":
                deleteSize(request, response);
                listS = adminDAO.findAllSize();
                session.setAttribute("listS", listS);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "edit-size":
                editSize(request, response);
                listS = adminDAO.findAllSize();
                session.setAttribute("listS", listS);
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
        boolean colorExist = adminDAO.findColorExistByIdAndColor(id, color);

        if (adminDAO.findColorByOldColorAndId(id, oldColorName)) {
            adminDAO.editColor(c, oldColorName);
            request.setAttribute("msgcoe", "Edit color successfully !!");
        } else {
            if (!colorExist) {
                adminDAO.editColor(c, oldColorName);
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
        boolean cateExist = adminDAO.findCateExistByIdAndCate(id, cate);
        if (adminDAO.findCateByOldCateAndId(id, oldCategoryName)) {
            adminDAO.editCate(c, oldCategoryName);
            request.setAttribute("msgce", "Edit category successfully !!");
        } else {
            if (!cateExist) {
                adminDAO.editCate(c, oldCategoryName);
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
        boolean brandExist = adminDAO.findBrandExistByBrandAndId(id, brand);
        if (adminDAO.findBrandByOldBrandAndId(id, oldBrandName)) {
            adminDAO.editBrand(b, oldBrandName);
            request.setAttribute("msgge", "Edit gender successfully !!");
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

    private void addGender(HttpServletRequest request, HttpServletResponse response) {
        String gender = request.getParameter("gender");

        boolean genderExist = adminDAO.findGenderExist(gender);

        if (!genderExist) {
            adminDAO.addGender(gender);
            request.setAttribute("msgga", "Create new gender success");
        } else {
            request.setAttribute("errorga", "This gender is exist already, please create other gender!");
        }
    }

    private void deleteGender(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("genderId"));
        adminDAO.deleteGenderById(id);
        request.setAttribute("msggd", "Delete gender successfully!");
    }

    private void editGender(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("genderIdToUpdate"));
        String gender = request.getParameter("genderToUpdate");
        String oldGenderName = request.getParameter("oldGenderName");
        Gender g = Gender.builder()
                .id(id)
                .gender(gender)
                .build();
        boolean genderExist = adminDAO.findGenderExistByIdAndGender(id, gender);
        if (adminDAO.findGenderByOldGenderAndId(id, oldGenderName)) {
            adminDAO.editGender(g, oldGenderName);
            request.setAttribute("msgge", "Edit gender successfully !!");
        } else {
            if (!genderExist) {
                adminDAO.editGender(g, oldGenderName);
                request.setAttribute("msgge", "Edit gender successfully !!");
            } else {
                request.setAttribute("errorge", "This gender exist already, please edit different from remaining gender !!");
            }
        }
    }

    private void addSize(HttpServletRequest request, HttpServletResponse response) {
        String size = request.getParameter("size");

        boolean sizeExist = adminDAO.findSizeExist(size);

        if (!sizeExist) {
            adminDAO.addSize(size);
            request.setAttribute("msgsa", "Create new size success");
        } else {
            request.setAttribute("errorsa", "This size is exist already, please create other gender!");
        }
    }

    private void editSize(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("sizeIdToUpdate"));
        String size = request.getParameter("sizeToUpdate");
        String oldSizeName = request.getParameter("oldSizeName");
        Size s = Size.builder()
                .id(id)
                .size(size)
                .build();
        boolean genderExist = adminDAO.findSizeExistByIdAndSize(id, size);
        if (adminDAO.findSizeByOldSizeAndId(id, oldSizeName)) {
            adminDAO.editSize(s, oldSizeName);
            request.setAttribute("msgse", "Edit size successfully !!");
        } else {
            if (!genderExist) {
                adminDAO.editSize(s, oldSizeName);
                request.setAttribute("msgse", "Edit size successfully !!");
            } else {
                request.setAttribute("errorse", "This size exist already, please edit different from remaining gender !!");
            }
        }
    }

    private void deleteSize(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("sizeId"));
        adminDAO.deleteSizeById(id);
        request.setAttribute("msgsd", "Delete size successfully!");
    }

   
}
