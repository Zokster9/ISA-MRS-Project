package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.DiscountDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "discount", nullable = false)
    private double discount;
    @Column(name="toDate", nullable = false)
    private Date toDate;
    @Column(name="fromDate", nullable = false)
    private Date fromDate;

    public Discount(){

    }

    public Discount(Integer id, double discount, boolean isActive, Date toDate, Date fromDate){
        this.id = id;
        this.discount = discount;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public Discount(DiscountDTO discountDTO){
        this.id = discountDTO.getId();
        this.discount = discountDTO.getDiscount();
        this.toDate = discountDTO.getToDate();
        this.fromDate = discountDTO.getFromDate();
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

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
}
