/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor

/**
 *
 * @author admin
 */
public class Account_Detail {
    private int id;
    private int account_id;
    private float phone_number;
    private boolean gender;
    private Date dob;
    private String member_code;
    private String address;
    private int type;

}
