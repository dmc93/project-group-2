package com.wrongmove.demo.dtos;

import com.wrongmove.demo.entities.Appointments;
import com.wrongmove.demo.entities.Property;
import com.wrongmove.demo.entities.Buyer;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class AppointmentsDto {

    private Integer id;

    private String firstName;
    private String surname;

    private LocalDate date;
    private String timeSlot;

    private Integer buyerId;
    private Integer propertyId;



    public AppointmentsDto() {
        super();
    }


    //    object constructor
    public AppointmentsDto(Appointments appointment) {
        this.id = appointment.getId();
        this.firstName = appointment.getFirstName();
        this.surname = appointment.getSurname();

        this.date = appointment.getDate();
        this.timeSlot = appointment.getTimeSlot();
        if (appointment.getBuyer() != null) this.buyerId = appointment.getBuyer().getId();
        if (appointment.getProperty() != null)this.propertyId = appointment.getProperty().getId();
    }

    //details


    // Getters and Setters
    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
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


}
