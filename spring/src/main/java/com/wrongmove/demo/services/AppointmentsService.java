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
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<AppointmentsDto> getAppointmentById(Integer id) {
        Optional<Appointments> optionalAppointment = repo.findById(id);
        return optionalAppointment.map(appointment -> ResponseEntity.ok(convertToDto(appointment)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<AppointmentsDto> createAppointment(AppointmentsDto appointmentDto) {
        Appointments appointment = convertToEntity(appointmentDto);
        validateAppointment(appointment);
        Appointments createdAppointment = repo.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdAppointment));
    }

    public ResponseEntity<AppointmentsDto> updateAppointment(Integer id, AppointmentsDto updatedDto) {
        Optional<Appointments> optionalAppointment = repo.findById(id);
        if (optionalAppointment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Appointments appointmentToUpdate = optionalAppointment.get();
        updateAppointmentFields(appointmentToUpdate, updatedDto);

        Appointments updatedAppointment = repo.save(appointmentToUpdate);
        return ResponseEntity.ok(convertToDto(updatedAppointment));
    }

    @Transactional
    public ResponseEntity<AppointmentsDto> removeAppointment(Integer id) {
        Optional<Appointments> optionalAppointment = repo.findById(id);
        if (optionalAppointment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Appointments removedAppointment = optionalAppointment.get();
        repo.deleteById(id);
        return ResponseEntity.ok(convertToDto(removedAppointment));
    }

    private AppointmentsDto convertToDto(Appointments appointment) {
        return new AppointmentsDto(
                appointment.getId(),
                appointment.getBuyerId(),
                appointment.getFirstName(),
                appointment.getSurname(),
                appointment.getPropertyId(),
                appointment.getDate(),
                appointment.getTimeslot()
        );
    }

    private Appointments convertToEntity(AppointmentsDto dto) {
        return new Appointments(
                dto.getId(),
                dto.getBuyerId(),
                dto.getFirstName(),
                dto.getSurname(),
                dto.getPropertyId(),
                dto.getDate(),
                dto.getTimeslot()
        );
    }

    private void validateAppointment(Appointments appointment) {
        if (appointment.getTimeslot() == null) {
            throw new IllegalArgumentException("Timeslot cannot be null");
        }
        // Additional validation logic can be added here
    }

    private void updateAppointmentFields(Appointments appointmentToUpdate, AppointmentsDto updatedDto) {
        if (updatedDto.getFirstName() != null) {
            appointmentToUpdate.setFirstName(updatedDto.getFirstName());
        }
        if (updatedDto.getSurname() != null) {
            appointmentToUpdate.setSurname(updatedDto.getSurname());
        }
        if (updatedDto.getPropertyId() != null) {
            appointmentToUpdate.setPropertyId(updatedDto.getPropertyId());
        }
        if (updatedDto.getDate() != null) {
            appointmentToUpdate.setDate(updatedDto.getDate());
        }
        if (updatedDto.getTimeslot() != null) {
            appointmentToUpdate.setTimeslot(updatedDto.getTimeslot());
        }
    }
}
