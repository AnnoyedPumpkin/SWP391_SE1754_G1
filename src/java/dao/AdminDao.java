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
import entity.Product;
import entity.Size;
import helper.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            String sql = "SELECT a.Email, a.Password, a.Role_Id "
                    + "FROM Account a "
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

    public List<Product> findByPage(int page) {
        List<Product> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Brand b ON pd.Brand_Id = b.id "
                    + "JOIN Category cate ON pd.Category_Id = cate.id "
                    + "ORDER BY p.price OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            preparedStatement = connection.prepareStatement(sql);
            int parameterIndex = 1;
            preparedStatement.setInt(parameterIndex++, (page - 1) * Constant.RECORD_PER_PAGE);
            preparedStatement.setInt(parameterIndex++, Constant.RECORD_PER_PAGE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product p = new Product();
                p.setId(resultSet.getInt("id"));
                p.setName(resultSet.getString("Name"));
                p.setCreate_on(resultSet.getDate("Create_on"));
                p.setDescription(resultSet.getString("Description"));
                p.setPrice(resultSet.getInt("Price"));
                productList.add(p);
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

}
