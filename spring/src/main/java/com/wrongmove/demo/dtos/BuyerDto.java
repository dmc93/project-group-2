package com.wrongmove.demo.dtos;

import com.wrongmove.demo.entities.Buyer;

public class BuyerDto {

    private Integer id;
    private String firstname;
    private String surname;

    public BuyerDto(){

    }

    public BuyerDto(Buyer buyer) {
        this.id = buyer.getId();
        this.firstname = buyer.getFirstname();
        this.surname = buyer.getSurname();
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
