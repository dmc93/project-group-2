package com.wrongmove.demo.rest;


import com.wrongmove.demo.dtos.SellerDto;
import com.wrongmove.demo.entities.Seller;
import com.wrongmove.demo.services.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SellerController {

    private final SellerService service;

    public SellerController(SellerService service) {
        this.service = service;
    }

    @GetMapping("seller/get/all")
    public List<SellerDto> getAllSellers () {
        return service.getAll();
    }

    @GetMapping("seller/get/{id}")
    public ResponseEntity<?> getSellerById (@PathVariable Integer id) {
        return service.getSeller(id);
    }

    @PostMapping("seller/add")
    public ResponseEntity<SellerDto> createSeller(@RequestBody Seller newSeller){
        return service.createSeller(newSeller);
    }

    @DeleteMapping("seller/remove/{id}")
    public ResponseEntity<?> removeSeller(@PathVariable Integer id) {
        return service.removeSeller(id);
    }

}
