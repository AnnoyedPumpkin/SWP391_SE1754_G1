/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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

public class Invoice_Detail {
    private int id;
    private int invoice_Id;
    private int product_Id;
    private int quantity;
    private double price;
    private double total_price;
}
