/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Invoice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.InvoiceDetailVM;
import model.InvoiceVM;

/**
 *
 * @author Admin
 */
public class OrderDao extends DBContext {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<InvoiceDetailVM> getListInvoiceDetailById(int orderId, int index) {
        try {
            List<InvoiceDetailVM> listInvoiceDetail = new ArrayList();
            connection = this.getConnection();
            String sql = "SELECT  p.Id, p.Name, p.Create_on, p.Description, im.Image,"
                    + "inv.Id, inv.Quantity, inv.Price, inv.TotalPrice "
                    + "from dbo.Product p JOIN dbo.[Invoice_Detail] inv ON p.Id = inv.Product_Id JOIN dbo.Image im ON im.Product_Id = p.Id "
                    + "Order By Create_on Desc OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (index - 1) * 5);
//            preparedStatement.setInt(1, accountId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InvoiceDetailVM invoiceDetailVM = new InvoiceDetailVM();
                invoiceDetailVM.setImageLink(resultSet.getString("Image"));                
                invoiceDetailVM.setProductId(resultSet.getInt("Id"));
                invoiceDetailVM.setPrice(resultSet.getFloat("Price"));
                invoiceDetailVM.setQuantity(resultSet.getInt("Quantity"));   
                invoiceDetailVM.setTotalPrice(resultSet.getInt("TotalPrice"));
                invoiceDetailVM.setProductName(resultSet.getString("Name"));
                listInvoiceDetail.add(invoiceDetailVM);
            }
            return listInvoiceDetail;
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

    public List<InvoiceVM> getUserInvoiceHistory(int userId, int index) {
         try {
            List<InvoiceVM> listInvoice = new ArrayList();
            connection = this.getConnection();
            String sql = "SELECT * FROM dbo.[Invoice] i JOIN dbo.[Invoice_Status] v ON i.Status_Id = v.Id WHERE Account_Id = ? order by Invoice_Date desc "
                    + " OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);            
            preparedStatement.setInt(2, (index - 1) * 5);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InvoiceVM invoiceVM = new InvoiceVM();
                invoiceVM.setAccount_id(userId);
                invoiceVM.setId(resultSet.getInt("Id"));
                invoiceVM.setCartCode(resultSet.getString("CartCode"));     
                invoiceVM.setStatus_Id(resultSet.getInt("Status_Id"));
                invoiceVM.setTotal_price(resultSet.getFloat("Total_Price"));
                invoiceVM.setInvoice_Date(resultSet.getDate("Invoice_Date"));
                invoiceVM.setStatus(resultSet.getString("Status"));
                listInvoice.add(invoiceVM);
            }
            return listInvoice;
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

    public int getUserInvoiceTotal(int userId) {
        try {
            connection = this.getConnection();
            String sql = "SELECT COUNT(*) FROM dbo.Invoice WHERE Account_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
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
    
    public int getUserInvoiceDetailTotal(int invoiceId) {
        try {
            connection = this.getConnection();
            String sql = "SELECT COUNT(*) FROM dbo.[Invoice_Detail] WHERE Invoice_Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, invoiceId);
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
}
