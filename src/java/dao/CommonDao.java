/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Account_Detail;
import entity.Cart;
import entity.Cart_Detail;
import entity.Category;
import entity.Color;
import entity.Discount;
import entity.Gender;
import entity.Image;
import entity.Invoice;
import entity.Invoice_Detail;
import entity.Invoice_Status;
import entity.Product;
import entity.Product_Detail;
import entity.Size;
import java.sql.Connection;
import java.util.Date;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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
     * @param email - The email to be checked.
     * @return true if the account with the specified email exists; otherwise, false.
     */
    public boolean checkAccountExistByEmail(String email) {
        try {
            connection = this.getConnection();
            String query = "SELECT * FROM Account WHERE Email=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
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
    public String getEmailByAccountId(int accId) {
        try {
            String query = "SELECT Email FROM Account WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Email");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Methods description: Checks the existence of OTP Code by email.
     *
     * @param email - The email of account to be checked.
     * @return true if the OTP Code matched; otherwise, false.
     */
    public String getOTPByEmail(String email) {
        try {
            String query = "SELECT Verify_Code FROM Account WHERE Email=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
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
        String password = bcryp.hashpw(newPassword, bcryp.gensalt());
        String query = "UPDATE Account Set password = ? Where Id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
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
     * @param email - The Email of the account for which the OTP Code added.
     */
    public void addOTPForAccountByEmail(String code, String email) {
        String query = "UPDATE Account Set Verify_Code = ? Where Email = ?";
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

            //Schedule a task to delete the OTP associated with the given Email after a specified delay.
            scheduleTaskToDeleteOTP(email, 5);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Methods description: Schedule a task to delete the OTP Code associated with the given Email after a specified
     * delay.
     *
     * @param email - The email for which the OTP Code is to be deleted
     * @param delayInMinutes - The delay in minutes before deleting the OTP Code.
     */
    public void scheduleTaskToDeleteOTP(String email, int delayInMinutes) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            try {
                deleteOTPByEmail(email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, delayInMinutes, TimeUnit.MINUTES);
    }

    /**
     * Methods description: Delete OTP Code associated with the given Email.
     *
     * @param email - The email for which the OTP Code is to be deleted.
     * @throws SQLException - If a database access error occurs.
     */
    public void deleteOTPByEmail(String email) throws SQLException {
        String deleteQuery = "UPDATE Account SET Verify_Code  = NULL WHERE Email = ?";
        preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setString(1, email);
        preparedStatement.executeUpdate();
    }

    /**
     *
     * @param productId
     */
    public void deleteProductInCartDetailByProductId(int productId) {
        try {
            String query = "DELETE FROM Cart_Detail WHERE Product_Id = ?";
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
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
     * Method description: Generates a random Cart code with 12 characters.
     *
     * @return A character array representing the randomly generated Cart Code.
     */
    public String generateRandomCartCode() {
        String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallChars = "abcdefghijklmnopqrstuvwxyz";

        String values = capitalChars + smallChars;
        Random random = new Random();
        StringBuilder password = new StringBuilder(14);
        for (int i = 0; i < 12; i++) {
            password.append(values.charAt(random.nextInt(values.length())));
        }
        password.insert(4, '-');
        password.insert(9, '-');
        return password.toString();
    }

    /**
     * Method description: Retrieves a list of active discounts.
     *
     * @return List of active discounts.
     */
    public List<Discount> getActiveDiscountList() {
        List<Discount> DisList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String query = "Select * From Discount Where [Status] = 1";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Discount dis = Discount.builder()
                        .id(resultSet.getInt("Id"))
                        .create_at(resultSet.getDate("Create_at"))
                        .discount_percent(resultSet.getDouble("Discount_percent"))
                        .status(resultSet.getInt("Status"))
                        .build();
                DisList.add(dis);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return DisList;
    }

    /**
     * Method description: Retrieves account information based on the provided account ID.
     *
     * @param accId - The ID of the account to retrieve information for.
     * @return Account information associated with the provided account ID.
     */
    public Account getAccountInformationByAccountId(int accId) {
        try {
            connection = this.getConnection();
            String query = "SELECT a.Id AS Account_Id, a.Email, a.Member_Code, a.Role_Id, \n"
                    + "ad.Id AS Account_Detail_Id, ad.[Address],ad.Dob,ad.Gender,ad.Phone_Number,ad.Username\n"
                    + "                    FROM Account a LEFT JOIN Account_Detail ad ON a.Id=ad.Account_Id\n"
                    + "                    WHERE a.Id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account_Detail account_detail = Account_Detail.builder()
                        .id(resultSet.getInt("Account_Detail_Id"))
                        .phone_number(resultSet.getString("Phone_Number"))
                        .gender(resultSet.getBoolean("Gender"))
                        .dob(resultSet.getDate("Dob"))
                        .address(resultSet.getString("Address"))
                        .username(resultSet.getString("Username"))
                        .build();
                Account acc = Account.builder()
                        .id(resultSet.getInt("Account_Id"))
                        .email(resultSet.getString("Email"))
                        .member_code(resultSet.getString("Member_Code"))
                        .role_Id(resultSet.getInt("Role_Id"))
                        .acc_det(account_detail)
                        .build();
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method description: Retrieves the details of shopping carts associated with a specific account ID.
     *
     * @param accId The ID of the account for which shopping cart details are to be retrieved.
     * @return A list of Cart objects containing the details of the shopping carts.
     */
    public List<Cart> getShoppingCartDetailsByAccountId(int accId) {
        List<Cart> CList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String query = "  Select c.Id AS Cart_ID, c.[Address],c.Account_Id, cd.Id AS Cart_Detail_Id,cd.Quantity,cd.Product_Id,\n" +
"                    p.Id AS Product_Id,p.[Name] AS Product_Name,p.Price,pd.Id AS Product_Detail_Id,pd.Stock, \n" +
"                    co.Color, s.Size, ca.Category, g.Gender, p.[Image_path] AS Image_Path\n" +
"                    From Cart c  JOIN Cart_Detail cd ON c.Id=cd.Cart_Id\n" +
"                     JOIN Product p ON p.Id = cd.Product_Id\n" +
"                     JOIN Product_Detail pd ON  pd.Product_Id = p.Id\n" +
"                     JOIN Color co ON co.Id = pd.Color_Id\n" +
"                     JOIN Size s ON s.Id = pd.Size_Id\n" +
"                     JOIN Category ca ON ca.Id = pd.Category_Id\n" +
"                     JOIN Gender g ON g.Id = pd.Gender_Id\n" +
"                    LEFT JOIN [Image] i ON i.Product_Id = p.Id\n" +
"                    Where (c.CartCode IS NULL) AND (c.Account_Id = ?)";
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
                        .image_path(resultSet.getString("Image_Path"))
                        .build();
                Product_Detail productDetail = Product_Detail.builder()
                        .id(resultSet.getInt("Product_Detail_Id"))
                        .stock(resultSet.getInt("Stock"))
                        .build();
                Cart_Detail cartDetail = Cart_Detail.builder()
                        .product_id(resultSet.getInt("Product_Id"))
                        .quantity(resultSet.getInt("Quantity"))
                        .build();
                Cart c = Cart.builder()
                        .id(resultSet.getInt("Cart_ID"))
                        .account_id(resultSet.getInt("Account_Id"))
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
     *
     * @param invoiceId
     * @return
     */
    public List<Invoice> getInvoiceListById(int invoiceId) {
        List<Invoice> IList = new ArrayList<>();
        try {
            connection = this.getConnection();
            String query = "SELECT i.Id AS Invoice_Id, i.Account_Id, i.Invoice_Date, i.Total_Price , i.CartCode, i.[Address],\n"
                    + "id.Product_Id, id.Quantity, id.TotalPrice AS Total_Price_Per_Product, id.Id AS Invoice_Detail_Id,\n"
                    + "ist.[Status],\n"
                    + "p.[Name] AS Product_Name, p.Price AS Product_Price\n"
                    + "FROM Invoice i JOIN Invoice_Detail id ON i.Id = id.Invoice_Id\n"
                    + "               JOIN Invoice_Status ist ON i.Status_Id = ist.Id\n"
                    + "               JOIN Product p ON id.Product_Id = p.Id\n"
                    + "WHERE i.Id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, invoiceId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = Product.builder()
                        .name(resultSet.getString("Product_Name"))
                        .price(resultSet.getDouble("Product_Price"))
                        .build();
                Invoice_Status invoiceStatus = Invoice_Status.builder()
                        .status(resultSet.getString("Status"))
                        .build();
                Invoice_Detail invoiceDetail = Invoice_Detail.builder()
                        .product_Id(resultSet.getInt("Product_Id"))
                        .quantity(resultSet.getInt("Quantity"))
                        .total_price(resultSet.getDouble("Total_Price_Per_Product"))
                        .id(resultSet.getInt("Invoice_Detail_Id"))
                        .build();
                Invoice invoice = Invoice.builder()
                        .id(resultSet.getInt("Invoice_Id"))
                        .account_id(resultSet.getInt("Account_Id"))
                        .invoice_Date(resultSet.getDate("Invoice_Date"))
                        .total_price(resultSet.getDouble("Total_Price"))
                        .cartCode(resultSet.getString("CartCode"))
                        .address(resultSet.getString("Address"))
                        .in_de(invoiceDetail)
                        .in_st(invoiceStatus)
                        .pro(product)
                        .build();
                IList.add(invoice);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return IList;
    }

    /**
     * Methods description: Updates the quantity of a product in the cart detail based on the product ID.
     *
     * @param quantity - The new quantity of the product.
     * @param productId - The product ID to identify the product in the cart.
     */
    public void updateQuantityByProductId(int quantity, int productId) {
        String query = "UPDATE Cart_Detail Set quantity = ? Where Product_Id = ?";
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Methods description: Add Cart code for Cart with the given Id.
     *
     * @param cartCode - The Cart code to be add.
     * @param accId - The id of the cart for which the Cart code added.
     */
    public void addCartCodeForCartByAccountId(String cartCode, int accId) {
        String query = "UPDATE Cart Set CartCode = ? Where Account_Id = ?";
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cartCode);
            preparedStatement.setInt(2, accId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param accId
     * @param invoiceDate
     * @param totalPrice
     * @param cartCode
     * @param address
     * @param discountPercent
     */
    public void addInvoice(int accId, Date invoiceDate, double totalPrice, String cartCode, String address) {
        String query = "  INSERT INTO Invoice (Account_Id, Invoice_Date, Total_Price, CartCode, [Address], Status_Id) \n"
                + "  VALUES (?,?,?,?,?,1)";
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accId);
            preparedStatement.setDate(2, (java.sql.Date) invoiceDate);
            preparedStatement.setDouble(3, totalPrice);
            preparedStatement.setString(4, cartCode);
            preparedStatement.setString(5, address);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param cartCode
     * @return
     */
    public int getInvoiceIdByCartCode(String cartCode) {
        String query = "SELECT * FROM Invoice WHERE CartCode=?";
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cartCode);
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
     *
     * @param invoiceId
     * @param productId
     * @param quantity
     * @param unitPrice
     * @param totalPricePerProduct
     */
    public void addInvoiceDetail(int invoiceId, int productId, int quantity, double unitPrice, double totalPricePerProduct) {
        String query = "  INSERT INTO Invoice_Detail (Invoice_Id, Product_Id, Quantity, Price, TotalPrice ) \n"
                + "  VALUES (?,?,?,?,?)";
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, invoiceId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, unitPrice);
            preparedStatement.setDouble(5, totalPricePerProduct);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param cartId
     */
    public void deleteCartDetailByCartId(int cartId) {
        try {
            String query = "DELETE FROM Cart_Detail WHERE Cart_Id = ?";
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cartId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param Id
     */
    public void deleteCartById(int Id) {
        try {
            String query = "DELETE FROM Cart WHERE Id = ?";
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param stock
     * @param productId
     */
    public void updateProductDetailStock(int stock, int productId) {
        String query = "UPDATE Product_Detail Set Stock = ? Where Product_Id = ?";
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, stock);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}

class main {

    public static void main(String[] args) {
        CommonDao c = new CommonDao();
//        List<Cart> shoppingCartDetails = c.getShoppingCartDetailsByAccountId(15);
//        System.out.println(shoppingCartDetails.get(0).getS().getSize());
        // Iterate through the list and print each element
//        for (Cart cart : shoppingCartDetails) {
//            System.out.println("Cart ID: " + cart.getId());
//            System.out.println("Address: " + cart.getAddress());
//            System.out.println("Account ID: " + cart.getAccount_id());
//            System.out.println("Cart Detail ID: " + cart.getC_Det().getId());
//            System.out.println("Product ID: " + cart.getC_Det().getProduct_id());
//            System.out.println("Quantity: " + cart.getC_Det().getQuantity());
//            System.out.println("Product Name: " + cart.getP().getName());
//            System.out.println("Price: " + cart.getP().getPrice());
//            System.out.println("Product Detail ID: " + cart.getP_Det().getId());
//            System.out.println("Stock: " + cart.getP_Det().getStock());
//            System.out.println("Color: " + cart.getC().getColor());
//            System.out.println("Size: " + cart.getS().getSize());
//            System.out.println("Category: " + cart.getCate().getCategory());
//            System.out.println("Gender: " + cart.getGen().getGender());
//            System.out.println("Image Path: " + cart.getIma().getImage());
//            System.out.println("-------------------------------------------------------------");
//        }
//        List<Invoice> iv = c.getInvoiceListById(2);
//        for (Invoice invoice : iv) {
//            System.out.println("Invoice id: " + invoice.getId());
//            System.out.println("Account id: " + invoice.getAccount_id());
//            System.out.println("Invoice date: " + invoice.getInvoice_Date());
//            System.out.println("Total price: " + invoice.getTotal_price());
//            System.out.println("Address: " + invoice.getAddress());
//            System.out.println("Cart code: " + invoice.getCartCode());
//            System.out.println("Invoice detail id: " + invoice.getIn_de().getId());
//            System.out.println("Product id: " + invoice.getIn_de().getProduct_Id());
//            System.out.println("Quantity: " + invoice.getIn_de().getQuantity());
//            System.out.println("Total price: " + invoice.getIn_de().getTotal_price());
//            System.out.println("Status: " + invoice.getIn_st().getStatus());
//            System.out.println("Product name: " + invoice.getPro().getName());
//            System.out.println("Unit price: " + invoice.getPro().getPrice());
//
//            System.out.println("-------------------------------------------------------------");
//        }

    }
}
