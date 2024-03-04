/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

/**
 *
 * @author LENOVO
 */
public class Constant {
    public static final int RECORD_PER_PAGE = 12;
 
    public static final String SESSION_ACCOUNT = "account";
    
    public static final String EMAIL_REGEX = "^[0-9A-Za-z_+$*-]+(?:\\."+"[0-9A-Za-z_+$*-]+)*@"+"(?:[0-9A-Za-z-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    
    public static final int ROLE_CUSTOMER = 1;
    public static final int ROLE_ADMIN = 2;
    public static final int ROLE_SELLER = 3;
}
