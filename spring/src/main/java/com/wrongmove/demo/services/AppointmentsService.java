package com.wrongmove.demo.services;

import com.wrongmove.demo.dtos.AppointmentsDto;
import com.wrongmove.demo.entities.Appointments;
import com.wrongmove.demo.repos.AppointmentsRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentsService {

    private final AppointmentsRepo repo;

    public AppointmentsService(AppointmentsRepo repo) {
        this.repo = repo;
    }

    public List<AppointmentsDto> getAll() {
        List<Appointments> found = repo.findAll();
        List<AppointmentsDto> dtos = new ArrayList<>();
        for (Appointments b : found) {
            dtos.add(new AppointmentsDto(b));
        }
        return dtos;
        
    }

    public ResponseEntity<?> get(Integer id) {
        Optional<Appointments> optionalAppointment = repo.findById(id);

        if (optionalAppointment.isEmpty()) {
            return new ResponseEntity<>("No appointment found with id: " + id, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new AppointmentsDto(optionalAppointment.get()));
    }

    public ResponseEntity<AppointmentsDto> createAppointment(Appointments appointment) {
        if (appointment.getTimeslot() == null) {
            return ResponseEntity.badRequest().body(new AppointmentsDto()); // Handle bad request if time slot is null
        }

        Appointments created = repo.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AppointmentsDto(created));
    }

    public ResponseEntity<?> updateAppointment(Integer id, String firstName, String surname, Integer propertyId, LocalDate date, String timeSlot, Integer buyerId) {
        Optional<Appointments> optionalAppointment = repo.findById(id);

        if (optionalAppointment.isEmpty()) {
            return new ResponseEntity<>("No appointment found with id: " + id, HttpStatus.NOT_FOUND);
        }


        Appointments toUpdate = optionalAppointment.get();

        if (firstName != null) {
            toUpdate.setFirstName(firstName);
        }
        if (surname != null) {
            toUpdate.setSurname(surname);
        }
        if (propertyId != null) {
            toUpdate.setPropertyId(propertyId);
        }
        if (date != null) {
            toUpdate.setDate(date);
        }
        if (timeSlot != null) {
            toUpdate.setTimeslot(timeSlot);
        }
        if (buyerId != null) {
            // Implement logic to set buyer if needed
        }


        return ResponseEntity.ok(this.repo.save(toUpdate));
    }

    @Transactional
    public ResponseEntity<?> removeAppointment(Integer id) {
        Optional<Appointments> optionalAppointment = repo.findById(id);

        if (optionalAppointment.isEmpty()) {
            return new ResponseEntity<>("No appointment found with id: " + id, HttpStatus.NOT_FOUND);
        }

        Appointments removed = optionalAppointment.get();
        repo.deleteById(id);
        return ResponseEntity.ok(new AppointmentsDto(removed));
    }
}
