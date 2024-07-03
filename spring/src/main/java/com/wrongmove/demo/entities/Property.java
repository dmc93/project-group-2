package com.wrongmove.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;
    private String city;
    private int bedrooms;
    private int bathrooms;
    private String hasGarden;
    private String imageUrl;
    private String state;
    private int price;

    public Property(){

    }

    public Property(int price, String state, String imageUrl, String hasGarden, int bathrooms, int bedrooms, String city, String address, Integer id) {
        this.price = price;
        this.state = state;
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
