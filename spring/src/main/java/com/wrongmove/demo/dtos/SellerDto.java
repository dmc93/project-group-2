package com.wrongmove.demo.dtos;

import com.wrongmove.demo.entities.Property;
import com.wrongmove.demo.entities.Seller;


import java.util.ArrayList;
import java.util.List;

public class SellerDto {

    private Integer id;
    private String firstname;
    private String surname;
    private List<PropertyDto> properties= new ArrayList<>();


    public SellerDto(Seller seller) {
        this.id = seller.getid();
        this.firstname = seller.getFirstname();
        this.surname = seller.getSurname();
        if(seller.getProperties() != null) {
            for (Property property: seller.getProperties()){
                this.properties.add(new PropertyDto(property));
            }
        }
    }
    public SellerDto(List<PropertyDto> properties) {super();}



    public SellerDto(Integer id, String firstname, String surname) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;

    }

    public void setProperties(List<PropertyDto> properties) {
        this.properties = properties;
    }

    public List<PropertyDto> getProperties() {
        return properties;
    }

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
