package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address findAddressById(Integer id){
        return  addressRepository.findAddressById(id);
    }
}
