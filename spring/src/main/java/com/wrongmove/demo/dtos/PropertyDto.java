package com.wrongmove.demo.dtos;

import com.wrongmove.demo.entities.Property;

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

    public PropertyDto(){

    }

    public PropertyDto(Property property){
        this.imageUrl = property.getImageUrl();
        this.garden = property.isHasGarden();
        this.bathrooms = property.getBathrooms();
        this.bedrooms = property.getBedrooms();
        this.town = property.getTown();
        this.street = property.getStreet();
        this.id = property.getId();
        this.state = property.getState();
        this.price = property.getPrice();
    }

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

    public String getGarden() {
        return garden;
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

