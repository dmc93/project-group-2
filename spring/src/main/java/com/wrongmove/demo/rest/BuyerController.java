package com.wrongmove.demo.rest;


import com.wrongmove.demo.dtos.BuyerDto;
import com.wrongmove.demo.entities.Buyer;
import com.wrongmove.demo.services.BuyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BuyerController {

    private final BuyerService service;

    public BuyerController(BuyerService service) {
        this.service = service;
    }

    @GetMapping("buyer/get/all")
    public List<BuyerDto> getAllBuyers () {
        return service.getAll();
    }

    @GetMapping("buyer/get/{id}")
    public ResponseEntity<?> getBuyerById (@PathVariable Integer id) {
        return service.getBuyer(id);
    }

    @PostMapping("buyer/add")
    public ResponseEntity<BuyerDto> createBuyer(@RequestBody Buyer newBuyer){
        return service.createBuyer(newBuyer);
    }

    @DeleteMapping("buyer/remove/{id}")
    public ResponseEntity<?> removeBuyer(@PathVariable Integer id) {
        return service.removeBuyer(id);
    }

    }
