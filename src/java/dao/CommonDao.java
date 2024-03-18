/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import constant.Constant;
import context.DBContext;
import entity.Account;
import entity.Account_Form;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Gender;
import entity.Image;
import entity.Invoice_Form;
import entity.Product_Detail;
import entity.Product_Form;
import entity.Size;
import helper.BCrypt;
import static helper.BCrypt.hashpw;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author admin
 */
public class CommonDao extends DBContext {

    BCrypt bcryp = new BCrypt();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Account_Form checkExistOfAcc(Account account) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Account a "
                    + "JOIN Role r ON r.id = a.role_id "
                    + "WHERE a.status = 1 AND a.Email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");

                // Kiem tra mat khau nhap vao voi mat khau ma hoa trong db
                if (bcryp.checkpw(account.getPassword(), hashedPassword)) {
                    Account_Form af = new Account_Form();
                    af.setId(resultSet.getInt("Id"));
                    af.setEmail(resultSet.getString("Email"));
                    af.setRole(resultSet.getString("Role"));
                    af.setRole_Id(resultSet.getInt("Role_Id"));
                    return af;
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

    public boolean CheckAccount(Account account) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM [Account] WHERE Email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());

            resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Trả về true nếu tìm thấy tài khoản, ngược lại trả về false

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createAccountCustomer(Account account) {
        //Ma hoa mat khau khi them vao db
        String password = bcryp.hashpw(account.getPassword(), bcryp.gensalt());
        try {
            connection = this.getConnection();

            String sql = "INSERT INTO [dbo].[Account] ([Email], [Password], [Role_Id], [Status]) VALUES (?, ?, ?, 1)";
            preparedStatement = connection.prepareStatement(sql);

            // Set parameters
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, account.getRole_Id());

            // Execute the update
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product_Form> findByPage(int page) {
        List<Product_Form> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT p.*, cate.*, b.*, SUM(pd.Stock) as totalStockOfEachProduct FROM Product p JOIN Product_Detail pd ON pd.Product_Id = p.id \n"
                    + "JOIN Category cate ON cate.Id = p.Category_Id JOIN Brand b ON b.Id = p.Brand_Id \n"
                    + "WHERE p.status = 1 \n"
                    + "GROUP BY p.id, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, p.Price,p.Description, p.Create_on, p.Status,p.Status_date, p.Expire_date, cate.Id, cate.Category,  b.Id, b.Brand \n"
                    + "HAVING SUM(pd.Stock) > 0 ORDER BY p.create_on DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
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
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setBrand(resultSet.getString("Brand"));
                pf.setStatus(resultSet.getInt("Status"));
                productList.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public int findTotalProducts() {
        int totalProduct = 0;

        try {
            connection = this.getConnection();

            String sql = "SELECT COUNT(*) "
                    + "FROM ( "
                    + "    SELECT p.id "
                    + "    FROM Product p "
                    + "    JOIN Category c ON c.Id = p.Category_Id "
                    + "    JOIN Brand b ON b.Id = p.Brand_Id "
                    + "    JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "    WHERE p.status = 1 "
                    + "    GROUP BY p.id, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, p.Price, p.Description, p.Create_on, p.Status, p.Status_date, p.Expire_date, c.Id, c.Category, b.Id, b.Brand "
                    + "    HAVING SUM(pd.Stock) > 0 "
                    + ") AS subquery";
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

    public List<Product_Form> getTop12ProductNewest() {
        List<Product_Form> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT TOP 12 p.*, cate.*, b.*, SUM(pd.Stock) as totalStockOfEachProduct "
                    + "FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Category cate ON cate.Id = p.Category_Id "
                    + "JOIN Brand b ON b.Id = p.Brand_Id "
                    + "WHERE p.status = 1 "
                    + "GROUP BY p.id, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, p.Price,p.Description, p.Create_on, p.Status,p.Status_date, p.Expire_date, cate.Id, cate.Category,  b.Id, b.Brand "
                    + "HAVING SUM(pd.Stock) > 0 "
                    + "ORDER BY p.create_on DESC ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product_Form pf = new Product_Form();
                pf.setId(resultSet.getInt("id"));
                pf.setName(resultSet.getString("Name"));
                pf.setCreate_on(resultSet.getDate("Create_on"));
                pf.setDescription(resultSet.getString("Description"));
                pf.setPrice(resultSet.getDouble("Price"));
                pf.setImage_path(resultSet.getString("Image_path"));
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setBrand(resultSet.getString("Brand"));
                pf.setStatus(resultSet.getInt("Status"));
                productList.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product_Form getProductById(int productID) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Product p "
                    + "JOIN Category c ON c.id = p.Category_Id "
                    + "JOIN Brand b ON b.id = p.Brand_Id "
                    + "WHERE p.id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product_Form pf = new Product_Form();
                pf.setId(resultSet.getInt("Id"));
                pf.setName(resultSet.getString("Name"));
                pf.setDescription(resultSet.getString("Description"));
                pf.setPrice(resultSet.getFloat("Price"));
                pf.setImage_path(resultSet.getString("Image_path"));
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setBrand(resultSet.getString("Brand"));
                return pf;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Size> findSizeByProductId(int productID) {
        List<Size> listS = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT DISTINCT pd.Size_Id, s.Size FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.Id "
                    + "JOIN Size s ON s.Id = pd.Size_Id WHERE p.Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size s = new Size();
                s.setId(resultSet.getInt("Size_Id"));
                s.setSize(resultSet.getString("Size"));
                listS.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listS;
    }

    public List<Gender> findGenderByProductId(int productID) {
        List<Gender> listG = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT DISTINCT pd.Gender_Id, g.gender FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.Id "
                    + "JOIN Gender g ON g.Id = pd.Gender_Id WHERE p.Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gender g = new Gender();
                g.setId(resultSet.getInt("Gender_Id"));
                g.setGender(resultSet.getString("Gender"));
                listG.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listG;
    }

    public List<Color> findColorByProductId(int productID) {
        List<Color> listC = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT DISTINCT pd.Color_Id, c.Color FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.Id "
                    + "JOIN Color c ON c.Id = pd.Color_Id WHERE p.Id =? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Color c = new Color();
                c.setId(resultSet.getInt("Color_Id"));
                c.setColor(resultSet.getString("Color"));
                listC.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listC;
    }

    public List<Image> getImagesByProductID(int productID) {
        List<Image> images = new ArrayList<>();

        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Image WHERE Product_Id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productID);

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

    public List<Product_Form> getListProductRelated(String category, int id) {
        List<Product_Form> listPf = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT TOP 8 p.*, cate.*, b.*, SUM(pd.Stock) as totalStockOfEachProduct "
                    + "FROM Product p "
                    + "JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "JOIN Category cate ON cate.Id = p.Category_Id "
                    + "JOIN Brand b ON b.Id = p.Brand_Id "
                    + "WHERE p.status = 1 AND cate.category = ? AND p.id != ? "
                    + "GROUP BY p.id, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, p.Price,p.Description, p.Create_on, p.Status,p.Status_date, p.Expire_date, cate.Id, cate.Category,  b.Id, b.Brand "
                    + "HAVING SUM(pd.Stock) > 0 ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category);
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product_Form pf = new Product_Form();
                pf.setId(resultSet.getInt("Id"));
                pf.setName(resultSet.getString("Name"));
                pf.setDescription(resultSet.getString("Description"));
                pf.setPrice(resultSet.getFloat("Price"));
                pf.setImage_path(resultSet.getString("Image_path"));
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setBrand(resultSet.getString("Brand"));
                listPf.add(pf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPf;
    }

    public int getStockProductDetailByAllField(String productID, String colorId, String sizeId, String genderID) {
        int StockOfProductDetail = 0;
        try {
            connection = this.getConnection();

            String sql = "SELECT pd.Stock FROM Product_Detail pd "
                    + "WHERE pd.Product_Id = ? AND pd.Color_Id = ? AND pd.Gender_Id = ? AND pd.Size_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorId);
            preparedStatement.setString(3, genderID);
            preparedStatement.setString(4, sizeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StockOfProductDetail = resultSet.getInt("Stock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StockOfProductDetail;
    }

    public List<Gender> findGenderByColorAndSize(String productID, String colorId, String sizeId) {
        List<Gender> listG = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct g.Gender, pd.Gender_Id FROM Product_Detail pd "
                    + "JOIN Gender g ON g.Id = pd.Gender_Id "
                    + "WHERE pd.Product_Id = ? AND pd.Color_Id = ? AND pd.Size_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorId);
            preparedStatement.setString(3, sizeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gender g = new Gender();
                g.setId(resultSet.getInt("Gender_Id"));
                g.setGender(resultSet.getString("Gender"));
                listG.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listG;
    }

    public List<Gender> findGenderBySize(String productID, String sizeId) {
        List<Gender> listG = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct g.Gender, pd.Gender_Id FROM Product_Detail pd "
                    + "JOIN Gender g ON g.Id = pd.Gender_Id "
                    + "WHERE pd.Product_Id = ? AND pd.Size_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, sizeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gender g = new Gender();
                g.setId(resultSet.getInt("Gender_Id"));
                g.setGender(resultSet.getString("Gender"));
                listG.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listG;
    }

    public List<Color> findColorBySize(String productID, String sizeId) {
        List<Color> listC = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct c.Color, pd.Color_Id FROM Product_Detail pd "
                    + "JOIN Color c ON c.Id = pd.Color_Id "
                    + "WHERE pd.Product_Id = ? AND pd.Size_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, sizeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Color c = new Color();
                c.setId(resultSet.getInt("Color_Id"));
                c.setColor(resultSet.getString("Color"));
                listC.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listC;
    }

    public List<Color> findColorByGender(String productID, String genderID) {
        List<Color> listC = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct c.Color, pd.Color_Id FROM Product_Detail pd "
                    + "JOIN Color c ON c.Id = pd.Color_Id "
                    + "WHERE pd.Product_Id = ? AND pd.Gender_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Color c = new Color();
                c.setId(resultSet.getInt("Color_Id"));
                c.setColor(resultSet.getString("Color"));
                listC.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listC;
    }

    public List<Size> findSizeByGender(String productID, String genderID) {
        List<Size> listS = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct s.Size, pd.Size_Id FROM Product_Detail pd "
                    + "JOIN Size s ON s.Id = pd.Size_Id "
                    + "WHERE pd.Product_Id = ? AND pd.Gender_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size s = new Size();
                s.setId(resultSet.getInt("Size_Id"));
                s.setSize(resultSet.getString("Size"));
                listS.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listS;
    }

    public List<Size> findSizeByColorId(String productID, String colorId) {
        List<Size> listS = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct s.Size, pd.Size_Id FROM Product_Detail pd "
                    + "JOIN Size s ON s.id = pd.Size_Id WHERE pd.Product_Id = ? AND pd.Color_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size s = new Size();
                s.setId(resultSet.getInt("Size_Id"));
                s.setSize(resultSet.getString("Size"));
                listS.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listS;
    }

    public List<Gender> findGenderByColorId(String productID, String colorId) {
        List<Gender> listG = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct g.Gender, pd.Gender_Id FROM Product_Detail pd "
                    + "JOIN Gender g ON g.Id = pd.Gender_Id "
                    + "WHERE pd.Product_Id = ? AND pd.Color_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gender g = new Gender();
                g.setId(resultSet.getInt("Gender_Id"));
                g.setGender(resultSet.getString("Gender"));
                listG.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listG;
    }

    public List<Size> findSizeByColorAndGender(String productID, String colorId, String genderID) {
        List<Size> listS = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct s.Size, pd.Size_Id FROM Product_Detail pd "
                    + "JOIN Size s ON s.Id = pd.Size_Id "
                    + "WHERE pd.Product_Id = ? AND pd.Color_Id = ? AND pd.Gender_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, colorId);
            preparedStatement.setString(3, genderID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size s = new Size();
                s.setId(resultSet.getInt("Size_Id"));
                s.setSize(resultSet.getString("Size"));
                listS.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listS;
    }

    public List<Color> findColorByGenderAndSize(String productID, String genderID, String sizeId) {
        List<Color> listC = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT distinct c.Color, pd.Color_Id FROM Product_Detail pd "
                    + "JOIN Color c ON c.Id = pd.Color_Id "
                    + "WHERE pd.Product_Id = ? AND pd.Gender_Id = ? AND pd.Size_Id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, genderID);
            preparedStatement.setString(3, sizeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Color c = new Color();
                c.setId(resultSet.getInt("Color_Id"));
                c.setColor(resultSet.getString("Color"));
                listC.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listC;
    }

//    public void addProductToCart(int accountID, Product_Form pf) {
//        try {
//            connection = this.getConnection();
//
//            String sql = "INSERT INTO [dbo].[Cart] ([Account_Id], [Address], [CartCode]) VALUES (?, ?, ?)";
//            preparedStatement = connection.prepareStatement(sql);
//
//            // Set parameters
//            preparedStatement.setString(1, account.getEmail());
//            preparedStatement.setString(2, password);
//            preparedStatement.setInt(3, account.getRole_Id());
//
//            // Execute the update
//            int affectedRows = preparedStatement.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
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

    public int changePassword(String password, int accountId) {
        try {
            connection = this.getConnection();
            String sql = "UPDATE Account SET Password = ? WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, accountId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public Account_Form editAccountDetails(Account_Form af) {
        try {
            connection = this.getConnection();
            String sql = "UPDATE Account_Detail SET Phone_Number = ?, "
                    + "Gender_Id = ?, "
                    + "Dob = ?, "
                    + "Address = ?, "
                    + "Username = ? "
                    + "WHERE Account_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, af.getPhone_number());
            preparedStatement.setInt(2, af.getGender_id());
            preparedStatement.setDate(3, (Date) af.getDob());
            preparedStatement.setString(4, af.getAddress());
            preparedStatement.setString(5, af.getUsername());
            preparedStatement.setInt(6, af.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                sql = "Select * FROM Account_Detail ad JOIN  Gender g ON ad.Gender_Id = g.Id WHERE Account_Id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, af.getId());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    af = new Account_Form();
                    af.setAddress(resultSet.getString("Address"));
                    af.setPhone_number(resultSet.getString("Phone_Number"));
                    af.setDob(resultSet.getDate("Dob"));
                    af.setGender(resultSet.getString("Gender"));
                    af.setGender_id(resultSet.getInt("Gender_Id"));
                    af.setUsername(resultSet.getString("Username"));
                    return af;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account_Form findProfileById(Account_Form account) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Account_Detail ad LEFT JOIN Gender g ON ad.Gender_Id = g.id WHERE Account_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getId());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account_Form af = new Account_Form();
                af.setAddress(resultSet.getString("Address"));
                af.setPhone_number(resultSet.getString("Phone_Number"));
                af.setDob(resultSet.getDate("Dob"));
                af.setGender(resultSet.getString("Gender"));
                af.setGender_id(resultSet.getInt("Gender_Id"));
                af.setUsername(resultSet.getString("Username"));
                return af;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkExistOfAccountDetail(Account_Form af) {
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Account_Detail ad WHERE ad.Account_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, af.getId());

            resultSet = preparedStatement.executeQuery();

            // Check if resultSet has any rows
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account_Form insertAccountDetails(Account_Form af) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Account_Detail "
                    + "(Phone_Number, Gender_Id, Dob, Address, Username, Account_Id) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, af.getPhone_number());
            preparedStatement.setInt(2, af.getGender_id());
            preparedStatement.setDate(3, (Date) af.getDob());
            preparedStatement.setString(4, af.getAddress());
            preparedStatement.setString(5, af.getUsername());
            preparedStatement.setInt(6, af.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                sql = "Select * FROM Account_Detail ad LEFT JOIN Gender g ON ad.Gender_Id = g.id WHERE Account_Id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, af.getId());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    af = new Account_Form();
                    af.setAddress(resultSet.getString("Address"));
                    af.setPhone_number(resultSet.getString("Phone_Number"));
                    af.setDob(resultSet.getDate("Dob"));
                    af.setGender(resultSet.getString("Gender"));
                    af.setGender_id(resultSet.getInt("Gender_Id"));
                    af.setUsername(resultSet.getString("Username"));
                    return af;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Invoice_Form> findAllInvoiceByAccountId(Account_Form account) {
        List<Invoice_Form> listIf = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "Select p.id as productID, i.Id,i.Invoice_Date, id.[TotalPrice],i.Status_Id, i.CartCode, i.Address, id.Price, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, c.Category, b.Brand from Invoice i JOIN Invoice_Detail id ON i.Id = id.Invoice_Id \n"
                    + "	  JOIN Product p ON p.Id = id.Product_Id JOIN Category c ON c.Id = p.Category_Id JOIN Brand b ON b.Id = p.Brand_Id\n"
                    + "	  JOIN Product_Detail pd ON pd.Id = id.ProductDetail_Id \n"
                    + "	  WHERE i.Account_Id = ? \n"
                    + "	  GROUP BY p.id, i.Id, i.Invoice_Date, id.TotalPrice, i.Status_Id, i.CartCode, i.Address, id.Price, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, c.Category, b.Brand\n"
                    + "	  ORDER BY i.Invoice_Date DESC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Invoice_Form invoiceF = new Invoice_Form();
                invoiceF.setId(resultSet.getInt("Id"));
                invoiceF.setInvoice_date(resultSet.getDate("Invoice_Date"));
                invoiceF.setTotalPrice(resultSet.getFloat("TotalPrice"));
                invoiceF.setStatus_id(resultSet.getInt("Status_Id"));
                invoiceF.setCartCode(resultSet.getString("Address"));
                invoiceF.setPrice(resultSet.getFloat("Price"));
                invoiceF.setProduct_id(resultSet.getInt("productID"));
                invoiceF.setProductName(resultSet.getString("Name"));
                invoiceF.setProductImage(resultSet.getString("Image_path"));
                invoiceF.setProductCategory(resultSet.getString("Category"));
                invoiceF.setProductBrand(resultSet.getString("Brand"));
                listIf.add(invoiceF);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return listIf;
    }

    public List<Invoice_Form> getInvoiceDetails(String invoiceID, String productID) {
        List<Invoice_Form> listIf = new ArrayList<>();
        try {
            connection = this.getConnection();

            String sql = "SELECT pd.Id , id.Quantity, id.Price, id.TotalPrice, c.Color, s.Size, g.Gender, b.Brand, cate.Category FROM Invoice i JOIN Invoice_Detail id ON id.Invoice_Id = i.Id\n"
                    + "	  JOIN Product p ON p.Id = id.Product_Id JOIN Product_Detail pd ON pd.Id = id.ProductDetail_Id\n"
                    + "	  JOIN Category cate ON cate.Id = p.Category_Id JOIN Brand b ON b.Id = p.Brand_Id\n"
                    + "	  JOIN Color c ON c.Id = pd.Color_Id JOIN Size s ON s.id = pd.Size_Id JOIN Gender g ON g.Id =pd.Gender_Id\n"
                    + "	  WHERE i.Id = ? AND  p.id = ?\n"
                    + "	  GROUP BY pd.Id, id.Quantity, id.Price, id.TotalPrice, c.Color, s.Size, g.Gender, b.Brand, cate.Category";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, invoiceID);
            preparedStatement.setString(2, productID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Invoice_Form invoiceF = new Invoice_Form();
                invoiceF.setProductQuantity(resultSet.getInt("Quantity"));
                invoiceF.setPrice(resultSet.getFloat("Price"));
                invoiceF.setTotalPrice(resultSet.getFloat("TotalPrice"));
                invoiceF.setProductColor(resultSet.getString("Color"));
                invoiceF.setProductSize(resultSet.getString("Size"));
                invoiceF.setProductGender(resultSet.getString("Gender"));
                invoiceF.setProductBrand(resultSet.getString("Brand"));
                invoiceF.setProductCategory(resultSet.getString("Category"));
                listIf.add(invoiceF);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return listIf;
    }

    public int addToCart(int accountID, String address) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Cart (Account_Id, Address) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, accountID);
            preparedStatement.setString(2, address);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getProductDetail(Product_Form pf) {
        int productDetailId = 0;
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product_Detail WHERE "
                    + "Product_Id = ? AND Color_Id = ? "
                    + "AND Size_Id = ? AND Gender_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pf.getProduct_id());
            preparedStatement.setInt(2, pf.getColor_id());
            preparedStatement.setInt(3, pf.getSize_id());
            preparedStatement.setInt(4, pf.getGender_id());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                productDetailId = resultSet.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productDetailId;
    }

    public int getCartByAccId(int accountID) {
        int cartId = 0;
        try {
            connection = this.getConnection();
            String sql = "SELECT Id FROM Cart WHERE Account_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cartId = resultSet.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartId;
    }

    public int addToCartDetail(int productDetailId, int cartId, Product_Form pf) {
        try {
            connection = this.getConnection();
            String sql = "INSERT INTO Cart_Detail(Product_Detail_Id, Create_at, Quantity, Cart_Id, Product_Id) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productDetailId);
            preparedStatement.setDate(2, (Date) pf.getCreated_on());
            preparedStatement.setInt(3, pf.getQuantity());
            preparedStatement.setInt(4, cartId);
            preparedStatement.setInt(5, pf.getProduct_id());

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Product_Form> findCartByAccountId(int accountID) {
        List<Product_Form> listPf = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT c.Id as CartID, c.Account_Id, p.Image_path, p.Name, SUM(p.Price * cd.Quantity) AS TotalPrice, co.Color, s.Size, g.Gender, cd.Product_Detail_Id, cd.Product_Id, SUM(cd.Quantity) AS TotalQuantity \n"
                    + "FROM Cart c JOIN Cart_Detail cd ON cd.Cart_Id = c.Id \n"
                    + "JOIN Product p ON p.Id = cd.Product_Id\n"
                    + "JOIN Product_Detail pd ON pd.Id = cd.Product_Detail_Id\n"
                    + "JOIN Color co ON co.Id = pd.Color_Id\n"
                    + "JOIN Size s ON s.Id = pd.Size_Id\n"
                    + "JOIN Gender g ON g.Id = pd.Gender_Id\n"
                    + "WHERE c.Account_Id = ? GROUP BY  c.Id, c.Account_Id, p.image_path, cd.Product_Detail_Id, cd.Product_Id, p.Name, p.Price, co.Color, s.Size, g.Gender";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product_Form pf = new Product_Form();
                pf.setImage_path(resultSet.getString("Image_path"));
                pf.setName(resultSet.getString("Name"));
                pf.setPrice(resultSet.getFloat("TotalPrice"));
                pf.setColor(resultSet.getString("Color"));
                pf.setSize(resultSet.getString("Size"));
                pf.setGender(resultSet.getString("Gender"));
                pf.setQuantity(resultSet.getInt("TotalQuantity"));
                pf.setAccount_id(resultSet.getInt("Account_Id"));
                pf.setProduct_id(resultSet.getInt("Product_Detail_Id"));
                pf.setId(resultSet.getInt("Product_Id"));
                pf.setCart_id(resultSet.getInt("CartID"));
                listPf.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPf;
    }

    public int findTotalPriceInCart(int id) {
        int totalPrice = 0;
        try {
            connection = this.getConnection();
            String sql = "SELECT SUM(TotalPrice) AS TotalPriceAll FROM (\n"
                    + "    SELECT SUM(p.Price * cd.Quantity) AS TotalPrice FROM Cart c JOIN \n"
                    + "        Cart_Detail cd ON cd.Cart_Id = c.Id JOIN Product_Detail pd ON pd.Id = cd.Product_Detail_Id JOIN Product p ON p.Id = pd.Product_Id WHERE  c.Account_Id = ? GROUP BY cd.Product_Detail_Id\n" // Added space before GROUP BY
                    + ") AS Subquery;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalPrice = resultSet.getInt("TotalPriceAll");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    public void removeItemInCart(int accountID) {
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Cart WHERE Account_Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeItemInCartDetail(int productID, int productDetailID) {
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Cart_Detail WHERE Product_Id = ? AND Product_Detail_Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productID);
            preparedStatement.setInt(2, productDetailID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findExistCartByAccountId(int accountID) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Cart c "
                    + "WHERE c.Account_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountID);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findExistCartDetail(int cartID) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Cart_Detail cd "
                    + "WHERE cd.Cart_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cartID);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    public List<Product_Form> findByPage(int page, String sortedByPrice, String sortedByDate, String[] brandID, String[] cateID, String[] colorID, String[] sizeID, String[] genderID) {
        List<Product_Form> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT p.*, cate.*, b.*, SUM(pd.Stock) as totalStockOfEachProduct FROM Product p ");
            sqlBuilder.append("JOIN Product_Detail pd ON pd.Product_Id = p.id ");
            sqlBuilder.append("JOIN Category cate ON cate.Id = p.Category_Id ");
            sqlBuilder.append("JOIN Brand b ON b.Id = p.Brand_Id ");
            sqlBuilder.append("JOIN Color c ON c.Id = pd.Color_Id ");
            sqlBuilder.append("JOIN Size s ON s.Id = pd.Size_Id ");
            sqlBuilder.append("JOIN Gender g ON g.Id = pd.Gender_Id ");
            sqlBuilder.append("WHERE p.status = 1 ");

            if (brandID != null && brandID.length > 0) {
                sqlBuilder.append("AND b.Id IN (");
                for (int i = 0; i < brandID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < brandID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            if (cateID != null && cateID.length > 0) {
                sqlBuilder.append("AND cate.Id IN (");
                for (int i = 0; i < cateID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < cateID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            if (colorID != null && colorID.length > 0) {
                sqlBuilder.append("AND c.Id IN (");
                for (int i = 0; i < colorID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < colorID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            if (sizeID != null && sizeID.length > 0) {
                sqlBuilder.append("AND s.Id IN (");
                for (int i = 0; i < sizeID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < sizeID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            if (genderID != null && genderID.length > 0) {
                sqlBuilder.append("AND g.Id IN (");
                for (int i = 0; i < genderID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < genderID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            sqlBuilder.append("GROUP BY p.id, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, p.Price,p.Description, p.Create_on, p.Status,p.Status_date, p.Expire_date, cate.Id, cate.Category,  b.Id, b.Brand ");
            sqlBuilder.append("HAVING SUM(pd.Stock) > 0 ");

            sqlBuilder.append("ORDER BY CASE WHEN ? = 'desc' THEN p.price END DESC, "
                    + "CASE WHEN ? = 'asc' THEN p.price END ASC, "
                    + "CASE WHEN ? = 'desc' THEN p.Create_on END DESC, "
                    + "CASE WHEN ? = 'asc' THEN p.Create_on END ASC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ");

            preparedStatement = connection.prepareStatement(sqlBuilder.toString());
            int parameterIndex = 1;

            if (brandID != null && brandID.length > 0) {
                for (String brand : brandID) {
                    preparedStatement.setString(parameterIndex++, brand);
                }
            }
            if (cateID != null && cateID.length > 0) {
                for (String cate : cateID) {
                    preparedStatement.setString(parameterIndex++, cate);
                }
            }
            if (colorID != null && colorID.length > 0) {
                for (String color : colorID) {
                    preparedStatement.setString(parameterIndex++, color);
                }
            }
            if (sizeID != null && sizeID.length > 0) {
                for (String size : sizeID) {
                    preparedStatement.setString(parameterIndex++, size);
                }
            }
            if (genderID != null && genderID.length > 0) {
                for (String gender : genderID) {
                    preparedStatement.setString(parameterIndex++, gender);
                }
            }
            preparedStatement.setString(parameterIndex++, sortedByPrice);
            preparedStatement.setString(parameterIndex++, sortedByPrice);
            preparedStatement.setString(parameterIndex++, sortedByDate);
            preparedStatement.setString(parameterIndex++, sortedByDate);
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
                pf.setCategory_id(resultSet.getInt("Category_Id"));
                pf.setCategory(resultSet.getString("Category"));
                pf.setBrand_id(resultSet.getInt("Brand_Id"));
                pf.setBrand(resultSet.getString("Brand"));
                pf.setStatus(resultSet.getInt("Status"));
                productList.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public int findTotalProducts(String[] brandID, String[] cateID, String[] colorID, String[] sizeID, String[] genderID) {
        int totalProduct = 0;
        try {
            connection = this.getConnection();
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT COUNT(*) AS TotalProduct\n"
                    + "FROM (\n"
                    + "    SELECT p.id\n"
                    + "    FROM Product p\n"
                    + "    JOIN Product_Detail pd ON pd.Product_Id = p.id\n"
                    + "    JOIN Category cate ON cate.Id = p.Category_Id \n"
                    + "    JOIN Brand b ON b.Id = p.Brand_Id \n"
                    + "    JOIN Color c ON c.Id = pd.Color_Id \n"
                    + "    JOIN Size s ON s.Id = pd.Size_Id \n"
                    + "    JOIN Gender g ON g.Id = pd.Gender_Id \n"
                    + "    WHERE p.status = 1 ");

            if (brandID != null && brandID.length > 0) {
                sqlBuilder.append("AND b.Id IN (");
                for (int i = 0; i < brandID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < brandID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            if (cateID != null && cateID.length > 0) {
                sqlBuilder.append("AND cate.Id IN (");
                for (int i = 0; i < cateID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < cateID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            if (colorID != null && colorID.length > 0) {
                sqlBuilder.append("AND c.Id IN (");
                for (int i = 0; i < colorID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < colorID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            if (sizeID != null && sizeID.length > 0) {
                sqlBuilder.append("AND s.Id IN (");
                for (int i = 0; i < sizeID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < sizeID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            if (genderID != null && genderID.length > 0) {
                sqlBuilder.append("AND g.Id IN (");
                for (int i = 0; i < genderID.length; i++) {
                    sqlBuilder.append("?");
                    if (i < genderID.length - 1) {
                        sqlBuilder.append(",");
                    }
                }
                sqlBuilder.append(") ");
            }
            sqlBuilder.append("GROUP BY p.id, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, p.Price,p.Description, p.Create_on, p.Status,p.Status_date, p.Expire_date, cate.Id, cate.Category,  b.Id, b.Brand ");
            sqlBuilder.append("HAVING SUM(pd.Stock) > 0 ");
            sqlBuilder.append(") as query ");
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());
            int parameterIndex = 1;

            if (brandID != null && brandID.length > 0) {
                for (String brand : brandID) {
                    preparedStatement.setString(parameterIndex++, brand);
                }
            }
            if (cateID != null && cateID.length > 0) {
                for (String cate : cateID) {
                    preparedStatement.setString(parameterIndex++, cate);
                }
            }
            if (colorID != null && colorID.length > 0) {
                for (String color : colorID) {
                    preparedStatement.setString(parameterIndex++, color);
                }
            }
            if (sizeID != null && sizeID.length > 0) {
                for (String size : sizeID) {
                    preparedStatement.setString(parameterIndex++, size);
                }
            }
            if (genderID != null && genderID.length > 0) {
                for (String gender : genderID) {
                    preparedStatement.setString(parameterIndex++, gender);
                }
            }
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalProduct = resultSet.getInt("TotalProduct");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProduct;
    }

    public int countWomenProduct() {
        int totalProduct = 0;

        try {
            connection = this.getConnection();

            String sql = "SELECT COUNT(*) "
                    + "FROM ( "
                    + "    SELECT p.id "
                    + "    FROM Product p "
                    + "    JOIN Category c ON c.Id = p.Category_Id "
                    + "    JOIN Brand b ON b.Id = p.Brand_Id "
                    + "    JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "    WHERE p.status = 1 AND pd.Gender_Id = 2 "
                    + "    GROUP BY p.id, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, p.Price, p.Description, p.Create_on, p.Status, p.Status_date, p.Expire_date, c.Id, c.Category, b.Id, b.Brand "
                    + "    HAVING SUM(pd.Stock) > 0 "
                    + ") AS subquery";
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

    public int countMenProduct() {
        int totalProduct = 0;

        try {
            connection = this.getConnection();

            String sql = "SELECT COUNT(*) "
                    + "FROM ( "
                    + "    SELECT p.id "
                    + "    FROM Product p "
                    + "    JOIN Category c ON c.Id = p.Category_Id "
                    + "    JOIN Brand b ON b.Id = p.Brand_Id "
                    + "    JOIN Product_Detail pd ON pd.Product_Id = p.id "
                    + "    WHERE p.status = 1 AND pd.Gender_Id = 1 "
                    + "    GROUP BY p.id, p.Name, p.Image_path, p.Category_Id, p.Brand_Id, p.Price, p.Description, p.Create_on, p.Status, p.Status_date, p.Expire_date, c.Id, c.Category, b.Id, b.Brand "
                    + "    HAVING SUM(pd.Stock) > 0 "
                    + ") AS subquery";
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

    public List<Image> selectTop10DistinctImageProduct() {
        List<Image> listI = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT Distinct TOP 10 i.Product_Id, i.Image FROM Image i";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Image i = new Image();
                i.setProduct_Id(resultSet.getInt("Product_Id"));
                i.setImage(resultSet.getString("Image"));
                listI.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listI;
    }

}
