package com.wrongmove.demo.repos;

import com.wrongmove.demo.entities.Buyer;
import com.wrongmove.demo.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SellerRepo extends JpaRepository<Seller, Integer> {
}
