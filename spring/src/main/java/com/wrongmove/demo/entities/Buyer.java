package com.wrongmove.demo.entities;

import jakarta.persistence.*;

@@Entity
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String firstname;
    private String surname;

    public Buyer(Integer id, String firstname, String surname) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
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
