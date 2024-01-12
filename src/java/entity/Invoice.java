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
public class Invoice {
    private int id;
    private int account_detail_id;
    private Date created_date;
    private float total_price;

    public Invoice() {
    }

    public Invoice(int id, int account_detail_id, Date created_date, float total_price) {
        this.id = id;
        this.account_detail_id = account_detail_id;
        this.created_date = created_date;
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_detail_id() {
        return account_detail_id;
    }

    public void setAccount_detail_id(int account_detail_id) {
        this.account_detail_id = account_detail_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }
    
    
}
