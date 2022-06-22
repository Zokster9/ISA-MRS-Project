package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Discount;
import com.example.projectmrsisa.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Transactional(readOnly = false)
    public void endDiscount(Date date, Integer id){
        discountRepository.endDiscount(date, id);
    }

    public List<Discount> findAll(){
        return discountRepository.findAll();
    }

    public Discount save(Discount discount){
        return discountRepository.save(discount);
    }
}
