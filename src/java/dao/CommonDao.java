/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Cart;
import entity.Cart_Detail;
import entity.Category;
import entity.Color;
import entity.Gender;
import entity.Image;
import entity.Product;
import entity.Product_Detail;
import entity.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author admin
 */
public class CommonDao extends DBContext {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    BCrypt bcryp = new BCrypt();

    public Account checkExistOfAcc(Account account) {
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

            String sql = "INSERT INTO [dbo].[Account] ([Email], [Password], [Member_code], [Role_Id]) VALUES (?, ?, ?, 1)";
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

    /**
     * Methods description: Checks the existence of an account by email.
     *
     * @param Email - The email to be checked.
     * @return true if the account with the specified email exists; otherwise, false.
     */
    public boolean checkAccountExistByEmail(String Email) {
        try {
            connection = this.getConnection();
            String query = "SELECT * FROM Account WHERE Email=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Methods description: Gets the account ID based on the email given.
     *
     * @param email - The email for which the account ID is needed.
     * @return the account ID of the email given; otherwise, -1.
     */
    public int getAccountIdByEmail(String email) {
        try {
            String query = "SELECT id FROM Account WHERE Email=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    /**
     * Methods description: Checks the existence of OTP Code by email.
     *
     * @param Email - The email of account to be checked.
     * @return true if the OTP Code matched; otherwise, false.
     */
    public String getOTPByEmail(String Email) {
        try {
            String query = "SELECT Verify_Code FROM Account WHERE Email=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Verify_Code");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Methods description: Updates the password for the specified account.
     *
     * @param newPassword - The new password to be set.
     * @param accountId - The ID of the account for which the password should be updated.
     */
    public void updatePasswordById(String newPassword, int accountId) {
        String query = "UPDATE Account Set password = ? Where Id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Methods description: Add OTP Code for Account with the given Email, It also includes a scheduled task to
     * automatically delete the OTP code after a specified delay in minutes.
     *
     * @param code - The OTP Code to be add.
     * @param Email - The Email of the account for which the OTP Code added.
     */
    public void addOTPForAccountByEmail(String code, String Email) {
        String query = "UPDATE Account Set Verify_Code = ? Where Email = ?";
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, Email);
            preparedStatement.executeUpdate();

            //Schedule a task to delete the OTP associated with the given Email after a specified delay.
            scheduleTaskToDeleteOTP(Email, 2);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Methods description: Schedule a task to delete the OTP Code associated with the given Email after a specified
     * delay.
     *
     * @param Email - The email for which the OTP Code is to be deleted
     * @param delayInMinutes - The delay in minutes before deleting the OTP Code.
     */
    public void scheduleTaskToDeleteOTP(String Email, int delayInMinutes) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            try {
                deleteOTPByEmail(Email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, delayInMinutes, TimeUnit.MINUTES);
    }

    /**
     * Methods description: Delete OTP Code associated with the given Email.
     *
     * @param Email - The email for which the OTP Code is to be deleted.
     * @throws SQLException - If a database access error occurs.
     */
    public void deleteOTPByEmail(String Email) throws SQLException {
        String deleteQuery = "UPDATE Account SET Verify_Code  = NULL WHERE Email = ?";
        preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setString(1, Email);
        preparedStatement.executeUpdate();
    }

    /**
     * Method description: Generates a random password with 8 characters.
     *
     * @return A String representing the randomly generated password.
     */
    public String generateRandomPassword() {
        String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*?)";

        String values = capitalChars + smallChars + numbers + symbols;

        Random random = new Random();

        StringBuilder password = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            password.append(values.charAt(random.nextInt(values.length())));
        }

        return password.toString();
    }

    /**
     * Method description: Generates a random OTP Code with 6 characters.
     *
     * @return A character array representing the randomly generated OTP Code.
     */
    public String generateRandomOTP() {
        int otpLength = 6;
        String digits = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);

        for (int i = 0; i < otpLength; i++) {
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }

        return otp.toString();
    }

    /**
     * Method description:  Retrieves the details of shopping carts associated with a specific account ID.
     *
     * @param accId The ID of the account for which shopping cart details are to be retrieved.
     * @return A list of Cart objects containing the details of the shopping carts.
     */
    public List<Cart> getShoppingCartDetailsByAccountId(int accId) {
        List<Cart> CList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String query = "Select c.Id AS Cart_ID, c.Discount_Id AS Discount_Id_Of_Cart, c.CartCode, c.[Address], c.Account_Id, cd.Product_Detail_Id,cd.Quantity,p.[Name] AS Product_Name, p.Price, pd.Discount_Id AS Discount_Id_Of_Product, \n"
                    + "pd.Stock, co.Color, s.Size, ca.Category, g.Gender, i.[Image] AS Image_Path\n"
                    + "From Cart c JOIN Cart_Detail cd ON c.Id=cd.Cart_Id\n"
                    + "JOIN Product_Detail pd ON cd.Product_Detail_Id = pd.Id\n"
                    + "JOIN Product p ON p.Id = pd.Product_Id\n"
                    + "JOIN Color co ON co.Id = pd.Color_Id\n"
                    + "JOIN Size s ON s.Id = pd.Size_Id\n"
                    + "JOIN Category ca ON ca.Id = pd.Category_Id\n"
                    + "JOIN Gender g ON g.Id = pd.Gender_Id\n"
                    + "LEFT JOIN [Image] i ON i.Product_Id = p.Id\n"
                    + "WHERE (c.CartCode IS NULL) AND (c.Account_Id = ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Color color = Color.builder()
                        .color(resultSet.getString("Color"))
                        .build();
                Size size = Size.builder()
                        .size(resultSet.getString("Size"))
                        .build();
                Category category = Category.builder()
                        .category(resultSet.getString("Category"))
                        .build();
                Gender gender = Gender.builder()
                        .gender(resultSet.getString("Gender"))
                        .build();
                Image image = Image.builder()
                        .image(resultSet.getString("Image_Path"))
                        .build();
                Product product = Product.builder()
                        .name(resultSet.getString("Product_Name"))
                        .price(resultSet.getDouble("Price"))
                        .build();
                Product_Detail productDetail = Product_Detail.builder()
                        .discount_id(resultSet.getInt("Discount_Id_Of_Product"))
                        .stock(resultSet.getInt("Stock"))
                        .build();
                Cart_Detail cartDetail = Cart_Detail.builder()
                        .product_detail_id(resultSet.getInt("Product_Detail_Id"))
                        .quantity(resultSet.getInt("Quantity"))
                        .build();
                Cart c = Cart.builder()
                        .id(resultSet.getInt("Cart_ID"))
                        .discount_id(resultSet.getInt("Discount_Id_Of_Cart"))
                        .account_id(resultSet.getInt("Account_Id"))
                        .cart_code(resultSet.getString("CartCode"))
                        .address(resultSet.getString("Address"))
                        .c_Det(cartDetail)
                        .p_Det(productDetail)
                        .p(product)
                        .c(color)
                        .cate(category)
                        .s(size)
                        .gen(gender)
                        .ima(image)
                        .build();
                CList.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return CList;
    }
    /**
     * Methods description: 
     * 
     * @param quantity
     * @param id 
     */
    public void updateQuantity(int quantity, int id) {
        String query = "UPDATE Cart_Detail Set quantity = ? Where id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

class main {

    public static void main(String[] args) {
        CommonDao c = new CommonDao();
        List<Cart> cartList = c.getShoppingCartDetailsByAccountId(6);
        int count = 0;
        for (Cart cart : cartList) {
            System.out.println("Cart ID: " + cart.getId());
            System.out.println("Discount ID of Cart: " + cart.getDiscount_id());
            System.out.println("Account ID: " + cart.getAccount_id());
            System.out.println("Cart Code: " + cart.getCart_code());

            Product_Detail productDetail = cart.getP_Det();
            System.out.println("Discount Id of Product: " + productDetail.getDiscount_id());
            // Accessing CartDetail fields
            Cart_Detail cartDetail = cart.getC_Det();
            System.out.println("Product Detail ID: " + cartDetail.getProduct_detail_id());
            System.out.println("Quantity: " + cartDetail.getQuantity());

            Product p = cart.getP();
            System.out.println("Product Name: " + p.getName());
            System.out.println("Price: " + p.getPrice());
            count++;
        }
        System.out.println("-----------------------------------");
        System.out.println(count);
    }
}
