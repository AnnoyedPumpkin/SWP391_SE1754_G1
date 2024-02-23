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

public class Invoice {
    private int id;
    private int account_id;
    private Date invoice_Date;
    private float total_price;
    private int status_Id;
    private String cartCode;
    
}
