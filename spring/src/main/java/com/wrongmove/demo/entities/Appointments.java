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
    private Integer propertyId;
    private LocalDate date;
    private String timeSlot;

    @ManyToOne
    private Buyer buyer;


    public Appointments() {
    }


    public Appointments(Integer id,  String firstName, String surname, Integer propertyId, LocalDate date, String timeSlot) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.propertyId = propertyId;
        this.date = date;
        this.timeSlot = timeSlot;
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

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
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
