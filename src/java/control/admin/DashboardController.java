/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.admin;

import constant.Constant;
import dao.AdminDao;
import entity.Account;
import entity.Account_Form;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Discount;
import entity.Gender;
import entity.Pagination;
import entity.Product;
import entity.Size;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        adminDAO = new AdminDao();
        HttpSession session = request.getSession();
        List<Color> listC;
        List<Brand> listB;
        List<Gender> listG;
        List<Category> listCate;
        List<Size> listS;
        List<Discount> listD;
        List<Account_Form> listAf;
        int countAccounts = adminDAO.countAllAccount();
        int countInvoices = adminDAO.countAllInvoice();
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
            case "manage-discount":
                listD = adminDAO.findAllDiscount();
                session.setAttribute("listD", listD);
                url = "../views/admin/manageDiscount.jsp";
                break;
            case "manageUser":
                listAf = adminDAO.findAllAccount();
                session.setAttribute("listAf", listAf);
                url = "../views/admin/manageUser.jsp";
                break;
            default:
                session.setAttribute("countAccount", countAccounts);
                session.setAttribute("countInvoice", countInvoices);
                url = "../views/admin/dashboard.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        List<Color> listC;
        List<Brand> listB;
        List<Category> listCate;
        List<Gender> listG;
        List<Size> listS;
        List<Discount> listD;
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
            case "update-color":
                updateColor(request, response);
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
            case "update-brand":
                updateBrand(request, response);
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
            case "update-category":
                updateCategory(request, response);
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
            case "update-gender":
                updateGender(request, response);
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
            case "update-size":
                updateSize(request, response);
                listS = adminDAO.findAllSize();
                session.setAttribute("listS", listS);
                url = "../views/admin/productCharacteristic.jsp";
                break;
            case "add-discount":
                addDiscount(request, response);
                listD = adminDAO.findAllDiscount();
                session.setAttribute("listD", listD);
                url = "../views/admin/manageDiscount.jsp";
                break;
            case "delete-discount":
                deleteDiscount(request, response);
                listD = adminDAO.findAllDiscount();
                session.setAttribute("listD", listD);
                url = "../views/admin/manageDiscount.jsp";
                break;
            case "update-discount":
                updateDiscount(request, response);
                listD = adminDAO.findAllDiscount();
                session.setAttribute("listD", listD);
                url = "../views/admin/manageDiscount.jsp";
                break;
            default:
                throw new AssertionError();
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void addColor(HttpServletRequest request, HttpServletResponse response) {
        adminDAO = new AdminDao();
        String color = request.getParameter("color");

        if (color == null || color.isEmpty() || !color.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorcoa", "Create color error!");
            return;
        }

        boolean colorExist = adminDAO.findColorExist(color);

        if (!colorExist) {
            adminDAO.addColor(color);
            request.setAttribute("msgcoa", "Create new color success");
        } else {
            request.setAttribute("errorcoa", "This color is exist already, please create other color!");
        }
    }

    private void deleteColor(HttpServletRequest request, HttpServletResponse response) {
        String idToDelete = request.getParameter("colorId");

        if (idToDelete == null || idToDelete.isEmpty()) {
            request.setAttribute("errorcod", "Delete color error!");
            return;
        }

        int id = Integer.parseInt(idToDelete);

        adminDAO.deleteColorIdInProductDetail(id);
        adminDAO.deleteColorById(id);
        request.setAttribute("msgcod", "Delete color successfully!");
    }

    private void updateColor(HttpServletRequest request, HttpServletResponse response) {
        String idToEdit = request.getParameter("colorIdToUpdate");
        String color = request.getParameter("colorToUpdate");
        String oldColorName = request.getParameter("oldNameColor");

        if (idToEdit == null || idToEdit.isEmpty() || color == null || color.isEmpty() || oldColorName == null || oldColorName.isEmpty() || !idToEdit.matches("^[0-9]+$") || !color.matches("^[A-Za-z\\s]+$") || !oldColorName.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorcoe", "Edit color error!");
            return;
        }

        int id = Integer.parseInt(idToEdit);

        Color c = Color.builder()
                .id(id)
                .color(color)
                .build();
        boolean colorExist = adminDAO.findColorExistByIdAndColor(id, color);
        if (!colorExist) {
            adminDAO.editColor(c, oldColorName);
            request.setAttribute("msgcoe", "Edit color successfully !!");
        } else {
            request.setAttribute("errorcoe", "This color exist already, please edit different from remaining color !!");
        }

    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) {
        String category = request.getParameter("cate");

        if (category == null || category.isEmpty() || !category.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorca", "Create category error!");
            return;
        }

        boolean cateExist = adminDAO.findCateExist(category);

        if (!cateExist) {
            adminDAO.addCategory(category);
            request.setAttribute("msgca", "Create new category success");
        } else {
            request.setAttribute("errorca", "This category is exist already, please create other category!");
        }
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        String idToEdit = request.getParameter("categoryIdToUpdate");
        String cate = request.getParameter("categoryToUpdate");
        String oldCategoryName = request.getParameter("oldNameCategory");

        if (idToEdit == null || idToEdit.isEmpty() || cate == null || cate.isEmpty() || oldCategoryName == null || oldCategoryName.isEmpty() || !idToEdit.matches("^[0-9]+$") || !cate.matches("^[A-Za-z\\s]+$") || !oldCategoryName.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorce", "Edit brand error!");
            return;
        }

        int id = Integer.parseInt(idToEdit);
        Category c = Category.builder()
                .id(id)
                .category(cate)
                .build();
        boolean cateExist = adminDAO.findCateExistByIdAndCate(id, cate);

        if (!cateExist) {
            adminDAO.editCate(c, oldCategoryName);
            request.setAttribute("msgce", "Edit category successfully !!");
        } else {
            request.setAttribute("errorce", "This category exist already, please edit different from remaining category !!");
        }
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
        String idToDelete = request.getParameter("categoryId");

        if (idToDelete == null || idToDelete.isEmpty()) {
            request.setAttribute("errorcd", "Delete gender error!");
            return;
        }

        int id = Integer.parseInt(idToDelete);

        adminDAO.deleteCateIdInProductDetail(id);
        adminDAO.deleteCateById(id);
        request.setAttribute("msgcd", "Delete category successfully!");
    }

    private void addBrand(HttpServletRequest request, HttpServletResponse response) {
        String brand = request.getParameter("brand");

        if (brand == null || brand.isEmpty() || !brand.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorba", "Create brand error!");
            return;
        }

        boolean cateExist = adminDAO.findBrandExist(brand);

        if (!cateExist) {
            adminDAO.addBrand(brand);
            request.setAttribute("msgba", "Create new brand success");
        } else {
            request.setAttribute("errorba", "This brand is exist already, please create other brand!");
        }
    }

    private void updateBrand(HttpServletRequest request, HttpServletResponse response) {
        String idToEdit = request.getParameter("brandIdToUpdate");
        String brand = request.getParameter("brandToUpdate");
        String oldBrandName = request.getParameter("oldBrandName");

        if (idToEdit == null || idToEdit.isEmpty() || brand == null || brand.isEmpty() || oldBrandName == null || oldBrandName.isEmpty() || !idToEdit.matches("^[0-9]+$") || !brand.matches("^[A-Za-z\\s]+$") || !oldBrandName.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorbe", "Edit brand error!");
            return;
        }

        int id = Integer.parseInt(idToEdit);
        Brand b = Brand.builder()
                .id(id)
                .brand(brand)
                .build();
        boolean brandExist = adminDAO.findBrandExistByBrandAndId(id, brand);

        if (!brandExist) {
            adminDAO.editBrand(b, oldBrandName);
            request.setAttribute("msgbe", "Edit brand successfully !!");
        } else {
            request.setAttribute("errorbe", "This brand exist already, please edit different from remaining brand !!");
        }
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response) {
        String idToDelete = request.getParameter("brandId");

        if (idToDelete == null || idToDelete.isEmpty()) {
            request.setAttribute("errorbd", "Delete gender error!");
            return;
        }

        int id = Integer.parseInt(idToDelete);

        adminDAO.deleteBrandIdInProductDetail(id);
        adminDAO.deleteBrandById(id);
        request.setAttribute("msgbd", "Delete brand successfully!");
    }

    private void addGender(HttpServletRequest request, HttpServletResponse response) {
        String gender = request.getParameter("gender");

        if (gender == null || gender.isEmpty() || !gender.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorga", "Create gender error!");
            return;
        }

        boolean genderExist = adminDAO.findGenderExist(gender);

        if (!genderExist) {
            adminDAO.addGender(gender);
            request.setAttribute("msgga", "Create new gender success");
        } else {
            request.setAttribute("errorga", "This gender is exist already, please create other gender!");
        }
    }

    private void deleteGender(HttpServletRequest request, HttpServletResponse response) {
        String idToDelete = request.getParameter("genderId");

        if (idToDelete == null || idToDelete.isEmpty()) {
            request.setAttribute("errorgd", "Delete gender error!");
            return;
        }

        int id = Integer.parseInt(idToDelete);

        adminDAO.deleteGenderIdInProductDetail(id);
        adminDAO.deleteGenderById(id);
        request.setAttribute("msggd", "Delete gender successfully!");
    }

    private void updateGender(HttpServletRequest request, HttpServletResponse response) {
        String idToUpdate = request.getParameter("genderIdToUpdate");
        String gender = request.getParameter("genderToUpdate");
        String oldGenderName = request.getParameter("oldGenderName");

        if (idToUpdate == null || idToUpdate.isEmpty() || gender == null || gender.isEmpty() || oldGenderName == null || oldGenderName.isEmpty() || !idToUpdate.matches("^[0-9]+$") || !gender.matches("^[A-Za-z\\s]+$") || !oldGenderName.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorge", "Edit gender error!");
            return;
        }

        int id = Integer.parseInt(idToUpdate);

        Gender g = Gender.builder()
                .id(id)
                .gender(gender)
                .build();
        boolean genderExist = adminDAO.findGenderExistByIdAndGender(id, gender);

        if (!genderExist) {
            adminDAO.editGender(g, oldGenderName);
            request.setAttribute("msgge", "Edit gender successfully !!");
        } else {
            request.setAttribute("errorge", "This gender exist already, please edit different from remaining gender !!");
        }
    }

    private void addSize(HttpServletRequest request, HttpServletResponse response) {
        String size = request.getParameter("size");

        if (size == null || size.isEmpty() || !size.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorsa", "Create size error!");
            return;
        }

        boolean sizeExist = adminDAO.findSizeExist(size);

        if (!sizeExist) {
            adminDAO.addSize(size);
            request.setAttribute("msgsa", "Create new size success");
        } else {
            request.setAttribute("errorsa", "This size is exist already, please create other gender!");
        }
    }

    private void updateSize(HttpServletRequest request, HttpServletResponse response) {
        String idToEdit = request.getParameter("sizeIdToUpdate");
        String size = request.getParameter("sizeToUpdate");
        String oldSizeName = request.getParameter("oldSizeName");

        if (idToEdit == null || idToEdit.isEmpty() || size == null || size.isEmpty() || oldSizeName == null || oldSizeName.isEmpty() || !idToEdit.matches("^[0-9]+$") || !size.matches("^[A-Za-z\\s]+$") || !oldSizeName.matches("^[A-Za-z\\s]+$")) {
            request.setAttribute("errorse", "Edit size error!");
            return;
        }
        int id = Integer.parseInt(idToEdit);
        Size s = Size.builder()
                .id(id)
                .size(size)
                .build();
        boolean genderExist = adminDAO.findSizeExistByIdAndSize(id, size);

        if (!genderExist) {
            adminDAO.editSize(s, oldSizeName);
            request.setAttribute("msgse", "Edit size successfully !!");
        } else {
            request.setAttribute("errorse", "This size exist already, please edit different from remaining gender !!");
        }
    }

    private void deleteSize(HttpServletRequest request, HttpServletResponse response) {
        String idToDelete = request.getParameter("sizeId");

        if (idToDelete == null || idToDelete.isEmpty()) {
            request.setAttribute("errorsd", "Delete size error!");
            return;
        }

        int id = Integer.parseInt(idToDelete);
        adminDAO.deleteSizeIdInProductDetail(id);
        adminDAO.deleteSizeById(id);
        request.setAttribute("msgsd", "Delete size successfully!");
    }

    private void addDiscount(HttpServletRequest request, HttpServletResponse response) {
        String discountRaw = request.getParameter("discount");
        Date currentTimestamp = new Date(System.currentTimeMillis());
        String statusRaw = request.getParameter("status");
        if (discountRaw == null || discountRaw.isEmpty() || statusRaw == null || statusRaw.isEmpty() || !discountRaw.matches("^[0-9]+$") || !statusRaw.equalsIgnoreCase("Inactive") || !statusRaw.equalsIgnoreCase("Active")) {
            request.setAttribute("errorad", "Create discount error!");
            return;
        }
        int discountData = Integer.parseInt(discountRaw);
        int status = Integer.parseInt(statusRaw);
        boolean discountExist = adminDAO.findDiscountExist(discountData);
        Discount discount = Discount.builder()
                .create_at(currentTimestamp)
                .discount_percent(discountData)
                .status(status)
                .build();
        if (!discountExist) {
            adminDAO.addDiscount(discount);
            request.setAttribute("msgad", "Create new discount success!");
        } else {
            request.setAttribute("errorad", "This discount is exist already, please create other discount!");
        }
    }

    private void deleteDiscount(HttpServletRequest request, HttpServletResponse response) {
        String idToDelete = request.getParameter("discountId");

        if (idToDelete == null || idToDelete.isEmpty()) {
            request.setAttribute("errordd", "Delete discount error!");
            return;
        }

        int id = Integer.parseInt(idToDelete);
        adminDAO.deleteDiscountById(id);
        request.setAttribute("msgdd", "Delete discount successfully!");
    }

    private void updateDiscount(HttpServletRequest request, HttpServletResponse response) {
        String idToEdit = request.getParameter("discountIdToUpdate");
        String oldDiscountRaw = request.getParameter("oldDiscount");
        String discountPercentRaw = request.getParameter("discountPercentToUpdate");
        String statusRaw = request.getParameter("status");

        if (idToEdit == null || idToEdit.isEmpty() || discountPercentRaw == null || discountPercentRaw.isEmpty() || statusRaw == null || statusRaw.isEmpty() || oldDiscountRaw == null || oldDiscountRaw.isEmpty() || !discountPercentRaw.matches("^[0-9]+$") || !oldDiscountRaw.matches("^[0-9]+$") || !statusRaw.equalsIgnoreCase("Inactive") || !statusRaw.equalsIgnoreCase("Active")) {
            request.setAttribute("errored", "Edit discount error!");
            return;
        }
        int id = Integer.parseInt(idToEdit);
        int discountPercent = Integer.parseInt(discountPercentRaw);
        int oldDiscount = Integer.parseInt(oldDiscountRaw);
        int status = Integer.parseInt(statusRaw);
        Discount discount = Discount.builder()
                .id(id)
                .discount_percent(discountPercent)
                .status(status)
                .build();
        boolean discountExist = adminDAO.findDiscountExistByDiscount(discountPercent);

        if (!discountExist) {
            adminDAO.editDiscount(discount, oldDiscount);
            request.setAttribute("msged", "Edit discount successfully !!");
        } else {
            request.setAttribute("errored", "This discount exist already, please edit different from remaining gender !!");
        }
    }

}
