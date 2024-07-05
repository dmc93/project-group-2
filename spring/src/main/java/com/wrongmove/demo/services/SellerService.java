package com.wrongmove.demo.services;

import com.wrongmove.demo.dtos.SellerDto;
import com.wrongmove.demo.entities.Seller;
import com.wrongmove.demo.repos.SellerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class SellerService {

    private final SellerRepo repo;

    public SellerService(SellerRepo repo) {
        this.repo = repo;
    }

    public List<SellerDto> getAll() {
        List<SellerDto> dtos = new ArrayList<>();
        List<Seller> found = this.repo.findAll();
        for (Seller seller : found) {
            dtos.add(new SellerDto(seller));
        }

        return dtos;
    }

    public ResponseEntity<?> getSeller(Integer id) {
        Optional<Seller> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("No seller found with id " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new SellerDto(found.get()));
    }

    public ResponseEntity<SellerDto> createSeller(Seller newSeller) {
        Seller created = this.repo.save(newSeller);

        return new ResponseEntity<>(new SellerDto(created), HttpStatus.CREATED);
    }

        public ResponseEntity<?> removeSeller(Integer id) {
        Optional<Seller> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>("No seller found with id " + id, HttpStatus.NOT_FOUND);
        }
        this.repo.deleteById(id);
        return ResponseEntity.ok("Seller with id " + id + " has been deleted.");

        }
}
