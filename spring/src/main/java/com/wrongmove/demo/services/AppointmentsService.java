package com.wrongmove.demo.services;

import com.wrongmove.demo.dtos.AppointmentsDto;
import com.wrongmove.demo.entities.Appointments;
import com.wrongmove.demo.repos.AppointmentsRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentsService {

    private AppointmentsRepo repo;

    public AppointmentsService(AppointmentsRepo repo) {
        this.repo = repo;
    }

    public List<AppointmentsDto> getAllAppointments() {
        List<Appointments> foundAppointments = repo.findAll();
        return foundAppointments.stream()
                .map(AppointmentsDto::new)
                .collect(Collectors.toList());
    }

    public ResponseEntity<AppointmentsDto> getAppointmentById(Integer id) {
        Optional<Appointments> optionalAppointment = repo.findById(id);
        return optionalAppointment.map(appointment -> ResponseEntity.ok(new AppointmentsDto(appointment)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<AppointmentsDto> createAppointment(Appointments appointment) {
             validateAppointment(appointment);
        Appointments createdAppointment = repo.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AppointmentsDto(createdAppointment));
    }



    @Transactional
    public ResponseEntity<AppointmentsDto> removeAppointment(Integer id) {
        Optional<Appointments> optionalAppointment = repo.findById(id);
        if (optionalAppointment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Appointments removedAppointment = optionalAppointment.get();
        repo.deleteById(id);
        return ResponseEntity.ok(new AppointmentsDto(removedAppointment));
    }


    private void validateAppointment(Appointments appointment) {
        if (appointment.getTimeSlot() == null) {
            throw new IllegalArgumentException("Timeslot cannot be null");
        }

    }


}
