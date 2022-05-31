package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Discount;

import java.util.Date;

public class DiscountDTO {

    private Integer id;
    private double discount;
    private Date fromDate;
    private Date toDate;

    public DiscountDTO(){

    }

    public DiscountDTO(Discount discount){
        this.id = discount.getId();
        this.discount = discount.getDiscount();
        this.fromDate = discount.getFromDate();
        this.toDate = discount.getToDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
