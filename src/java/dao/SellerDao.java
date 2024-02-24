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
import model.AccountDetailUM;
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
}
