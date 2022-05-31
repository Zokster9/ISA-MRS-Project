package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    @Modifying
    @Query("update Discount d set d.toDate=?1 where d.id=?2")
    public void endDiscount(Date date, Integer id);
}
