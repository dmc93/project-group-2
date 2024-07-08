package com.wrongmove.demo.dtos;
//comment for Sana

import com.wrongmove.demo.entities.Appointments;
import com.wrongmove.demo.entities.Buyer;

import java.util.ArrayList;
import java.util.List;

public class BuyerDto {

    private Integer id;
    private String firstname;
    private String surname;
    private List<AppointmentsDto> appointments = new ArrayList<>();


    public BuyerDto(Buyer buyer) {
        this.id = buyer.getId();
        this.firstname = buyer.getFirstname();
        this.surname = buyer.getSurname();
        if (buyer.getAppointments() != null) {
            for (Appointments appointment : buyer.getAppointments()) {
                this.appointments.add(new AppointmentsDto(appointment));
            }
        }
    }
    public BuyerDto(List<AppointmentsDto> appointments) {
        super();
    }

    public Integer getId() {
        return id;
    }

    public BuyerDto(Integer id, String firstname, String surname) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
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

    public List<AppointmentsDto> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentsDto> appointments) {
        this.appointments = appointments;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
