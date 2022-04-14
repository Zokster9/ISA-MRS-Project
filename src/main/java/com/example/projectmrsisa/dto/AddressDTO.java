package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Address;

public class AddressDTO {
    private Integer id;
    private String country;
    private String city;
    private String street;

    public AddressDTO(){

    }

    public AddressDTO(Address address){
        id = address.getId();
        country = address.getCountry();
        city = address.getCity();
        street = address.getStreet();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
