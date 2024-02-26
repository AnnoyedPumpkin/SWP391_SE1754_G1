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
import java.util.ArrayList;
import java.util.Collection;
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
                List<Image> listImages = adminDAO.getImagesByProductID(productID);
                session.setAttribute("productForm", productForm);
                session.setAttribute("listImages", listImages);
                request.getRequestDispatcher("/views/admin/productDetails.jsp").forward(request, response);
                break;
            case "update-product":
                String productIdToUpdate = request.getParameter("productID");
                String colorIDToUpdate = request.getParameter("colorID");
                String categoryIDToUpdate = request.getParameter("categoryID");
                String sizeIDToUpdate = request.getParameter("sizeID");
                String brandIDToUpdate = request.getParameter("brandID");
                String genderIDToUpdate = request.getParameter("genderID");
                productForm = adminDAO.findProductByID(productIdToUpdate, colorIDToUpdate, categoryIDToUpdate, sizeIDToUpdate, brandIDToUpdate, genderIDToUpdate);
                listImages = adminDAO.getImagesByProductID(productIdToUpdate);
                session.setAttribute("productForm", productForm);
                session.setAttribute("listImages", listImages);
                session.setAttribute("listB", listB);
                session.setAttribute("listCate", listCate);
                session.setAttribute("listC", listC);
                session.setAttribute("listS", listS);
                session.setAttribute("listG", listG);
                request.getRequestDispatcher("/views/admin/editProduct.jsp").forward(request, response);
                break;
            case "delete-images-product":
                String productIdToDeleteImage = request.getParameter("imagesID");
                String imagePathToDelete = request.getParameter("imagePath");
                adminDAO.deleteImagesProduct(productIdToDeleteImage, imagePathToDelete);
                listImages = adminDAO.getImagesByProductID(productIdToDeleteImage);
                session.setAttribute("listImages", listImages);
                request.getRequestDispatcher("/views/admin/editProduct.jsp").forward(request, response);
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
            case "edit-product":
                editProduct(request, response);
                url = "/views/admin/productDetails.jsp";
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
        String action = request.getParameter("action") == null ? "default" : request.getParameter("action");
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

    private void addProduct(HttpServletRequest request, HttpServletResponse response) {
        String productName = request.getParameter("product_name");
        String productDescription = request.getParameter("productDescription");
        String productPriceRaw = request.getParameter("productPrice");
        String colorIdRaw = request.getParameter("color");
        String cateIdRaw = request.getParameter("cate");
        String sizeIdRaw = request.getParameter("size");
        String brandIdRaw = request.getParameter("brand");
        String stockRaw = request.getParameter("stock");
        String genderIdRaw = request.getParameter("gender");
        String imagePath = "";
        if (productPriceRaw == null || productPriceRaw.isEmpty() || colorIdRaw == null || colorIdRaw.isEmpty() || cateIdRaw == null || cateIdRaw.isEmpty() || brandIdRaw == null || brandIdRaw.isEmpty() || genderIdRaw == null || genderIdRaw.isEmpty()) {
            request.setAttribute("errorap", "Error when creating new product!");
            return;
        }
        double productPrice = Double.parseDouble(productPriceRaw);
        int colorID = Integer.parseInt(colorIdRaw);
        int cateID = Integer.parseInt(cateIdRaw);
        int sizeID = Integer.parseInt(sizeIdRaw);
        int brandID = Integer.parseInt(brandIdRaw);
        int stock = Integer.parseInt(stockRaw);
        int genderID = Integer.parseInt(genderIdRaw);
        Date currentTimestamp = new Date(System.currentTimeMillis());
        try {
            Part part = request.getPart("image");
            if (part != null && part.getSize() > 0) {
                String path = request.getServletContext().getRealPath("/images");
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File image = new File(dir, part.getSubmittedFileName());
                part.write(image.getAbsolutePath());
                imagePath = "/SWP391_SE1754_G1/images/" + image.getName();
            }
        } catch (Exception e) {
            request.setAttribute("errorap", "Error when creating new product!");
            return;
        }
        try {
            Product_Form productForm = Product_Form.builder()
                    .name(productName)
                    .create_on(currentTimestamp)
                    .description(productDescription)
                    .price(productPrice)
                    .image_path(imagePath)
                    .color_id(colorID)
                    .category_id(cateID)
                    .size_id(sizeID)
                    .brand_id(brandID)
                    .gender_id(genderID)
                    .build();
            boolean isExistProduct = adminDAO.findProductExist(productName, colorID, cateID, sizeID, brandID, genderID);
            if (!isExistProduct) {
                if (!adminDAO.findExistProduct(productName)) {
                    adminDAO.insertNewProduct(productForm);
                }
                int productId = adminDAO.getProductIdByProductName(productName);
                Product_Detail productDetail = Product_Detail.builder()
                        .product_id(productId)
                        .color_id(colorID)
                        .category_id(cateID)
                        .size_id(sizeID)
                        .brand_id(brandID)
                        .stock(stock)
                        .gender_id(genderID)
                        .build();
                adminDAO.insertProductDetail(productDetail);
                request.setAttribute("msgap", "Create new product successfully !!");
            } else {
                request.setAttribute("errorap", "Product exist already, please create other !!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorap", "Error when creating new product!");
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String idProduct = request.getParameter("product_ID");
        int idColor = Integer.parseInt(request.getParameter("color_ID"));
        int idCate = Integer.parseInt(request.getParameter("category_ID"));
        int idSize = Integer.parseInt(request.getParameter("size_ID"));
        int idBrand = Integer.parseInt(request.getParameter("brand_ID"));
        int idGender = Integer.parseInt(request.getParameter("gender_ID"));

        adminDAO.deleteProductDetailById(idProduct, idColor, idCate, idSize, idBrand, idGender);

        if (adminDAO.findProductDetailExist(idProduct, idColor, idCate, idSize, idBrand, idGender)) {
            request.setAttribute("errdp", "Delete product error !!");
        } else {
            List<Product_Detail> listPd = adminDAO.findProductDetail(idProduct, idColor, idCate, idSize, idBrand, idGender);
            if (listPd.size() == 0) {
                adminDAO.deleteProductById(idProduct);
            }
            request.setAttribute("msgdp", "Delete product successfully !!");
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String productIdRaw = request.getParameter("product_id");
        String productName = request.getParameter("product_name");
        String productDescription = request.getParameter("productDescription");
        String productPriceRaw = request.getParameter("productPrice");
        String colorIdOldRaw = request.getParameter("color_id");
        String cateIdOldRaw = request.getParameter("cate_id");
        String sizeIdOldRaw = request.getParameter("size_id");
        String brandIdOldRaw = request.getParameter("brand_id");
        String genderIdOldRaw = request.getParameter("gender_id");
        String colorIdRaw = request.getParameter("color");
        String cateIdRaw = request.getParameter("cate");
        String sizeIdRaw = request.getParameter("size");
        String brandIdRaw = request.getParameter("brand");
        String stockRaw = request.getParameter("stock");
        String genderIdRaw = request.getParameter("gender");
        String imagePathLayout = "";
        String imagesPath = "";
        List<String> imagePaths;
        if (productIdRaw == null || productIdRaw.isEmpty() || productPriceRaw == null || productPriceRaw.isEmpty() || colorIdRaw == null || colorIdRaw.isEmpty() || cateIdRaw == null || cateIdRaw.isEmpty() || brandIdRaw == null || brandIdRaw.isEmpty() || genderIdRaw == null || genderIdRaw.isEmpty()) {
            request.setAttribute("errorup", "Error when updating product!");
            return;
        }
        int colorIdOld = Integer.parseInt(colorIdOldRaw);
        int cateIdOld = Integer.parseInt(cateIdOldRaw);
        int sizeIdOld = Integer.parseInt(sizeIdOldRaw);
        int brandIdOld = Integer.parseInt(brandIdOldRaw);
        int genderIdOld = Integer.parseInt(genderIdOldRaw);
        int productID = Integer.parseInt(productIdRaw);
        double productPrice = Double.parseDouble(productPriceRaw);
        int colorID = Integer.parseInt(colorIdRaw);
        int cateID = Integer.parseInt(cateIdRaw);
        int sizeID = Integer.parseInt(sizeIdRaw);
        int brandID = Integer.parseInt(brandIdRaw);
        int stock = Integer.parseInt(stockRaw);
        int genderID = Integer.parseInt(genderIdRaw);

        try {
            Part part = request.getPart("image_layout");
            String path = request.getServletContext().getRealPath("/images");
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (part.getSubmittedFileName() != null && part.getSize() > 0 && part.getName().equals("image_layout")) {
                File image = new File(dir, part.getSubmittedFileName());
                part.write(image.getAbsolutePath());
                imagePathLayout = "/SWP391_SE1754_G1/images/" + image.getName();
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorup", "Error when update product image layout!");
            return;
        }
        try {
            Collection<Part> parts = request.getParts();
            imagePaths = new ArrayList<>();

            String uploadPath = request.getServletContext().getRealPath("/images");
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for (Part part : parts) {
                if (part.getSubmittedFileName() != null && part.getSize() > 0 && part.getName().equals("list_image")) {
                    File image = new File(dir, part.getSubmittedFileName());
                    part.write(image.getAbsolutePath());
                    imagesPath = "/SWP391_SE1754_G1/images/" + image.getName();
                    imagePaths.add(imagesPath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorup", "Error when updating product images!");
            return;
        }
        Product_Form productForm1 = Product_Form.builder()
                .id(productID)
                .name(productName)
                .description(productDescription)
                .price(productPrice)
                .image_path(imagePathLayout)
                .color_id(colorID)
                .category_id(cateID)
                .size_id(sizeID)
                .brand_id(brandID)
                .stock(stock)
                .gender_id(genderID)
                .build();
        boolean isExistProduct = adminDAO.findExistProduct(productForm1);
        if (!isExistProduct) {
            adminDAO.updateProductDetail(productForm1, colorIdOld, cateIdOld, sizeIdOld, genderIdOld, brandIdOld);
            adminDAO.updateProduct(productForm1);
            request.setAttribute("msgup", "Update product successfully !!");
        } else {
            request.setAttribute("errorup", "Product exist already!");
        }
        if (imagePathLayout != null && !imagePathLayout.isEmpty()) {
            adminDAO.updateProductImageLayout(productForm1);
            request.setAttribute("msgup", "Images product layout update successfully !!");
        }
        String imagePathString = String.join(",", imagePaths);
        if (imagePathString != null && !imagePathString.isEmpty()) {
            Image image = Image.builder()
                    .product_Id(productID)
                    .image(imagePathString)
                    .build();
            int size = adminDAO.getSizeByProductID(productID);
            if (imagePaths.size() + size <= 6) {
                adminDAO.deleteImagesExistAlready(image);
                adminDAO.saveImage(image);
                request.setAttribute("msgup", "Images product update successfully !!");
            } else {
                request.setAttribute("errorup", "Maximum of 6 images allowed.");
            }
        }
        Product_Form productForm = adminDAO.findProductByID(productIdRaw, colorIdRaw, cateIdRaw, sizeIdRaw, brandIdRaw, genderIdRaw);
        List<Image> listImages = adminDAO.getImagesByProductID(productIdRaw);
        session.setAttribute("productForm", productForm);
        session.setAttribute("listImages", listImages);
    }
}
