/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import constant.Constant;
import context.DBContext;
import entity.Account;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Discount;
import entity.Gender;
import entity.Image;
import entity.Product;
import entity.Product_Detail;
import entity.Product_Form;
import entity.Size;
import helper.BCrypt;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class AdminDao extends DBContext {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    BCrypt bcryp = new BCrypt();

    public Account checkExistOfAdmin(Account account) {
        try {
            connection = this.getConnection();

            String sql = "SELECT a.Email, a.Password, a.Role_Id, r.Role "
                    + "FROM Account a "
                    + "JOIN Role r ON r.Id = a.Role_Id "
                    + "WHERE a.Email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");

                // Kiem tra mat khau nhap vao voi mat khau ma hoa trong db
                if (bcryp.checkpw(account.getPassword(), hashedPassword)) {
                    Account foundAccount = new Account();
                    foundAccount.setEmail(resultSet.getString("email"));
                    foundAccount.setPassword(hashedPassword);
                    foundAccount.setRole_Id(resultSet.getInt("Role_Id"));
                    foundAccount.setRole(resultSet.getString("Role"));
                    return foundAccount;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Color> findAllColor() {
        List<Color> listC = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Color c ORDER BY c.id";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Color color = new Color();
                color.setId(resultSet.getInt("Id"));
                color.setColor(resultSet.getString("Color"));
                listC.add(color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listC;
    }

    public List<Brand> findAllBrand() {
        List<Brand> listB = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Brand b ORDER BY b.id";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Brand brand = new Brand();
                brand.setId(resultSet.getInt("Id"));
                brand.setBrand(resultSet.getString("Brand"));
                listB.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listB;
    }

    public List<Gender> findAllGender() {
        List<Gender> listG = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Gender g ORDER BY g.id";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Gender gender = new Gender();
                gender.setId(resultSet.getInt("Id"));
                gender.setGender(resultSet.getString("Gender"));
                listG.add(gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listG;
    }

    public List<Category> findAllCategory() {
        List<Category> listC = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Category c ORDER BY c.id";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("Id"));
                category.setCategory(resultSet.getString("Category"));
                listC.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listC;
    }

    public boolean findColorExist(String color) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Color WHERE Color = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, color);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findColorExistByIdAndColor(int id, String color) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Color WHERE Color = ? AND Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, color);
            preparedStatement.setInt(2, id);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addColor(String color) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Color (Color) VALUES (?)";

            // Create a PreparedStatement with the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, color);

            int rowsAffected = preparedStatement.executeUpdate();

            // Close the PreparedStatement when you're done
            preparedStatement.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void deleteColorById(int id) {
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Color WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteColorIdInProductDetail(int colorId) {
        try {
            Connection connection = getConnection();

            String sql = "UPDATE Product_Detail SET Color_Id = NULL WHERE Color_Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, colorId);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editColor(Color c, String oldColorName) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Color "
                    + "SET Color = ? "
                    + "WHERE Id = ? AND Color = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getColor());
            preparedStatement.setInt(2, c.getId());
            preparedStatement.setString(3, oldColorName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findCateExist(String category) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Category WHERE Category = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findCateExistByIdAndCate(int id, String cate) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Category WHERE Category = ? AND Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cate);
            preparedStatement.setInt(2, id);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addCategory(String category) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Category (Category) VALUES (?)";

          
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, category);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCate(Category c, String oldCategoryName) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Category "
                    + "SET Category = ? "
                    + "WHERE Id = ? AND Category = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getCategory());
            preparedStatement.setInt(2, c.getId());
            preparedStatement.setString(3, oldCategoryName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCateById(int id) {
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Category WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCateIdInProductDetail(int cateId) {
        try {
            Connection connection = getConnection();

            String sql = "UPDATE Product_Detail SET Category_Id = NULL WHERE Category_Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cateId);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addBrand(String brand) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Brand (Brand) VALUES (?)";

            // Create a PreparedStatement with the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, brand);

            int rowsAffected = preparedStatement.executeUpdate();

            // Close the PreparedStatement when you're done
            preparedStatement.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean findBrandExist(String brand) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Brand WHERE Brand = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, brand);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findBrandExistByBrandAndId(int id, String gender) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Brand WHERE Brand = ? AND Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gender);
            preparedStatement.setInt(2, id);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void editCate(Brand b) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Brand "
                    + "SET Brand = ? "
                    + "WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, b.getBrand());
            preparedStatement.setInt(2, b.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editBrand(Brand b, String oldBrandName) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Brand "
                    + "SET brand = ? "
                    + "WHERE Id = ? AND brand = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, b.getBrand());
            preparedStatement.setInt(2, b.getId());
            preparedStatement.setString(3, oldBrandName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBrandById(int id) {
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Brand WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBrandIdInProductDetail(int brandID) {
        try {
            Connection connection = getConnection();

            String sql = "UPDATE Product_Detail SET Brand_Id = NULL WHERE Brand_Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, brandID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findGenderExist(String gender) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Gender WHERE Gender = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gender);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findGenderExistByIdAndGender(int id, String gender) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Gender WHERE Gender = ? AND Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gender);
            preparedStatement.setInt(2, id);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addGender(String gender) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Gender (Gender) VALUES (?)";

            // Create a PreparedStatement with the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, gender);

            int rowsAffected = preparedStatement.executeUpdate();

            // Close the PreparedStatement when you're done
            preparedStatement.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void deleteGenderById(int id) {
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Gender WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGenderIdInProductDetail(int genderID) {
        try {
            Connection connection = getConnection();

            String sql = "UPDATE Product_Detail SET Gender_Id = NULL WHERE Gender_Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, genderID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editGender(Gender g, String oldGenderName) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Gender "
                    + "SET gender = ? "
                    + "WHERE Id = ? AND gender = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, g.getGender());
            preparedStatement.setInt(2, g.getId());
            preparedStatement.setString(3, oldGenderName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Size> findAllSize() {
        List<Size> listS = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Size s ORDER BY s.id";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Size size = new Size();
                size.setId(resultSet.getInt("Id"));
                size.setSize(resultSet.getString("Size"));
                listS.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listS;
    }

    public int addSize(String size) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Size (Size) VALUES (?)";

            // Create a PreparedStatement with the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, size);

            int rowsAffected = preparedStatement.executeUpdate();

            // Close the PreparedStatement when you're done
            preparedStatement.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean findSizeExist(String size) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Size WHERE Size = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, size);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findSizeExistByIdAndSize(int id, String size) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Size WHERE Size = ? AND Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, size);
            preparedStatement.setInt(2, id);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void editSize(Size s, String oldSizeName) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Size "
                    + "SET size = ? "
                    + "WHERE Id = ? AND size = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, s.getSize());
            preparedStatement.setInt(2, s.getId());
            preparedStatement.setString(3, oldSizeName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSizeById(int id) {
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Size WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSizeIdInProductDetail(int sizeID) {
        try {
            Connection connection = getConnection();

            String sql = "UPDATE Product_Detail SET Size_Id = NULL WHERE Size_Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sizeID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> findAllProducts() {
        List<Product> listP = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product p";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product p = new Product();
                p.setId(resultSet.getInt("Id"));
                p.setName(resultSet.getString("Name"));
                p.setCreate_on(resultSet.getDate("Create_on"));
                p.setDescription(resultSet.getString("Description"));
                p.setPrice(resultSet.getDouble("Price"));
                listP.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listP;
    }

    public int findTotalProducts() {
        int totalProduct = 0;

        try {
            connection = this.getConnection();

            String sql = "SELECT COUNT(*) FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalProduct = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProduct;
    }

    public int findTotalProducts(String keyword) {
        int totalProduct = 0;

        try {
            connection = this.getConnection();

            String sql = "SELECT COUNT(*) FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id ";

            if (keyword != null && !keyword.equals("null")) {
                sql += "WHERE p.Name LIKE ? ";
            }
            preparedStatement = connection.prepareStatement(sql);
            int parameterIndex = 1;

            if (keyword != null && !keyword.equals("null")) {
                preparedStatement.setString(parameterIndex++, "%" + keyword + "%");
            }
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalProduct = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProduct;
    }

    public int findTotalProducts(String brandID, String cateID, String priceRange, String minPrice, String maxPrice, String colorID, String sizeID, String genderID) {
        int totalProduct = 0;

        try {
            connection = this.getConnection();

            String sql = "SELECT COUNT(*) FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id ";
            if (brandID != null && !brandID.equals("null")) {
                sql += "WHERE pd.Brand_Id = ? ";
            }
            if (cateID != null && !cateID.equals("null")) {
                if (sql.contains("WHERE")) {
                    sql += "AND pd.Category_Id = ? ";
                } else {
                    sql += "WHERE pd.Category_Id = ? ";
                }
            }
            if (colorID != null && !colorID.equals("null")) {
                if (sql.contains("WHERE")) {
                    sql += "AND pd.Color_Id = ? ";
                } else {
                    sql += "WHERE pd.Color_Id = ? ";
                }
            }
            if (sizeID != null && !sizeID.equals("null")) {
                if (sql.contains("WHERE")) {
                    sql += "AND pd.Size_Id = ? ";
                } else {
                    sql += "WHERE pd.Size_Id = ? ";
                }
            }
            if (genderID != null && !genderID.equals("null")) {
                if (sql.contains("WHERE")) {
                    sql += "AND pd.Gender_Id = ? ";
                } else {
                    sql += "WHERE pd.Gender_Id = ? ";
                }
            }
            if (priceRange == null || priceRange.isEmpty()) {
                if (sql.contains("WHERE")) {
                    sql += "AND p.price BETWEEN 0 AND 1000000 ";
                } else {
                    sql += "WHERE p.price BETWEEN 0 AND 1000000 ";
                }
            } else {
                if (sql.contains("WHERE")) {
                    sql += "AND p.price BETWEEN ? AND ? ";
                } else {
                    sql += "WHERE p.price BETWEEN ? AND ? ";
                }
            }
            preparedStatement = connection.prepareStatement(sql);
            int parameterIndex = 1;

            if (brandID != null && !brandID.equals("null")) {
                preparedStatement.setString(parameterIndex++, brandID);
            }
            if (cateID != null && !cateID.equals("null")) {
                preparedStatement.setString(parameterIndex++, cateID);
            }
            if (colorID != null && !colorID.equals("null")) {
                preparedStatement.setString(parameterIndex++, colorID);
            }
            if (sizeID != null && !sizeID.equals("null")) {
                preparedStatement.setString(parameterIndex++, sizeID);
            }
            if (genderID != null && !genderID.equals("null")) {
                preparedStatement.setString(parameterIndex++, genderID);
            }
            if (priceRange != null && !priceRange.isEmpty()) {
                preparedStatement.setString(parameterIndex++, minPrice);
                preparedStatement.setString(parameterIndex++, maxPrice);
            }
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalProduct = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProduct;
    }

    public List<Product_Form> findByPage(int page) {
        List<Product_Form> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "LEFT JOIN Color c ON pd.Color_Id = c.Id "
                    + "LEFT JOIN Category cate ON pd.Category_Id = cate.id "
                    + "LEFT JOIN Size s ON pd.Size_Id = s.Id "
                    + "LEFT JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "LEFT JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "ORDER BY p.price OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            preparedStatement = connection.prepareStatement(sql);
            int parameterIndex = 1;
            preparedStatement.setInt(parameterIndex++, (page - 1) * Constant.RECORD_PER_PAGE);
            preparedStatement.setInt(parameterIndex++, Constant.RECORD_PER_PAGE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product_Form pf = new Product_Form();
                pf.setId(resultSet.getInt("id"));
                pf.setName(resultSet.getString("Name"));
                pf.setCreate_on(resultSet.getDate("Create_on"));
                pf.setDescription(resultSet.getString("Description"));
                pf.setPrice(resultSet.getDouble("Price"));
                pf.setImage_path(resultSet.getString("Image_path"));
                pf.setProduct_id(resultSet.getInt("Product_Id"));
                pf.setColor_id(resultSet.getInt("Color_Id"));
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setSize_id(resultSet.getInt("Size_Id"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setStock(resultSet.getInt("Stock"));
                pf.setGender_id(resultSet.getInt("Gender_Id"));
                pf.setColor(resultSet.getString("Color"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setSize(resultSet.getString("Size"));
                pf.setBrand(resultSet.getString("Brand"));
                pf.setGender(resultSet.getString("Gender"));
                productList.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

//    public List<Product> findByPage(int page, String brandID, String cateID, String priceRange, String minPrice, String maxPrice, String colorID, String sizeID, String genderID) {
//        List<Product> productList = new ArrayList<>();
//        try {
//            connection = this.getConnection();
//            String sql = "SELECT * FROM Product p "
//                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
//                    + "JOIN Brand b ON pd.Brand_Id = b.id "
//                    + "JOIN Category cate ON pd.Category_Id = cate.id ";
//            if (brandID != null && !brandID.equals("null")) {
//                sql += "WHERE pd.Brand_Id = ? ";
//            }
//            if (cateID != null && !cateID.equals("null")) {
//                if (sql.contains("WHERE")) {
//                    sql += "AND pd.Category_Id = ? ";
//                } else {
//                    sql += "WHERE pd.Category_Id = ? ";
//                }
//            }
//            if (colorID != null && !colorID.equals("null")) {
//                if (sql.contains("WHERE")) {
//                    sql += "AND pd.Color_Id = ? ";
//                } else {
//                    sql += "WHERE pd.Color_Id = ? ";
//                }
//            }
//            if (sizeID != null && !sizeID.equals("null")) {
//                if (sql.contains("WHERE")) {
//                    sql += "AND pd.Size_Id = ? ";
//                } else {
//                    sql += "WHERE pd.Size_Id = ? ";
//                }
//            }
//            if (genderID != null && !genderID.equals("null")) {
//                if (sql.contains("WHERE")) {
//                    sql += "AND pd.Gender_Id = ? ";
//                } else {
//                    sql += "WHERE pd.Gender_Id = ? ";
//                }
//            }
//            if (priceRange == null || priceRange.isEmpty()) {
//                if (sql.contains("WHERE")) {
//                    sql += "AND p.price BETWEEN 0 AND 1000000 ";
//                } else {
//                    sql += "WHERE p.price BETWEEN 0 AND 1000000 ";
//                }
//            } else {
//                if (sql.contains("WHERE")) {
//                    sql += "AND p.price BETWEEN ? AND ? ";
//                } else {
//                    sql += "WHERE p.price BETWEEN ? AND ? ";
//                }
//            }
//            sql += "ORDER BY p.price OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
//            preparedStatement = connection.prepareStatement(sql);
//            int parameterIndex = 1;
//
//            if (brandID != null && !brandID.equals("null")) {
//                preparedStatement.setString(parameterIndex++, brandID);
//            }
//            if (cateID != null && !cateID.equals("null")) {
//                preparedStatement.setString(parameterIndex++, cateID);
//            }
//            if (colorID != null && !colorID.equals("null")) {
//                preparedStatement.setString(parameterIndex++, colorID);
//            }
//            if (sizeID != null && !sizeID.equals("null")) {
//                preparedStatement.setString(parameterIndex++, sizeID);
//            }
//            if (genderID != null && !genderID.equals("null")) {
//                preparedStatement.setString(parameterIndex++, genderID);
//            }
//            if (priceRange != null && !priceRange.isEmpty()) {
//                preparedStatement.setString(parameterIndex++, minPrice);
//                preparedStatement.setString(parameterIndex++, maxPrice);
//            }
//            preparedStatement.setInt(parameterIndex++, (page - 1) * Constant.RECORD_PER_PAGE);
//            preparedStatement.setInt(parameterIndex++, Constant.RECORD_PER_PAGE);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Product p = new Product();
//                p.setId(resultSet.getInt("id"));
//                p.setName(resultSet.getString("Name"));
//                p.setCreate_on(resultSet.getDate("Create_on"));
//                p.setDescription(resultSet.getString("Description"));
//                p.setPrice(resultSet.getInt("Price"));
//                productList.add(p);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return productList;
//    }
    public List<Product_Form> findPageByKeyword(int page, String sorted, String keyword) {
        List<Product_Form> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "LEFT JOIN Color c ON pd.Color_Id = c.Id "
                    + "LEFT JOIN Category cate ON pd.Category_Id = cate.id "
                    + "LEFT JOIN Size s ON pd.Size_Id = s.Id "
                    + "LEFT JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "LEFT JOIN Gender g ON pd.Gender_Id = g.Id ";
            if (keyword != null && !keyword.equals("null")) {
                sql += "WHERE p.Name LIKE ? ";
            }

            sql += "ORDER BY CASE WHEN ? = 'desc' THEN p.price END DESC, "
                    + "CASE WHEN ? = 'asc' THEN p.price END ASC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            preparedStatement = connection.prepareStatement(sql);
            int parameterIndex = 1;

            if (keyword != null && !keyword.equals("null")) {
                preparedStatement.setString(parameterIndex++, "%" + keyword + "%");
            }

            preparedStatement.setString(parameterIndex++, sorted);
            preparedStatement.setString(parameterIndex++, sorted);
            preparedStatement.setInt(parameterIndex++, (page - 1) * Constant.RECORD_PER_PAGE);
            preparedStatement.setInt(parameterIndex++, Constant.RECORD_PER_PAGE);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product_Form pf = new Product_Form();
                pf.setId(resultSet.getInt("id"));
                pf.setName(resultSet.getString("Name"));
                pf.setCreate_on(resultSet.getDate("Create_on"));
                pf.setDescription(resultSet.getString("Description"));
                pf.setPrice(resultSet.getDouble("Price"));
                pf.setImage_path(resultSet.getString("Image_path"));
                pf.setProduct_id(resultSet.getInt("Product_Id"));
                pf.setColor_id(resultSet.getInt("Color_Id"));
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setSize_id(resultSet.getInt("Size_Id"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setStock(resultSet.getInt("Stock"));
                pf.setGender_id(resultSet.getInt("Gender_Id"));
                pf.setColor(resultSet.getString("Color"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setSize(resultSet.getString("Size"));
                pf.setBrand(resultSet.getString("Brand"));
                pf.setGender(resultSet.getString("Gender"));
                productList.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product_Form> findByPage(int page, String sorted, String brandID, String cateID, String priceRange, String minPrice, String maxPrice, String colorID, String sizeID, String genderID) {
        List<Product_Form> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id ";
            if (brandID != null && !brandID.equals("null")) {
                sql += "WHERE pd.Brand_Id = ? ";
            }
            if (cateID != null && !cateID.equals("null")) {
                if (sql.contains("WHERE")) {
                    sql += "AND pd.Category_Id = ? ";
                } else {
                    sql += "WHERE pd.Category_Id = ? ";
                }
            }
            if (colorID != null && !colorID.equals("null")) {
                if (sql.contains("WHERE")) {
                    sql += "AND pd.Color_Id = ? ";
                } else {
                    sql += "WHERE pd.Color_Id = ? ";
                }
            }
            if (sizeID != null && !sizeID.equals("null")) {
                if (sql.contains("WHERE")) {
                    sql += "AND pd.Size_Id = ? ";
                } else {
                    sql += "WHERE pd.Size_Id = ? ";
                }
            }
            if (genderID != null && !genderID.equals("null")) {
                if (sql.contains("WHERE")) {
                    sql += "AND pd.Gender_Id = ? ";
                } else {
                    sql += "WHERE pd.Gender_Id = ? ";
                }
            }
            if (priceRange == null || priceRange.isEmpty()) {
                if (sql.contains("WHERE")) {
                    sql += "AND p.price BETWEEN 0 AND 1000000 ";
                } else {
                    sql += "WHERE p.price BETWEEN 0 AND 1000000 ";
                }
            } else {
                if (sql.contains("WHERE")) {
                    sql += "AND p.price BETWEEN ? AND ? ";
                } else {
                    sql += "WHERE p.price BETWEEN ? AND ? ";
                }
            }
            sql += "ORDER BY CASE WHEN ? = 'desc' THEN p.price END DESC, "
                    + "CASE WHEN ? = 'asc' THEN p.price END ASC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            preparedStatement = connection.prepareStatement(sql);
            int parameterIndex = 1;

            if (brandID != null && !brandID.equals("null")) {
                preparedStatement.setString(parameterIndex++, brandID);
            }
            if (cateID != null && !cateID.equals("null")) {
                preparedStatement.setString(parameterIndex++, cateID);
            }
            if (colorID != null && !colorID.equals("null")) {
                preparedStatement.setString(parameterIndex++, colorID);
            }
            if (sizeID != null && !sizeID.equals("null")) {
                preparedStatement.setString(parameterIndex++, sizeID);
            }
            if (genderID != null && !genderID.equals("null")) {
                preparedStatement.setString(parameterIndex++, genderID);
            }
            if (priceRange != null && !priceRange.isEmpty()) {
                preparedStatement.setString(parameterIndex++, minPrice);
                preparedStatement.setString(parameterIndex++, maxPrice);
            }

            preparedStatement.setString(parameterIndex++, sorted);
            preparedStatement.setString(parameterIndex++, sorted);
            preparedStatement.setInt(parameterIndex++, (page - 1) * Constant.RECORD_PER_PAGE);
            preparedStatement.setInt(parameterIndex++, Constant.RECORD_PER_PAGE);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product_Form pf = new Product_Form();
                pf.setId(resultSet.getInt("id"));
                pf.setName(resultSet.getString("Name"));
                pf.setCreate_on(resultSet.getDate("Create_on"));
                pf.setDescription(resultSet.getString("Description"));
                pf.setPrice(resultSet.getDouble("Price"));
                pf.setImage_path(resultSet.getString("Image_path"));
                pf.setProduct_id(resultSet.getInt("Product_Id"));
                pf.setColor_id(resultSet.getInt("Color_Id"));
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setSize_id(resultSet.getInt("Size_Id"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setStock(resultSet.getInt("Stock"));
                pf.setGender_id(resultSet.getInt("Gender_Id"));
                pf.setColor(resultSet.getString("Color"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setSize(resultSet.getString("Size"));
                pf.setBrand(resultSet.getString("Brand"));
                pf.setGender(resultSet.getString("Gender"));
                productList.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Brand> countProductsByBrand() {
        List<Brand> brandCounts = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT b.Brand, b.id, COUNT(*) AS Count "
                    + "FROM Product_Detail pd "
                    + "INNER JOIN Brand b ON pd.brand_id = b.id "
                    + "GROUP BY b.Brand, b.id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand b = new Brand();
                b.setId(resultSet.getInt("Id"));
                b.setBrand(resultSet.getString("Brand"));
                b.setCountEachBrand(resultSet.getInt("Count"));
                brandCounts.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandCounts;
    }

    public Product_Form findProductByID(String productID, String colorID, String categoryID, String sizeID, String brandID, String genderID) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "LEFT JOIN Color c ON pd.Color_Id = c.Id "
                    + "LEFT JOIN Category cate ON pd.Category_Id = cate.id "
                    + "LEFT JOIN Size s ON pd.Size_Id = s.Id "
                    + "LEFT JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "LEFT JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "WHERE p.Id = ? ";
            if (brandID != null && !brandID.equals("null")) {
                sql += "AND pd.Brand_Id = ? ";
            }
            if (categoryID != null && !categoryID.equals("null")) {
                sql += "AND pd.Category_Id = ? ";
            }
            if (colorID != null && !colorID.equals("null") && !colorID.equals("0")) {
                sql += "AND pd.Color_Id = ? ";
            }
            if (sizeID != null && !sizeID.equals("null")) {
                sql += "AND pd.Size_Id = ? ";
            }
            if (genderID != null && !genderID.equals("null")) {
                sql += "AND pd.Gender_Id = ? ";

            }

            preparedStatement = connection.prepareStatement(sql);
            int parameterIndex = 1;

            if (productID != null && !productID.equals("null")) {
                preparedStatement.setString(parameterIndex++, productID);
            }
            if (brandID != null && !brandID.equals("null")) {
                preparedStatement.setString(parameterIndex++, brandID);
            }
            if (categoryID != null && !categoryID.equals("null")) {
                preparedStatement.setString(parameterIndex++, categoryID);
            }
            if (colorID != null && !colorID.equals("null") && !colorID.equals("0")) {
                preparedStatement.setString(parameterIndex++, colorID);
            }
            if (sizeID != null && !sizeID.equals("null")) {
                preparedStatement.setString(parameterIndex++, sizeID);
            }
            if (genderID != null && !genderID.equals("null")) {
                preparedStatement.setString(parameterIndex++, genderID);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product_Form pf = new Product_Form();
                pf.setId(resultSet.getInt("id"));
                pf.setName(resultSet.getString("Name"));
                pf.setCreate_on(resultSet.getDate("Create_on"));
                pf.setDescription(resultSet.getString("Description"));
                pf.setPrice(resultSet.getDouble("Price"));
                pf.setImage_path(resultSet.getString("Image_path"));
                pf.setProduct_id(resultSet.getInt("Product_Id"));
                pf.setColor_id(resultSet.getInt("Color_Id"));
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setSize_id(resultSet.getInt("Size_Id"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setStock(resultSet.getInt("Stock"));
                pf.setGender_id(resultSet.getInt("Gender_Id"));
                pf.setColor(resultSet.getString("Color"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setSize(resultSet.getString("Size"));
                pf.setBrand(resultSet.getString("Brand"));
                pf.setGender(resultSet.getString("Gender"));
                return pf;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean findProductExist(String productName, int colorID, int cateID, int sizeID, int brandID, int genderID) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "WHERE p.Name = ? AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, colorID);
            preparedStatement.setInt(3, cateID);
            preparedStatement.setInt(4, sizeID);
            preparedStatement.setInt(5, brandID);
            preparedStatement.setInt(6, genderID);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findExistProduct(String productName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Product p "
                    + "WHERE p.Name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertNewProduct(Product_Form productForm) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Product (Name, Create_on, Description, Price, Image_path) VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productForm.getName());
            preparedStatement.setDate(2, (Date) productForm.getCreate_on());
            preparedStatement.setString(3, productForm.getDescription());
            preparedStatement.setDouble(4, productForm.getPrice());
            preparedStatement.setString(5, productForm.getImage_path());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getProductIdByProductName(String productName) {
        int productId = 0;
        try {
            connection = this.getConnection();

            String sql = "SELECT Id FROM Product WHERE Name = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productName);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    productId = resultSet.getInt("Id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productId;
    }

    public void insertProductDetail(Product_Detail productDetail) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Product_Detail (Product_Id, Color_Id, Category_Id, Size_Id, Brand_Id, Stock, Gender_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productDetail.getProduct_id());
            preparedStatement.setInt(2, productDetail.getColor_id());
            preparedStatement.setInt(3, productDetail.getCategory_id());
            preparedStatement.setInt(4, productDetail.getSize_id());
            preparedStatement.setInt(5, productDetail.getBrand_id());
            preparedStatement.setInt(6, productDetail.getStock());
            preparedStatement.setInt(7, productDetail.getGender_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Image> getImagesByProductID(String productID) {
        List<Image> images = new ArrayList<>();

        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Image WHERE Product_Id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productID);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Image image = Image.builder()
                            .id(resultSet.getInt("Id"))
                            .product_Id(resultSet.getInt("Product_Id"))
                            .image(resultSet.getString("Image"))
                            .build();

                    images.add(image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public void deleteProductDetailById(String idProduct, int idColor, int idCate, int idSize, int idBrand, int idGender) {
        try {
            connection = this.getConnection();

            String sql = "DELETE FROM Product_Detail WHERE Product_Id = ? AND Color_Id = ? AND Category_Id = ? AND Size_Id = ? AND Brand_Id = ? AND Gender_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idProduct);
            preparedStatement.setInt(2, idColor);
            preparedStatement.setInt(3, idCate);
            preparedStatement.setInt(4, idSize);
            preparedStatement.setInt(5, idBrand);
            preparedStatement.setInt(6, idGender);
            preparedStatement.executeUpdate();

//            String deleteProductSql = "DELETE FROM Product WHERE Id = ?";
//            preparedStatement = connection.prepareStatement(deleteProductSql);
//            preparedStatement.setString(1, idProduct);
//            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findProductDetailExist(String idProduct, int idColor, int idCate, int idSize, int idBrand, int idGender) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Product_Detail pd "
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "WHERE pd.Product_Id = ? AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idProduct);
            preparedStatement.setInt(2, idColor);
            preparedStatement.setInt(3, idCate);
            preparedStatement.setInt(4, idSize);
            preparedStatement.setInt(5, idBrand);
            preparedStatement.setInt(6, idGender);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product_Detail> findProductDetail(String idProduct, int idColor, int idCate, int idSize, int idBrand, int idGender) {
        List<Product_Detail> listPd = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Product_Detail pd "
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "WHERE pd.Product_Id = ? AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idProduct);
            preparedStatement.setInt(2, idColor);
            preparedStatement.setInt(3, idCate);
            preparedStatement.setInt(4, idSize);
            preparedStatement.setInt(5, idBrand);
            preparedStatement.setInt(6, idGender);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product_Detail pd = Product_Detail.builder()
                        .id(resultSet.getInt("Id"))
                        .product_id(resultSet.getInt("Product_Id"))
                        .build();
                listPd.add(pd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPd;
    }

    public void deleteProductById(String idProduct) {
        try {
            connection = this.getConnection();

            String sql = "DELETE FROM Product WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, idProduct);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Discount> findAllDiscount() {
        List<Discount> listD = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Discount d";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Discount d = new Discount();
                d.setId(resultSet.getInt("Id"));
                d.setCreate_at(resultSet.getDate("Create_at"));
                d.setDiscount_percent(resultSet.getInt("Discount_percent"));
                d.setStatus(resultSet.getInt("Status"));
                listD.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listD;
    }

    public List<Image> getImagesToEditByProductID(String productId) {
        List<Image> images = new ArrayList<>();

        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Image WHERE Product_Id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productId);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Image image = Image.builder()
                            .id(resultSet.getInt("Id"))
                            .product_Id(resultSet.getInt("Product_Id"))
                            .image(resultSet.getString("Image"))
                            .build();

                    images.add(image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public void saveImage(Image image) {
        try {
            connection = this.getConnection();
            String[] individualPaths = image.getImage().split(",");
            for (String path : individualPaths) {
                String sql = "INSERT INTO Image (Product_Id, Image) VALUES (?, ?)";

                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, image.getProduct_Id());
                preparedStatement.setString(2, path.trim());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findExistProduct(Product_Form productForm) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "WHERE p.Name = ? AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productForm.getName());
            preparedStatement.setInt(2, productForm.getColor_id());
            preparedStatement.setInt(3, productForm.getCategory_id());
            preparedStatement.setInt(4, productForm.getSize_id());
            preparedStatement.setInt(5, productForm.getBrand_id());
            preparedStatement.setInt(6, productForm.getGender_id());

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateProductDetail(Product_Form productForm, int colorIdOld, int cateIdOld, int sizeIdOld, int genderIdOld, int brandIdOld) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Product_Detail SET Color_Id = ?, Category_Id = ?, Size_Id = ?, Brand_Id = ?, Stock = ?, Gender_Id = ? "
                    + "WHERE Product_Id = ? AND Color_Id = ? AND Category_Id = ? "
                    + "AND Size_Id = ? AND Brand_Id = ? AND Gender_Id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productForm.getColor_id());
            preparedStatement.setInt(2, productForm.getCategory_id());
            preparedStatement.setInt(3, productForm.getSize_id());
            preparedStatement.setInt(4, productForm.getBrand_id());
            preparedStatement.setInt(5, productForm.getStock());
            preparedStatement.setInt(6, productForm.getGender_id());
            preparedStatement.setInt(7, productForm.getId());
            preparedStatement.setInt(8, colorIdOld);
            preparedStatement.setInt(9, cateIdOld);
            preparedStatement.setInt(10, sizeIdOld);
            preparedStatement.setInt(11, brandIdOld);
            preparedStatement.setInt(12, genderIdOld);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product_Form productForm) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Product SET Name = ?, Description = ?, Price = ? WHERE Id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productForm.getName());
            preparedStatement.setString(2, productForm.getDescription());
            preparedStatement.setDouble(3, productForm.getPrice());
            preparedStatement.setInt(4, productForm.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductImageLayout(Product_Form productForm) {
        try {
            connection = this.getConnection();
            String sql = "UPDATE Product SET Image_path = ? WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productForm.getImage_path());
            preparedStatement.setInt(2, productForm.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteImagesExistAlready(Image image) {
        try {
            connection = this.getConnection();
            String[] individualPaths = image.getImage().split(",");
            for (String path : individualPaths) {
                String sql = "DELETE FROM Image WHERE Product_Id = ? AND Image IN (?)";

                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, image.getProduct_Id());
                preparedStatement.setString(2, path.trim());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSizeByProductID(int productID) {
        int count = 0;
        try {
            connection = this.getConnection();
            String sql = "SELECT COUNT(*) FROM Image WHERE Product_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void deleteImagesProduct(String productIdToDeleteImage, String imagePathToDelete) {
        try {
            connection = this.getConnection();

            String sql = "DELETE FROM Image WHERE Product_Id = ? AND Image = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productIdToDeleteImage);
            preparedStatement.setString(2, imagePathToDelete);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findDiscountExist(int discountData) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Discount WHERE Discount_percent = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, discountData);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addDiscount(Discount discount) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Discount (Create_at, Discount_percent, Status) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, (Date) discount.getCreate_at());
            preparedStatement.setInt(2, discount.getDiscount_percent());
            preparedStatement.setInt(3, discount.getStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDiscountById(int id) {
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Discount WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findDiscountExistByIdAndDiscount(int id, int discountPercent) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Discount WHERE Discount_percent = ? AND Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, discountPercent);
            preparedStatement.setInt(2, id);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void editDiscount(Discount discount, int oldDiscount) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Discount "
                    + "SET Discount_percent = ? "
                    + "WHERE Id = ? AND Discount_percent = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, discount.getDiscount_percent());
            preparedStatement.setInt(2, discount.getId());
            preparedStatement.setInt(3, oldDiscount);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
