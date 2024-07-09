package com.wrongmove.demo.rest;

import com.wrongmove.demo.dtos.AppointmentsDto;
import com.wrongmove.demo.entities.Appointments;
import com.wrongmove.demo.services.AppointmentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    private final AppointmentsService service;

    public AppointmentsController(AppointmentsService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<AppointmentsDto> getAllAppointments() {
        return service.getAllAppointments();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AppointmentsDto> getAppointmentById(@PathVariable Integer id) {
        return service.getAppointmentById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentsDto> createAppointment(@RequestBody Appointments appointmentDto) {
        return service.createAppointment(appointmentDto);
    }



    @DeleteMapping("/remove/{id}")
    public ResponseEntity<AppointmentsDto> removeAppointment(@PathVariable Integer id) {
        return service.removeAppointment(id);
    }
}
