package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.DiscountDTO;
import com.example.projectmrsisa.model.Discount;
import com.example.projectmrsisa.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping(value="/updatePercentage")
    @PreAuthorize("hasAnyRole('mainAdmin','admin')")
    public ResponseEntity<DiscountDTO> createNewDiscount(@RequestBody DiscountDTO discountDTO){
        List<Discount> discounts;
        Discount discount;
        try {
            discounts = discountService.findAll();
            discountService.endDiscount(new Date(), discounts.get(discounts.size() - 1).getId());
            discountDTO.setDiscount(discountDTO.getDiscount()*0.01);
            discount = new Discount(discountDTO);
            discountService.save(discount);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DiscountDTO discountDTO1 = new DiscountDTO(discount);
        return new ResponseEntity<>(discountDTO1, HttpStatus.OK);
    }

    public Date getDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(date);
        } catch(ParseException e) {
            return new Date();
        }
    }
}
