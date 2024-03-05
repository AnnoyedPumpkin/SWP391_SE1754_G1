/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import constant.Constant;
import context.DBContext;
import entity.Account;
import entity.Account_Detail;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Gender;
import entity.Image;
import entity.Invoice;
import entity.Invoice_Form;
import entity.Product_Form;
import entity.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.AccountDetailUM;
import model.ProductVM;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Win 10
 */
public class SellerDao extends DBContext {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    BCrypt bcryp = new BCrypt();

    public Account checkExistOfSeller(Account account) {
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

    /**
     * Method description: Check account seller existed or net
     *
     * @param account - account to be checked
     * @return
     */
    public boolean CheckSellerAccount(Account account) {
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

    public boolean createAccountSeller(Account account) {
        //Ma hoa mat khau khi them vao db
        String password = bcryp.hashpw(account.getPassword(), bcryp.gensalt());
        try {
            connection = this.getConnection();

            String sql = "INSERT INTO [dbo].[Account] ([Email], [Password], [Member_code], [Role_Id]) VALUES (?, ?, ?, 3)";
            preparedStatement = connection.prepareStatement(sql);

            // Set parameters
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, account.getMember_code()); // Cài đặt giá trị cho cột Member_code

            // Execute the update
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account_Detail getAccountDetailByAccountId(int accountId) {
        try {
            connection = this.getConnection();

            String sql = "SELECT * FROM Account_Detail WHERE Account_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account_Detail accountDetail = new Account_Detail();
                accountDetail.setId(resultSet.getInt("Id"));
                accountDetail.setUserName(resultSet.getString("Username"));
                accountDetail.setAccount_id(resultSet.getInt("Account_Id"));
                accountDetail.setPhone_number(resultSet.getString("Phone_Number"));
                accountDetail.setGender(resultSet.getBoolean("Gender"));
                accountDetail.setDob(resultSet.getDate("Dob"));
                accountDetail.setAddress(resultSet.getString("Address"));
                return accountDetail;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Method description: Edit user profile
     *
     * @param accountDetailUM - account to be update
     * @return
     */
    public AccountDetailUM editProfile(AccountDetailUM accountDetailUM) {
        try {
            connection = this.getConnection();
            String sql = "UPDATE Account_Detail SET Phone_Number = ?, "
                    + "Gender = ?, "
                    + "Dob = ?, "
                    + "Address = ?, "
                    + "Username = ? "
                    + "WHERE Account_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountDetailUM.getPhone_number());
            preparedStatement.setBoolean(2, accountDetailUM.isGender());
            preparedStatement.setString(3, accountDetailUM.getDob());
            preparedStatement.setString(4, accountDetailUM.getAddress());
            preparedStatement.setString(5, accountDetailUM.getUserName());
            preparedStatement.setInt(6, accountDetailUM.getAccount_id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                sql = "Select * FROM Account_Detail ad JOIN  Account a ON ad.Account_Id = a.Id WHERE Account_Id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, accountDetailUM.getAccount_id());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    accountDetailUM = new AccountDetailUM();
                    accountDetailUM.setAddress(resultSet.getString("Address"));
                    accountDetailUM.setPhone_number(resultSet.getString("Phone_Number"));
                    accountDetailUM.setDob(resultSet.getString("Dob"));
                    accountDetailUM.setGender(resultSet.getBoolean("Gender"));
                    accountDetailUM.setUserName(resultSet.getString("Username"));
                    accountDetailUM.setEmail(resultSet.getString("Email"));

                    return accountDetailUM;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Method description: Change seller password
     *
     * @param password - Password that seller wanna change
     * @param accountId - accountId seller
     * @return
     */
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
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
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

    /**
     * Method description: Paging for product
     *
     * @param index
     * @return
     */
    public List<ProductVM> getListProductPaging(int index) {
        try {
            List<ProductVM> listProduct = new ArrayList();
            connection = this.getConnection();
            String sql = "SELECT  p.Id, p.Name, p.Create_on, p.Description, p.Price, im.Image "
                    + "from dbo.Product p JOIN dbo.Image im ON im.Product_Id = p.Id  "
                    + "Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (index - 1) * 8);
//            preparedStatement.setInt(1, accountId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductVM productVM = new ProductVM();
                productVM.setImageLink(resultSet.getString("Image"));
                productVM.setName(resultSet.getString("Name"));
                productVM.setDescription(resultSet.getString("Description"));
                productVM.setCreate_on(resultSet.getDate("Create_on"));
                productVM.setPrice(resultSet.getDouble("Price"));
                productVM.setId(resultSet.getInt("Id"));
                listProduct.add(productVM);
            }
            return listProduct;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Method description: Find product by ID
     *
     * @param productID: Product ID
     * @param colorID: Color ID
     * @param categoryID: Category ID
     * @param sizeID: Size ID
     * @param brandID: Brand ID
     * @param genderID: Gender ID
     * @return
     */
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

    public int findTotalProducts() {
        int totalProduct = 0;

        try {
            connection = this.getConnection();

            String sql = "SELECT COUNT(*) FROM dbo.Product";
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

    public int findTotalProducts(String[] brandID, String[] cateID, String priceRange, String minPrice, String maxPrice, String[] colorID, String[] sizeID, String[] genderID) {
        int totalProduct = 0;

        try {
            connection = this.getConnection();

            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Product p ");
            sql.append("LEFT JOIN Product_Detail pd ON pd.Product_Id = p.id ");

            if (brandID != null && brandID.length > 0) {
                sql.append("WHERE pd.Brand_Id IN (");
                for (int i = 0; i < brandID.length; i++) {
                    sql.append("?");
                    if (i < brandID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }
            if (cateID != null && cateID.length > 0) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND pd.Category_Id IN (");
                } else {
                    sql.append("WHERE pd.Category_Id IN (");
                }
                for (int i = 0; i < cateID.length; i++) {
                    sql.append("?");
                    if (i < cateID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }
            if (colorID != null && colorID.length > 0) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND pd.Color_Id IN (");
                } else {
                    sql.append("WHERE pd.Color_Id IN (");
                }
                for (int i = 0; i < colorID.length; i++) {
                    sql.append("?");
                    if (i < colorID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }
            if (sizeID != null && sizeID.length > 0) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND pd.Size_Id IN (");
                } else {
                    sql.append("WHERE pd.Size_Id IN (");
                }
                for (int i = 0; i < sizeID.length; i++) {
                    sql.append("?");
                    if (i < sizeID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }
            if (genderID != null && genderID.length > 0) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND pd.Gender_Id IN (");
                } else {
                    sql.append("WHERE pd.Gender_Id IN (");
                }
                for (int i = 0; i < genderID.length; i++) {
                    sql.append("?");
                    if (i < genderID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }

            if (priceRange == null || priceRange.isEmpty()) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND p.price BETWEEN 0 AND 1000000 ");
                } else {
                    sql.append("WHERE p.price BETWEEN 0 AND 1000000 ");
                }
            } else {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND p.price BETWEEN ? AND ? ");
                } else {
                    sql.append("WHERE p.price BETWEEN ? AND ? ");
                }
            }

            preparedStatement = connection.prepareStatement(sql.toString());
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

    public List<Product_Form> findByPage(int page, String sorted, String[] brandID, String[] cateID, String priceRange, String minPrice, String maxPrice, String[] colorID, String[] sizeID, String[] genderID) {
        List<Product_Form> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            StringBuilder sql = new StringBuilder("SELECT * FROM Product p ");
            sql.append("LEFT JOIN Product_Detail pd ON pd.Product_Id = p.id ");
            sql.append("LEFT JOIN Color c ON pd.Color_Id = c.Id ");
            sql.append("LEFT JOIN Category cate ON pd.Category_Id = cate.id ");
            sql.append("LEFT JOIN Size s ON pd.Size_Id = s.Id ");
            sql.append("LEFT JOIN Brand b ON pd.Brand_Id = b.Id ");
            sql.append("LEFT JOIN Gender g ON pd.Gender_Id = g.Id ");

            if (brandID != null && brandID.length > 0) {
                sql.append("WHERE pd.Brand_Id IN (");
                for (int i = 0; i < brandID.length; i++) {
                    sql.append("?");
                    if (i < brandID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }
            if (cateID != null && cateID.length > 0) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND pd.Category_Id IN (");
                } else {
                    sql.append("WHERE pd.Category_Id IN (");
                }
                for (int i = 0; i < cateID.length; i++) {
                    sql.append("?");
                    if (i < cateID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }
            if (colorID != null && colorID.length > 0) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND pd.Color_Id IN (");
                } else {
                    sql.append("WHERE pd.Color_Id IN (");
                }
                for (int i = 0; i < colorID.length; i++) {
                    sql.append("?");
                    if (i < colorID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }
            if (sizeID != null && sizeID.length > 0) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND pd.Size_Id IN (");
                } else {
                    sql.append("WHERE pd.Size_Id IN (");
                }
                for (int i = 0; i < sizeID.length; i++) {
                    sql.append("?");
                    if (i < sizeID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }
            if (genderID != null && genderID.length > 0) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND pd.Gender_Id IN (");
                } else {
                    sql.append("WHERE pd.Gender_Id IN (");
                }
                for (int i = 0; i < genderID.length; i++) {
                    sql.append("?");
                    if (i < genderID.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") ");
            }

            if (priceRange == null || priceRange.isEmpty()) {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND p.price BETWEEN 0 AND 1000000 ");
                } else {
                    sql.append("WHERE p.price BETWEEN 0 AND 1000000 ");
                }
            } else {
                if (sql.toString().contains("WHERE")) {
                    sql.append("AND p.price BETWEEN ? AND ? ");
                } else {
                    sql.append("WHERE p.price BETWEEN ? AND ? ");
                }
            }

            sql.append("ORDER BY CASE WHEN ? = 'desc' THEN p.price END DESC, CASE WHEN ? = 'asc' THEN p.price END ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

            preparedStatement = connection.prepareStatement(sql.toString());
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

    public List<Product_Form> findByPage(int page) {
        List<Product_Form> productList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "SELECT * FROM Product p "
                    + "LEFT JOIN Product_Detail pd ON pd.Product_Id = p.id "
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

    public List<Invoice_Form> findTotalInvoice() {
        List<Invoice_Form> listIf = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "Select i.ID, i.CartCode, i.Invoice_Date, ad.Username, a.Email, i.[address], i.Total_Price, ist.[Status] "
                    + "from Invoice i left join Account a on i.Account_Id = a.ID "
                    + "left join Account_Detail ad on a.ID = ad.Account_ID "
                    + "left join Invoice_Status ist on i.[Status_Id] = ist.ID";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice_Form invoiceForm = new Invoice_Form();
                invoiceForm.setId(resultSet.getInt("ID"));
                invoiceForm.setCartCode(resultSet.getInt("CartCode"));
                invoiceForm.setInvoice_Date(resultSet.getDate("Invoice_Date"));
                invoiceForm.setUsername(resultSet.getString("Username"));
                invoiceForm.setEmail(resultSet.getString("Email"));
                invoiceForm.setAddress(resultSet.getString("address"));
                invoiceForm.setTotalPrice(resultSet.getDouble("Total_Price"));
                invoiceForm.setStatus(resultSet.getString("Status"));
                listIf.add(invoiceForm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listIf;
    }

    public int countTotalInvoice() {
        int totalInvoice = 0;

        try {
            connection = this.getConnection();

            String sql = "SELECT Count (*) FROM [Invoice]";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalInvoice = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalInvoice;
    }

    public List<Invoice_Form> getListInvoiceDetailByID(int invoiceID) {
        List<Invoice_Form> listIf = new ArrayList<>();
        try {
            connection = this.getConnection();
            String sql = "Select i.ID, i.CartCode, p.Name, id.Quantity, id.TotalPrice, i.Invoice_Date, ad.Username, a.Email, i.[address], i.Total_Price \n"
                    + "  from Invoice i join Account a on i.Account_Id = a.ID \n"
                    + "  join Invoice_Detail id on i.ID = id.Invoice_Id\n"
                    + "  join Product p on id.product_Id = p.id\n"
                    + "  join Account_Detail ad on a.ID = ad.Account_ID \n"
                    + "  Where i.ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,invoiceID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice_Form invoiceForm = new Invoice_Form();
                invoiceForm.setId(resultSet.getInt("ID"));
                invoiceForm.setCartCode(resultSet.getInt("CartCode"));
                invoiceForm.setInvoice_Date(resultSet.getDate("Invoice_Date"));
                invoiceForm.setUsername(resultSet.getString("Username"));
                invoiceForm.setEmail(resultSet.getString("Email"));
                invoiceForm.setAddress(resultSet.getString("address"));
                invoiceForm.setTotalPrice(resultSet.getDouble("Total_Price"));
                invoiceForm.setProductName(resultSet.getString("Name"));
                invoiceForm.setProductPrice(resultSet.getDouble("TotalPrice"));
                invoiceForm.setProductQuantity(resultSet.getInt("Quantity"));
                listIf.add(invoiceForm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listIf;
    }
    
    public Invoice_Form getInvoiceDetailByID(int invoiceID) {
        try {
            connection = this.getConnection();
            String sql = "Select i.ID, i.CartCode, p.Name, id.Quantity, id.TotalPrice, i.Invoice_Date, ad.Username, a.Email, i.[address], i.Total_Price, ad.Phone_Number \n"
                    + "  from Invoice i join Account a on i.Account_Id = a.ID \n"
                    + "  join Invoice_Detail id on i.ID = id.Invoice_Id\n"
                    + "  join Product p on id.product_Id = p.id\n"
                    + "  join Account_Detail ad on a.ID = ad.Account_ID \n"
                    + "  Where i.ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,invoiceID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice_Form invoiceForm = new Invoice_Form();
                invoiceForm.setId(resultSet.getInt("ID"));
                invoiceForm.setCartCode(resultSet.getInt("CartCode"));
                invoiceForm.setInvoice_Date(resultSet.getDate("Invoice_Date"));
                invoiceForm.setUsername(resultSet.getString("Username"));
                invoiceForm.setEmail(resultSet.getString("Email"));
                invoiceForm.setAddress(resultSet.getString("address"));
                invoiceForm.setTotalPrice(resultSet.getDouble("Total_Price"));
                invoiceForm.setProductName(resultSet.getString("Name"));
                invoiceForm.setProductPrice(resultSet.getDouble("TotalPrice"));
                invoiceForm.setProductQuantity(resultSet.getInt("Quantity"));
                invoiceForm.setPhoneNumber(resultSet.getString("Phone_Number"));
                return invoiceForm;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
