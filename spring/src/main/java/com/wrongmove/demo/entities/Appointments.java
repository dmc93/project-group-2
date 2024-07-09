package com.wrongmove.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String surname;
    @ManyToOne
    private Property property;

    private LocalDate date;
    private String timeSlot;

    @ManyToOne
    private Buyer buyer;

    public Appointments() {
    }

    public Property getProperty() {
        return property;
    }


    public void setProperty(Property property) {
        this.property = property;
    }



    public Appointments(Integer id, String firstName, String surname, LocalDate date, String timeSlot, Buyer buyer, Property property) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.date = date;
        this.timeSlot = timeSlot;
        this.buyer = buyer;
        this.property = property;

    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
