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

public class Account {
    private int id;
    private String email;
    private String password;
    private String member_code;
    private String verify_code;
    private int role_Id;
    
    private Account_Detail acc_det;
}
