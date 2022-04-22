package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.AddressDTO;
import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address getAddress(Address address) {
        Address a = addressRepository.getAddressByCityCountryStreet(address.getCountry(), address.getCity(), address.getStreet());
        if (a == null) return saveAddress(address);
        return a;
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
    
    public Address findAddressById(Integer id){
        return  addressRepository.findAddressById(id);
    }
}
