package com.wrongmove.demo.services;

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

//    public ResponseEntity<?> removeProperty(int id){
//        Optional<Property> found = this.repo.findById(id);
//        if (found.isEmpty()) {
//            return new ResponseEntity<>("No Property found with ID " + id, HttpStatus.NOT_FOUND);
//        }
//        this.repo.deleteById(id);
//        return ResponseEntity.ok("Property with ID " + id +" has been deleted");
//    }

    public ResponseEntity<?> updateProperty(Integer id,
                                   String street,
                                   String town,
                                   Integer bedrooms,
                                   Integer bathrooms,
                                   String garden,
                                   String state,
                                   Integer price,
                                   String imageUrl){

        Optional<Property> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("No Property found with ID " + id, HttpStatus.NOT_FOUND);
        }

        Property toUpdate = found.get();

        if (street != null) toUpdate.setStreet(street);
        if (town != null) toUpdate.setTown(town);
        if (bedrooms != null) toUpdate.setBedrooms(bedrooms);
        if (bathrooms != null) toUpdate.setBathrooms(bathrooms);
        if (garden != null) toUpdate.setGarden(garden);
        if (state != null) toUpdate.setState(state);
        if (price != null) toUpdate.setPrice(price);
        if (imageUrl != null) toUpdate.setImageUrl(imageUrl);

        Property updated = this.repo.save(toUpdate);
        return ResponseEntity.ok(new PropertyDto(updated));


    }
}