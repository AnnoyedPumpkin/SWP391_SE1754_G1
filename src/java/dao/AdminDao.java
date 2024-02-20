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
            String sql = "SELECT * FROM Color WHERE Color = ? OR Id = ?";
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

    public boolean findColorByOldColorAndId(int id, String oldColorName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * "
                    + "FROM Color "
                    + "WHERE id = ? "
                    + "AND color = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, oldColorName);

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

    public void editColor(Color c, String oldColorName) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Color "
                    + "SET Color = ? "
                    + "WHERE Color = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getColor());
            preparedStatement.setString(2, oldColorName);
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
            String sql = "SELECT * FROM Gender WHERE Gender = ? OR Id = ?";
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

    public boolean findCateByOldCateAndId(int id, String oldCateName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * "
                    + "FROM Category g "
                    + "WHERE id = ? "
                    + "AND category = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, oldCateName);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addCategory(String category) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Category (Category) VALUES (?)";

            // Create a PreparedStatement with the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, category);

            int rowsAffected = preparedStatement.executeUpdate();

            // Close the PreparedStatement when you're done
            preparedStatement.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void editCate(Category c, String oldCategoryName) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Category "
                    + "SET Category = ? "
                    + "WHERE Category = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getCategory());
            preparedStatement.setString(2, oldCategoryName);
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
            String sql = "SELECT * FROM Brand WHERE Brand = ? OR Id = ?";
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

    public boolean findBrandByOldBrandAndId(int id, String oldBrandName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * "
                    + "FROM Brand b "
                    + "WHERE id = ? "
                    + "AND brand = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, oldBrandName);

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
                    + "WHERE brand = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, b.getBrand());
            preparedStatement.setString(2, oldBrandName);

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
            String sql = "SELECT * FROM Gender WHERE Gender = ? OR Id = ?";
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

    public void editGender(Gender g, String oldGenderName) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Gender "
                    + "SET gender = ? "
                    + "WHERE gender = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, g.getGender());
            preparedStatement.setString(2, oldGenderName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findGenderByOldGenderAndId(int id, String oldGenderName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * "
                    + "FROM Gender g "
                    + "WHERE id = ? "
                    + "AND gender = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, oldGenderName);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
            String sql = "SELECT * FROM Size WHERE Size = ? OR Id = ?";
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

    public boolean findSizeByOldSizeAndId(int id, String oldSizeName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * "
                    + "FROM Size "
                    + "WHERE id = ? "
                    + "AND size = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, oldSizeName);

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
                    + "WHERE size = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, s.getSize());
            preparedStatement.setString(2, oldSizeName);

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
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Brand b ON pd.Brand_Id = b.id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id ";
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
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Brand b ON pd.Brand_Id = b.id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id ";
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
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Brand b ON pd.Brand_Id = b.id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id ";
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
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id "
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
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id ";
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
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "WHERE p.Id = ? AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorID);
            preparedStatement.setString(3, categoryID);
            preparedStatement.setString(4, sizeID);
            preparedStatement.setString(5, brandID);
            preparedStatement.setString(6, genderID);
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

    public int findStockByCharacter(String productID, String colorID, String categoryID, String sizeID, String brandID, String genderID) {
        int stock = 0;
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "WHERE p.Id = ? AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorID);
            preparedStatement.setString(3, categoryID);
            preparedStatement.setString(4, sizeID);
            preparedStatement.setString(5, brandID);
            preparedStatement.setString(6, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stock = resultSet.getInt("Stock");
            }
            return stock;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Color getColorsForProduct(String productID, String colorID, String categoryID, String sizeID, String brandID, String genderID) {

        try {
            connection = this.getConnection();
            String sql = "SELECT c.* FROM Product_Detail pd "
                    + "JOIN Color c ON pd.Color_Id = c.Id "
                    + "WHERE pd.Product_Id = ? AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorID);
            preparedStatement.setString(3, categoryID);
            preparedStatement.setString(4, sizeID);
            preparedStatement.setString(5, brandID);
            preparedStatement.setString(6, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Color c = new Color();
                c.setId(resultSet.getInt("Id"));
                c.setColor(resultSet.getString("Color"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Size getSizeForProduct(String productID, String colorID, String categoryID, String sizeID, String brandID, String genderID) {

        try {
            connection = this.getConnection();
            String sql = "SELECT s.* FROM Product_Detail pd "
                    + "JOIN Size s ON pd.Size_Id = s.Id "
                    + "WHERE pd.Product_Id = ?  AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorID);
            preparedStatement.setString(3, categoryID);
            preparedStatement.setString(4, sizeID);
            preparedStatement.setString(5, brandID);
            preparedStatement.setString(6, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size s = new Size();
                s.setId(resultSet.getInt("Id"));
                s.setSize(resultSet.getString("Size"));
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Brand getBrandForProduct(String productID, String colorID, String categoryID, String sizeID, String brandID, String genderID) {

        try {
            connection = this.getConnection();
            String sql = "SELECT b.* FROM Product_Detail pd "
                    + "JOIN Brand b ON pd.Brand_Id = b.Id "
                    + "WHERE pd.Product_Id = ?  AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorID);
            preparedStatement.setString(3, categoryID);
            preparedStatement.setString(4, sizeID);
            preparedStatement.setString(5, brandID);
            preparedStatement.setString(6, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand b = new Brand();
                b.setId(resultSet.getInt("Id"));
                b.setBrand(resultSet.getString("Brand"));
                return b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category getCategoryForProduct(String productID, String colorID, String categoryID, String sizeID, String brandID, String genderID) {

        try {
            connection = this.getConnection();
            String sql = "SELECT c.* FROM Product_Detail pd "
                    + "JOIN Category c ON pd.Category_Id = c.Id "
                    + "WHERE pd.Product_Id = ?  AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorID);
            preparedStatement.setString(3, categoryID);
            preparedStatement.setString(4, sizeID);
            preparedStatement.setString(5, brandID);
            preparedStatement.setString(6, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category c = new Category();
                c.setId(resultSet.getInt("Id"));
                c.setCategory(resultSet.getString("Category"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Gender getGenderForProduct(String productID, String colorID, String categoryID, String sizeID, String brandID, String genderID) {

        try {
            connection = this.getConnection();
            String sql = "SELECT g.* FROM Product_Detail pd "
                    + "JOIN Gender g ON pd.Gender_Id = g.Id "
                    + "WHERE pd.Product_Id = ?  AND pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Size_Id = ? AND pd.Brand_Id = ? AND pd.Gender_Id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorID);
            preparedStatement.setString(3, categoryID);
            preparedStatement.setString(4, sizeID);
            preparedStatement.setString(5, brandID);
            preparedStatement.setString(6, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gender g = new Gender();
                g.setId(resultSet.getInt("Id"));
                g.setGender(resultSet.getString("Gender"));
                return g;
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
}
