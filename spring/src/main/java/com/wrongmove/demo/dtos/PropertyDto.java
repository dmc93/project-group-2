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
    private boolean hasGarden;
    private String imageUrl;

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
    }

    public PropertyDto(String imageUrl, boolean hasGarden, int bathrooms, int bedrooms, String city, String address, Integer id) {
        this.imageUrl = imageUrl;
        this.hasGarden = hasGarden;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.city = city;
        this.address = address;
        this.id = id;
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

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

