/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author admin
 */
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Invoice_Form {
    private int id;
    private int cartCode;
    private Date invoice_Date;
    private String username;
    private String email;
    private String address;
    private double totalPrice;
    private String status;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private String phoneNumber;
}
