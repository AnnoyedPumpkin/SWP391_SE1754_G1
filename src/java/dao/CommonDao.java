/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author admin
 */
public class CommonDao extends DBContext {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Account CheckExistOfAcc(Account account) {
        try {
            connection = this.getConnection();

            String sql = "SELECT a.Email, a.Password "
                    + "FROM Account a "
                    + "WHERE a.Email = ? AND a.Password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account foundAccount = new Account();
                foundAccount.setEmail(resultSet.getString("email"));
                foundAccount.setPassword(resultSet.getString("password"));
                return foundAccount;
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

    public boolean CreateAccount(Account account) {
        try {
            connection = this.getConnection();

            String sql = "INSERT INTO [dbo].[Account] ([Email], [Password]) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0; // Trả về true nếu có bản ghi được chèn thành công, ngược lại trả về false

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updatePassword(Account account) {
        try {
            String sql = "UPDATE account SET password = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setInt(2, account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            /* Đóng các tài nguyên, đóng kết nối */
        }
    }

//    public Account_Detail getAccountDetailByEmail(String email) {
//        try {
//            String sql = "SELECT * FROM account_detail WHERE Username = ?";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, email);
//
//            // Thực hiện truy vấn
//            resultSet = preparedStatement.executeQuery();
//
//            // Kiểm tra xem có dữ liệu trả về không
//            if (resultSet.next()) {
//                // Đọc thông tin từ ResultSet và tạo đối tượng Account_Detail
//                Account_Detail accountDetail = new Account_Detail();
//                accountDetail.setId(resultSet.getInt("id"));
//                accountDetail.setAccount_id(resultSet.getInt("account_id"));
//                accountDetail.setUsername(resultSet.getString("username"));
//                accountDetail.setPhone_number(resultSet.getFloat("phone_number"));
//                accountDetail.setGender(resultSet.getBoolean("gender"));
//                accountDetail.setDob(resultSet.getDate("dob"));
//                accountDetail.setAddress(resultSet.getString("address"));
//                accountDetail.setType(resultSet.getInt("type"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Xử lý hoặc ghi log lỗi
//        } finally {
//            // Đóng tất cả các tài nguyên (ResultSet, PreparedStatement, Connection)
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Account_Detail accountDetail = null;
//
//        return accountDetail;
//    }
//
}