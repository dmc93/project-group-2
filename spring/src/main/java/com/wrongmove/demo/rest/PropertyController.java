package com.wrongmove.demo.rest;

import com.wrongmove.demo.dtos.PropertyDto;
import com.wrongmove.demo.entities.Property;
import com.wrongmove.demo.services.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PropertyController {

    private PropertyService service;

    public PropertyController(PropertyService service){
        this.service = service;
    }

    @GetMapping("/property/getAll")
    public List<PropertyDto> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<?> getProperty(@PathVariable Integer id) {
        return this.service.getProperty(id);
    }

    @PostMapping("/property/add")
    public ResponseEntity<?> addProperty(@RequestBody Property property) {
        return this.service.addProperty(property);
    }

    @DeleteMapping("/property/remove/{id}")
    public ResponseEntity<?> removeProperty(@PathVariable int id){
        return this.service.removeProperty(id);
    }

    @PatchMapping("/property/update/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable int id,
                         @RequestParam(required = false) String address,
                         @RequestParam(required = false) String city,
                                            @RequestParam(required = false) Integer bedrooms,
                                            @RequestParam(required = false) Integer bathrooms,
                                            @RequestParam(required = false) Boolean hasGarden,
                         @RequestParam(required = false) String imageUrl){

        return this.service.updateProperty(id,address,city,bedrooms,bathrooms,hasGarden,imageUrl);
    }



}
