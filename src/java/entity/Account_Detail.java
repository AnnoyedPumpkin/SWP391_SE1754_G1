/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Account_Detail {
    private int id;
    private int account_id;
    private String username;
    private float phone_number;
    private boolean gender;
    private Date dob;
    private String member_code;
    private String address;
    private int type;

    public Account_Detail() {
    }

    public Account_Detail(int id, int account_id, String username, float phone_number, boolean gender, Date dob, String member_code, String address, int type) {
        this.id = id;
        this.account_id = account_id;
        this.username = username;
        this.phone_number = phone_number;
        this.gender = gender;
        this.dob = dob;
        this.member_code = member_code;
        this.address = address;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(float phone_number) {
        this.phone_number = phone_number;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
