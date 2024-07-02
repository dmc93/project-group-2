package com.wrongmove.demo.dtos;

import com.wrongmove.demo.entities.Appointments;

import java.time.LocalDate;


public class AppointmentsDto {

    private Integer bookingId;
    private Integer buyerId;
    private String firstName;
    private String surname;
    private Integer propertyId;
    private LocalDate date;
    private String timeslot;


    public AppointmentsDto() {
    }
    public AppointmentsDto(Appointments appointment) {
        this.bookingId = appointment.getBookingId();
        this.buyerId = appointment.getBuyerId();
        this.firstName = appointment.getFirstName();
        this.surname = appointment.getSurname();
        this.propertyId = appointment.getPropertyId();
        this.date = appointment.getDate();
        this.timeslot= appointment.getTimeslot();
    }


    public AppointmentsDto(Integer bookingId, Integer buyerId, String firstName, String surname, Integer propertyId, LocalDate date, String timeslot) {
        this.bookingId = bookingId;
        this.buyerId = buyerId;
        this.firstName = firstName;
        this.surname = surname;
        this.propertyId = propertyId;
        this.date = date;
        this.timeslot = timeslot;

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

    public void setTime(String timeslot) {
        this.timeslot = timeslot;
    }

}
