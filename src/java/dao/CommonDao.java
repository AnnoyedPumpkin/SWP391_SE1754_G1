/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Account_Detail;
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
import model.AccountDetailUM;
import model.ProductVM;
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

            String sql = "SELECT a.Email, a.Password, a.Role_Id, a.Id "
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
     * @return true if the account with the specified email exists; otherwise,
     * false.
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
    public boolean checkOTPMatchedByEmail(String Email) {
        try {
            String query = "SELECT Verify_Code FROM Account WHERE Email=?";
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
     * Methods description: Updates the password for the specified account.
     *
     * @param newPassword - The new password to be set.
     * @param accountId - The ID of the account for which the password should be
     * updated.
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
     * Methods description: Add OTP Code for Account with the given Email, It
     * also includes a scheduled task to automatically delete the OTP code after
     * a specified delay in minutes.
     *
     * @param otp_code - The OTP Code to be add.
     * @param Email - The Email of the account for which the OTP Code added.
     */
    public void addOTPForAccountByEmail(String otp_code, String Email) {
        String query = "UPDATE Account Set Verify_Code = ? Where Email = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, otp_code);
            preparedStatement.setString(2, Email);
            preparedStatement.executeUpdate();

            //Schedule a task to delete the OTP associated with the given Email after a specified delay.
            scheduleTaskToDeleteOTP(Email, 2);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Methods description: Schedule a task to delete the OTP Code associated
     * with the given Email after a specified delay.
     *
     * @param Email - The email for which the OTP Code is to be deleted
     * @param delayInMinutes - The delay in minutes before deleting the OTP
     * Code.
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

    public int getTotalProduct() {
        try {
            connection = this.getConnection();
            String sql = "SELECT COUNT(*) FROM dbo.Product";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
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
        return 0;
    }

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
}
