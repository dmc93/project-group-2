package com.wrongmove.demo.dtos;

import com.wrongmove.demo.entities.Property;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PropertyDto {


    private Integer id;
    private String address;
    private String city;
    private int bedrooms;
    private int bathrooms;
    private String hasGarden;
    private String imageUrl;
    private String state;
    private int price;

    public PropertyDto(){

    }

    public PropertyDto(Property property){
        this.imageUrl = property.getImageUrl();
        this.hasGarden = property.isHasGarden();
        this.bathrooms = property.getBathrooms();
        this.bedrooms = property.getBedrooms();
        this.city = property.getCity();
        this.address = property.getAddress();
        this.id = property.getId();
        this.state = property.getState();
        this.price = property.getPrice();
    }

    public PropertyDto(Integer id, String address, String city, int bedrooms, int bathrooms, String hasGarden, String imageUrl, String state, int price) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.hasGarden = hasGarden;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(String hasGarden) {
        this.hasGarden = hasGarden;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHasGarden() {
        return hasGarden;
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

