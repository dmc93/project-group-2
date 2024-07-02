package com.wrongmove.demo.dtos;

import com.wrongmove.demo.entities.Seller;

public class SellerDto {

    private Integer id;
    private String firstname;
    private String surname;

    public SellerDto(){

    }

    public SellerDto(Seller seller) {
        this.id = seller.getId();
        this.firstname = seller.getFirstname();
        this.surname = seller.getSurname();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
