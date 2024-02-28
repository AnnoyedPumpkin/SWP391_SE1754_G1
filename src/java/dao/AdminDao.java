/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Brand;
import entity.Category;
import entity.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

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

            String sql = "SELECT a.Email, a.Password, a.Role_Id, a.Id, a.Member_Code "
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
                    foundAccount.setId(resultSet.getInt("Id"));
                    foundAccount.setMember_code(resultSet.getString("member_code"));
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

    public void editColor(Color c) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Color "
                    + "SET Color = ? "
                    + "WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getColor());
            preparedStatement.setInt(2, c.getId());
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

    public void editCate(Category c) {
        try {
            connection = this.getConnection();

            String sql = "UPDATE Category "
                    + "SET Category = ? "
                    + "WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getCategory());
            preparedStatement.setInt(2, c.getId());
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

    public boolean findBrandByOldBrandName(String oldBrandName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * "
                    + "FROM Brand b "
                    + "WHERE b.brand = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, oldBrandName);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    public boolean findCategoryByOldCategoryName(String oldCategoryName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * "
                    + "FROM Category c "
                    + "WHERE c.category = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, oldCategoryName);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findColorByOldColorName(String oldColorName) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * "
                    + "FROM Color c "
                    + "WHERE c.color = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, oldColorName);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
