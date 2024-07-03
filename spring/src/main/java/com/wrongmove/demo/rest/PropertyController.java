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

    public PropertyController(PropertyService service) {
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
    public ResponseEntity<?> removeProperty(@PathVariable int id) {
        return this.service.removeProperty(id);
    }

    @PatchMapping("/property/update/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable int id,
                                            @RequestParam(required = false) String street,
                                            @RequestParam(required = false) String town,
                                            @RequestParam(required = false) Integer bedrooms,
                                            @RequestParam(required = false) Integer bathrooms,
                                            @RequestParam(required = false) String garden,
                                            @RequestParam(required = false) String state,
                                            @RequestParam(required = false) Integer price,
                                            @RequestParam(required = false) String imageUrl) {

        return this.service.updateProperty(id, street, town, bedrooms, bathrooms, garden, state, price, imageUrl);
    }


}
