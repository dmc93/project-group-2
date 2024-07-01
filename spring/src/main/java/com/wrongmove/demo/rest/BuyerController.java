package com.wrongmove.demo.rest;


import com.wrongmove.demo.dtos.BuyerDto;
import com.wrongmove.demo.entities.Buyer;
import com.wrongmove.demo.services.BuyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuyerController {

    private final BuyerService service;

    public BuyerController(BuyerService service) {
        this.service = service;
    }

    @GetMapping("buyer/get/all")
    public List<BuyerDto> getAll () {
        return this.service.getAll();
    }

    @GetMapping("buyer/get/{id}")
    public ResponseEntity<?> get (@PathVariable Integer id) {
        return.this.service.getBuyer(id);
    }

    @PostMapping("buyer/create")
    public ResponseEntity<BuyerDto> createBuyer(@RequestBody Buyer newBuyer){
        return this.service.createBuyer(newBuyer);
    }

    @DeleteMapping('buyer/remove/{id}')
    public ResponseEntity<?> removeBuyer(@PathVariable Integer id) {
        return this.service.removeBuyer(id);
    }

    @PatchMapping("buyer/update/{id}")
    public ResponseEntity<?> updateBuyer(@PathVariable Integer id,
                                         @RequestParam(required = false) String firstname,
                                         @RequestParam(required = false) String lastname) {
        return this.service.updateBuyer(id, firstname, lastname);
    }
}
