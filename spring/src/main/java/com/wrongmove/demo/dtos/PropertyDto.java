package com.wrongmove.demo.dtos;

import com.wrongmove.demo.entities.Appointments;
import com.wrongmove.demo.entities.Property;
import com.wrongmove.demo.entities.Seller;

import java.util.ArrayList;
import java.util.List;

public class PropertyDto {

    private Integer id;

    private String street;
    private String town;
    private int bedrooms;
    private int bathrooms;
    private String garden;
    private String imageUrl;
    private String state;
    private int price;

    //added for one to many mapping
    private List<AppointmentsDto> appointments = new ArrayList<>();


    public PropertyDto(List<AppointmentsDto> appointments) {
        super();
    }

    //object constructor
    public PropertyDto(Property property) {
        this.imageUrl = property.getImageUrl();
        this.garden = property.getGarden();
        this.bathrooms = property.getBathrooms();
        this.bedrooms = property.getBedrooms();
        this.town = property.getTown();
        this.street = property.getStreet();
        this.id = property.getId();
        this.state = property.getState();
        this.price = property.getPrice();
        if(property.getAppointments() != null){
            for (Appointments appointment: property.getAppointments()){
                this.appointments.add(new AppointmentsDto(appointment));
            }
        }
    }

    //details
    public PropertyDto(Integer id, String street, String town, int bedrooms, int bathrooms, String garden, String imageUrl, String state, int price) {
        this.id = id;
        this.street = street;
        this.town = town;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.garden = garden;
        this.imageUrl = imageUrl;
        this.state = state;
        this.price = price;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<AppointmentsDto> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentsDto> appointments) {
        this.appointments = appointments;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

