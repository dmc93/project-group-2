package com.wrongmove.demo.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class AppointmentsDto {

    private Integer id;
    private Integer buyerId;
    private String firstName;
    private String surname;
    private Integer propertyId;
    private LocalDate date;
    private String timeSlot;

    public AppointmentsDto() {
    }

    public AppointmentsDto(Integer id, Integer buyerId, String firstName, String surname, Integer propertyId, LocalDate date, String timeSlot) {
        this.id = id;
        this.buyerId = buyerId;
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
        return timeSlot;
    }

    public void setTimeslot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
