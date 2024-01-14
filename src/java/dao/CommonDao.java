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
    /**
     * Function description: Checks the existence of an account by email.
     * 
     * @param Email - The email to be checked.
     * @return true if the account with the specified email exists; otherwise, false.
     */
    public boolean checkAccountExistByEmail(String Email) {
        try {
            String query = "SELECT * FROM Account WHERE Email=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, Email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    /**
     * Function description: Gets the account ID based on the email given.
     * 
     * @param email - The email for which the account ID is needed.
     * @return the account ID of the email given; otherwise, -1.
     */
    public int getAccountIdByEmail(String email) {
        try {
            String query = "SELECT id FROM Account WHERE Email=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    /**
     * Function description: Checks the existence of an account by phone number.
     * 
     * @param phone_number - The phone number to be checked.
     * @return true if the account with the specified phone number exists; otherwise, false.
     */
    public boolean checkAccountExistByPhoneNumber(String phone_number) {
        try {
            String query = "SELECT * FROM Account_Detail WHERE phone_number=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, phone_number);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    /**
     * Function description: Gets the account ID based on the phone number.
     * 
     * @param phone_number - The phone number for which the account ID is needed.
     * @return the account ID if the phone number given; otherwise, -1.
     */
    public int getAccountIdByPhoneNumber(String phone_number) {
        try {
            String query = "SELECT Account_Id FROM Account_Detail WHERE phone_number=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, phone_number);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    /**
     * Function description: Updates the password for the specified account.
     * 
     * @param newPassword - The new password to be set.
     * @param accountId - The ID of the account for which the password should be updated.
     */
    public void updatePassword(String newPassword, int accountId) {
        String query = "UPDATE Account Set password = ? Where Id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
