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

}
