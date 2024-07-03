package com.wrongmove.demo.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.wrongmove.demo.dtos.PropertyDto;
import com.wrongmove.demo.entities.Property;
import com.wrongmove.demo.repos.PropertyRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private PropertyRepo repo;

    public PropertyService(PropertyRepo repo){
        this.repo = repo;
    }

    public List<PropertyDto> getAll(){
        List<PropertyDto> dtos = new ArrayList<>();
        List<Property> found = this.repo.findAll();
        for (Property property : found){
            dtos.add(new PropertyDto(property));
        }
        return dtos;
    }

    public ResponseEntity<?> getProperty(Integer id) {
        Optional<Property> found = this.repo.findById(id);
        if (found.isEmpty()){
            return new ResponseEntity<>("No Properties found with ID " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new PropertyDto(found.get()));
    }

    public ResponseEntity<PropertyDto> addProperty(Property property) {
        Property created = this.repo.save(property);

        return new ResponseEntity<>(new PropertyDto(created), HttpStatus.CREATED);
    }

    public ResponseEntity<?> removeProperty(int id){
        Optional<Property> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("No Property found with ID " + id, HttpStatus.NOT_FOUND);
        }
        this.repo.deleteById(id);
        return ResponseEntity.ok("Property with ID " + id +" has been deleted");
    }

    public ResponseEntity<?> updateProperty(Integer id,
                                   String address,
                                   String city,
                                   Integer bedrooms,
                                   Integer bathrooms,
                                   String hasGarden,
                                   String state,
                                   Integer price,
                                   String imageUrl){

        Optional<Property> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("No Property found with ID " + id, HttpStatus.NOT_FOUND);
        }

        Property toUpdate = found.get();

        if (address != null) toUpdate.setAddress(address);
        if (city != null) toUpdate.setCity(city);
        if (bedrooms != null) toUpdate.setBedrooms(bedrooms);
        if (bathrooms != null) toUpdate.setBathrooms(bathrooms);
        if (hasGarden != null) toUpdate.setHasGarden(hasGarden);
        if (state != null) toUpdate.setState(state);
        if (price != null) toUpdate.setPrice(price);
        if (imageUrl != null) toUpdate.setImageUrl(imageUrl);

        Property updated = this.repo.save(toUpdate);
        return ResponseEntity.ok(new PropertyDto(updated));


    }
}