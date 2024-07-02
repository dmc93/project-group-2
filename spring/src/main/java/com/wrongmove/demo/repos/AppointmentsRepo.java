package com.wrongmove.demo.repos;

import com.wrongmove.demo.entities.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepo extends JpaRepository<Appointments, Integer> {
}
