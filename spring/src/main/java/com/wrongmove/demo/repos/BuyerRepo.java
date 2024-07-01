package com.wrongmove.demo.repos;

import com.wrongmove.demo.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BuyerRepo extends JpaRepository<Buyer, Integer> {
}
