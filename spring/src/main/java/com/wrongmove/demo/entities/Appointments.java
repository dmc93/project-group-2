package com.wrongmove.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    private Integer buyerId;
    private String firstName;
    private String surname;
    private Integer propertyId;
    private LocalDate date;
    private String timeslot;


//    @ManyToOne
//    private Buyer buyer;

    public Appointments() {
    }

    public Appointments(String firstName, String surname, Integer propertyId, LocalDate date, String timeslot, Buyer buyer, Integer buyerId) {
        this.buyerId = buyerId;
        this.firstName = firstName;
        this.surname = surname;
        this.propertyId = propertyId;
        this.date = date;
        this.timeslot = timeslot;
//        this.buyer = buyer;
       
    }

    // Getters and Setters
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
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

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }


    //
//    public Buyer getBuyer() {
//        return buyer;
//    }
//
//    public void setBuyer(Buyer buyer) {
//        this.buyer = buyer;
//    }
}
