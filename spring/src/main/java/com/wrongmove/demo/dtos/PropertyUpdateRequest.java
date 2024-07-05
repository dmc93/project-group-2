package com.wrongmove.demo.dtos;

public class PropertyUpdateRequest {
    private String street;
    private String town;
    private Integer bedrooms;
    private Integer bathrooms;
    private String garden;
    private String state;
    private Integer price;
    private String imageUrl;

    public PropertyUpdateRequest() {
    }

    public PropertyUpdateRequest(String street, String town, Integer bedrooms, Integer bathrooms, String garden, String state, Integer price, String imageUrl) {
        this.street = street;
        this.town = town;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.garden = garden;
        this.state = state;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
