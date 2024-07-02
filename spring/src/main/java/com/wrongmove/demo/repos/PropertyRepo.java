package com.wrongmove.demo.repos;

import com.wrongmove.demo.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {
}
