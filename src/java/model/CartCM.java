/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class CartCM {
    private String cartId;
    private String productDetailId;
    private String productId;
    private Date createAt;
    private int quantity;
    private String address;
    private String cartCode;
    private int discountID;
    private int accoundId;
            
}
