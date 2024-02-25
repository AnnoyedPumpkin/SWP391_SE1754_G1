/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import constant.Constant;
import context.DBContext;
import entity.Account_Detail;
import entity.Brand;
import entity.Category;
import entity.Color;
import entity.Gender;
import entity.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.CartCM;
import model.ProductVM;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Admin
 */
public class ProductDao extends DBContext {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    CartCM cartCM = null;
    BCrypt bcryp = new BCrypt();

    public int checkUserAlreadyHaveCart(int userId) throws SQLException {
        try {
            connection = this.getConnection();
            int cartId = 0;
            String sql = "Select * FROM dbo.[Cart] Where Account_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cartId = resultSet.getInt("Id");
                return cartId;
            } else {
                System.out.println("Khong co ton tai account nay trogn cart");
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getProductQuantityInCartDetail(int productId, int cartId) {
        try {
            connection = this.getConnection();
            int currentQuantity = 0;
            String sql = "Select * FROM dbo.[Cart_Detail] Where Product_Id = ? AND Cart_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, cartId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                currentQuantity = resultSet.getInt("Quantity");
                System.out.println("Product Id" + productId + "Quantity" + currentQuantity);
                return currentQuantity;
            }
        } catch (Exception e) {
            System.out.println("Cannot get table cartDetail by productId");
        }
        return 0;
    }

    // goi cai ham nay 
    public int addProductToCart(int productId, int quantity, Account_Detail accountDetail, String address, String cartCode, int DiscountId) {
        try {
            connection = this.getConnection();
            // check user da co cart chua.
            int userId = accountDetail.getAccount_id();
            System.out.println("UserId " + userId);
            int newProductQuantity = 0;
            Date currentDateTime = new Date();
            int cartId = checkUserAlreadyHaveCart(userId);
            System.out.println("Cart" + cartId);
            if (cartId == 0) {
                System.out.println("Cart khong co nen vao day tao cart");
                // phai kiem tra o cho nay db co van de thiet ke. Viec insert vao bang phai dam bao data phai co o bang Discount;
                String sql = "INSERT INTO dbo.[Cart] (Account_Id, Address, CartCode, Discount_Id) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                // Tao tam thoi. O day co the tao 1 discoundId default voi gia tri la discount la 0.
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, cartCode);
                preparedStatement.setInt(4, DiscountId);
                int affectedRow = preparedStatement.executeUpdate();
                if (affectedRow > 0) {
                    System.out.println("Create new Cart");
                    // then set vao cart id
                    sql = "Select * From dbo.[Cart] WHERE Account_Id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, userId);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        cartId = resultSet.getInt("Id");
                    }
                } else {
                    System.out.println("Cannot create new cart");
                }
            }
            // check product da co trong cart hay chua
            int currentQuantity = getProductQuantityInCartDetail(productId, cartId);
            if (currentQuantity == 0) {
                // chua co thi insert vao
                String sql = "INSERT INTO dbo.[Cart_Detail] (Product_Id, Create_at, Quantity, Cart_Id) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                // Tao tam thoi. O day co the tao 1 discoundId default voi gia tri la discount la 0. 
                preparedStatement.setInt(1, productId);
                preparedStatement.setObject(2, LocalDateTime.now());
                preparedStatement.setInt(3, quantity);
                preparedStatement.setInt(4, cartId);

                int affectedRow = preparedStatement.executeUpdate();
                if (affectedRow > 0) {
                    return 1;
                } else {
                    System.out.println("Cannot insert into cartdetail with new product");
                }
            } else {
                // co san trong db roi thi update quantity lai thoi/
                currentQuantity += quantity;
                String sql = "UPDATE dbo.[Cart_Detail] SET Create_at = ?, Quantity = ? WHERE Product_Id = ? ";
                preparedStatement = connection.prepareStatement(sql);
                // Tao tam thoi. O day co the tao 1 discoundId default voi gia tri la discount la 0. 
                preparedStatement.setObject(1, LocalDateTime.now());
                preparedStatement.setInt(2, currentQuantity);
                preparedStatement.setInt(3, productId);
                int affectedRow = preparedStatement.executeUpdate();
                if (affectedRow > 0) {
                    return 1;
                } else {
                    System.out.println("Cannot insert into cartdetail with product with new quantity");
                }
            }
            //
            connection.close();
        } catch (Exception e) {
            System.out.println("addProductToCart " + e.getMessage());
        }
        return 0;
    }

    public List<Gender> getGender() {
        try {
            connection = this.getConnection();
            List<Gender> listGender = new ArrayList();
            String sql = "Select * FROM dbo.[Gender]";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gender gender = new Gender();
                gender.setGender(resultSet.getString("Gender"));
                gender.setId(resultSet.getInt("Id"));
                listGender.add(gender);
            }
            return listGender;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> getCategory() {
        try {
            connection = this.getConnection();
            List<Category> listGender = new ArrayList();
            String sql = "Select * FROM dbo.[Category]";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategory(resultSet.getString("Category"));
                category.setId(resultSet.getInt("Id"));
                listGender.add(category);
            }
            return listGender;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Color> getColor() {
        try {
            connection = this.getConnection();
            List<Color> listGender = new ArrayList();
            String sql = "Select * FROM dbo.[Color]";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Color gender = new Color();
                gender.setColor(resultSet.getString("Color"));
                gender.setId(resultSet.getInt("Id"));
                listGender.add(gender);
            }
            return listGender;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Brand> getBrand() {
        try {
            connection = this.getConnection();
            List<Brand> listGender = new ArrayList();
            String sql = "Select * FROM dbo.[Brand]";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand gender = new Brand();
                gender.setBrand(resultSet.getString("Brand"));
                gender.setId(resultSet.getInt("Id"));
                listGender.add(gender);
            }
            return listGender;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Size> getSize() {
        try {
            connection = this.getConnection();
            List<Size> listGender = new ArrayList();
            String sql = "Select * FROM dbo.[Size]";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Size gender = new Size();
                gender.setSize(resultSet.getString("Size"));
                gender.setId(resultSet.getInt("Id"));
                listGender.add(gender);
            }
            return listGender;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProductVM> getListProductFilterTotal(String colorId, String categoryId, String brandId, String sizeId, String genderId) {
        try {
            List<ProductVM> listProduct = new ArrayList();
            System.out.println("color: " + colorId.isEmpty() + "-Cate: " + categoryId.isEmpty() + "-Brand: "
                    + brandId.isEmpty() + "-Gender: " + genderId.isEmpty() + "-size: " + sizeId.isEmpty());
            connection = this.getConnection();
            String sql = "SELECT  p.Id, p.Name, p.Create_on, p.Description, p.Price, im.Image "
                    + "from dbo.Product p JOIN dbo.Image im ON im.Product_Id = p.Id JOIN dbo.[Product_Detail] pd ON p.Id = pd.Product_Id ";
            preparedStatement = connection.prepareStatement(sql);

            if (!colorId.isEmpty() && !categoryId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Brand_Id = ? "
                        + "AND pd.Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, brandId);
                preparedStatement.setString(4, sizeId);
                preparedStatement.setString(5, genderId);
            } else if (!categoryId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Brand_Id = ? "
                        + "AND Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, sizeId);
                preparedStatement.setString(4, genderId);
            } else if (!colorId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Brand_Id = ? "
                        + "AND Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, sizeId);
                preparedStatement.setString(4, genderId);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, sizeId);
                preparedStatement.setString(4, genderId);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !brandId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Brand_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, brandId);
                preparedStatement.setString(4, genderId);
            } else // 4 options.
            if (!colorId.isEmpty() && !categoryId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Brand_Id = ? AND pd.Size_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, brandId);
                preparedStatement.setString(4, sizeId);
            } else if (!categoryId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Size_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, sizeId);
                preparedStatement.setString(3, genderId);
            } else if (!categoryId.isEmpty() && !brandId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Brand_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, genderId);
            } else if (!categoryId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Brand_Id = ? "
                        + "AND Size_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, sizeId);
            } else if (!colorId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Size_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, sizeId);
                preparedStatement.setString(3, genderId);
            } else if (!colorId.isEmpty() && !brandId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Brand_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, genderId);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, genderId);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Size_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, sizeId);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !brandId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Brand_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, brandId);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty()) {
                System.out.println("Vao day la dung roi cua color voi category");
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
            } else if (!colorId.isEmpty() && !brandId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Brand_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, brandId);
            } else if (!colorId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Size_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, sizeId);
            } else if (!colorId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, genderId);
            } else if (!categoryId.isEmpty() && !brandId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Brand_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, brandId);
            } else if (!categoryId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Size_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, sizeId);
            } else if (!categoryId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, genderId);
            } else if (!brandId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Brand_Id = ? AND pd.Size_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, brandId);
                preparedStatement.setString(2, sizeId);
            } else if (!brandId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Brand_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, brandId);
                preparedStatement.setString(2, genderId);
            } else if (!sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, sizeId);
                preparedStatement.setString(2, genderId);
            } else if (!colorId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
            } else if (!categoryId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
            } else if (!brandId.isEmpty()) {
                sql += " WHERE pd.Brand_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, brandId);
            } else if (!sizeId.isEmpty()) {
                sql += " WHERE pd.Size_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, sizeId);
            } else if (!genderId.isEmpty()) {
                sql += " WHERE pd.Gender_Id = ? Order By Create_on Desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,  genderId );
            }
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

    public List<ProductVM> getListProductPaging(int index, String colorId, String categoryId, String brandId, String sizeId, String genderId) {
        try {
            List<ProductVM> listProduct = new ArrayList();
            connection = this.getConnection();
            String sql = "SELECT  p.Id, p.Name, p.Create_on, p.Description, p.Price, im.Image "
                    + "from dbo.Product p JOIN dbo.Image im ON im.Product_Id = p.Id JOIN dbo.[Product_Detail] pd ON p.Id = pd.Product_Id ";
            preparedStatement = connection.prepareStatement(sql);
            if (colorId.isEmpty() && categoryId.isEmpty() && brandId.isEmpty() && sizeId.isEmpty() && genderId.isEmpty()) {
                sql += " Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, (index - 1) * 8);
            }

            if (!colorId.isEmpty() && !categoryId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? AND pd.Brand_Id = ? "
                        + "AND pd.Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, brandId);
                preparedStatement.setString(4, sizeId);
                preparedStatement.setString(5, genderId);
                preparedStatement.setInt(6, (index - 1) * 8);
            } else if (!categoryId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Brand_Id = ? "
                        + "AND Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, sizeId);
                preparedStatement.setString(4, genderId);
                preparedStatement.setInt(5, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Brand_Id = ? "
                        + "AND Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, sizeId);
                preparedStatement.setString(4, genderId);
                preparedStatement.setInt(5, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, sizeId);
                preparedStatement.setString(4, genderId);
                preparedStatement.setInt(5, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !brandId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Brand_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, brandId);
                preparedStatement.setString(4, genderId);
                preparedStatement.setInt(5, (index - 1) * 8);
            } else // 4 options.
            if (!colorId.isEmpty() && !categoryId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Brand_Id = ? AND pd.Size_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, brandId);
                preparedStatement.setString(4, sizeId);
                preparedStatement.setInt(5, (index - 1) * 8);
            } else if (!categoryId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Size_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, sizeId);
                preparedStatement.setString(3, genderId);
                preparedStatement.setInt(4, (index - 1) * 8);
            } else if (!categoryId.isEmpty() && !brandId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Brand_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, genderId);
                preparedStatement.setInt(4, (index - 1) * 8);
            } else if (!categoryId.isEmpty() && !brandId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Brand_Id = ? "
                        + "AND Size_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, sizeId);
                preparedStatement.setInt(4, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Size_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, sizeId);
                preparedStatement.setString(3, genderId);
                preparedStatement.setInt(4, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !brandId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Brand_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setString(3, genderId);
                preparedStatement.setInt(4, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, genderId);
                preparedStatement.setInt(4, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Size_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, sizeId);
                preparedStatement.setInt(4, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty() && !brandId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? "
                        + "AND Brand_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY ";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setString(3, brandId);
                preparedStatement.setInt(4, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !categoryId.isEmpty()) {
                System.out.println("Vao day la dung roi cua color voi category");
                sql += " WHERE pd.Color_Id = ? AND pd.Category_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, categoryId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !brandId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Brand_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Size_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, sizeId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!colorId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setString(2, genderId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!categoryId.isEmpty() && !brandId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Brand_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, brandId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!categoryId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Size_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, sizeId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!categoryId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setString(2, genderId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!brandId.isEmpty() && !sizeId.isEmpty()) {
                sql += " WHERE pd.Brand_Id = ? AND pd.Size_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, brandId);
                preparedStatement.setString(2, sizeId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!brandId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Brand_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, brandId);
                preparedStatement.setString(2, genderId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!sizeId.isEmpty() && !genderId.isEmpty()) {
                sql += " WHERE pd.Size_Id = ? AND pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, sizeId);
                preparedStatement.setString(2, genderId);
                preparedStatement.setInt(3, (index - 1) * 8);
            } else if (!colorId.isEmpty()) {
                sql += " WHERE pd.Color_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, colorId);
                preparedStatement.setInt(2, (index - 1) * 8);
            } else if (!categoryId.isEmpty()) {
                sql += " WHERE pd.Category_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, categoryId);
                preparedStatement.setInt(2, (index - 1) * 8);
            } else if (!brandId.isEmpty()) {
                sql += " WHERE pd.Brand_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, brandId);
                preparedStatement.setInt(2, (index - 1) * 8);
            } else if (!sizeId.isEmpty()) {
                sql += " WHERE pd.Size_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, sizeId);
                preparedStatement.setInt(2, (index - 1) * 8);
            } else if (!genderId.isEmpty()) {
                sql += " WHERE pd.Gender_Id = ? Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, genderId);
                preparedStatement.setInt(2, (index - 1) * 8);
            }
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
}
