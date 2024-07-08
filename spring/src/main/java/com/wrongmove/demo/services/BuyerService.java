package com.wrongmove.demo.services;

import com.wrongmove.demo.dtos.BuyerDto;
import com.wrongmove.demo.entities.Buyer;
import com.wrongmove.demo.repos.BuyerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class BuyerService {

    private final BuyerRepo repo;

    public BuyerService(BuyerRepo repo) {
        this.repo = repo;
    }

    public List<BuyerDto> getAll() {
        List<BuyerDto> dtos = new ArrayList<>();
        List<Buyer> found = this.repo.findAll();
        for (Buyer buyer : found) {
            dtos.add(new BuyerDto(buyer));
        }

        return dtos;
    }

    public ResponseEntity<?> getBuyer(Integer id) {
        Optional<Buyer> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("No buyer found with id " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new BuyerDto(found.get()));
    }

    public ResponseEntity<BuyerDto> createBuyer(Buyer newBuyer) {
        Buyer created = this.repo.save(newBuyer);

        return new ResponseEntity<>(new BuyerDto(created), HttpStatus.CREATED);
    }

        public ResponseEntity<?> removeBuyer(Integer id) {
        Optional<Buyer> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("No buyer found with id " + id, HttpStatus.NOT_FOUND);
        }
        this.repo.deleteById(id);
        return ResponseEntity.ok("Buyer with id " + id + " has been deleted.");
        }
}
