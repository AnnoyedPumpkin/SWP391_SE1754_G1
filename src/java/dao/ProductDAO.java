/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account_Detail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import model.CartCM;
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
                String sql = "INSERT INTO dbo.[Cart] (Account_Id, Address, CartCode) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                // Tao tam thoi. O day co the tao 1 discoundId default voi gia tri la discount la 0.
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, cartCode);
                int affectedRow = preparedStatement.executeUpdate();
                if (affectedRow > 0) {
                    System.out.println("Create new Cart");
                    // then set vao cart id
                    sql = "Select * From dbo.[Cart] WHERE Account_Id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, userId);
                    resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()) {
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
                String sql = "INSERT INTO dbo.[Cart_Detail] (Product_Id, Create_at, Quantity, Cart_Id, Discount_Id) VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                // Tao tam thoi. O day co the tao 1 discoundId default voi gia tri la discount la 0. 
                preparedStatement.setInt(1, productId);
                preparedStatement.setObject(2, LocalDateTime.now());
                preparedStatement.setInt(3, quantity);
                preparedStatement.setInt(4, cartId);   
                preparedStatement.setInt(5, DiscountId);

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
}
