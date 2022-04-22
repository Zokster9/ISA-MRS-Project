package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select a from Address a where a.country = ?1 and a.city = ?2 and a.street = ?3")
    public Address getAddressByCityCountryStreet(String country, String city, String street);
    
    @Query("select a from Address a where a.id = ?1")
    public Address findAddressById(Integer id);
}
