package com.wrongmove.demo.rest;

import com.wrongmove.demo.dtos.AppointmentsDto;
import com.wrongmove.demo.entities.Appointments;
import com.wrongmove.demo.services.AppointmentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public List<AppointmentsDto> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return this.service.get(id);
    }

    @PostMapping("/create")
    public AppointmentsDto createAppointment(@RequestBody Appointments appointment) {
        return this.service.createAppointment(appointment).getBody();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Integer id,
                                               @RequestParam(required = false) String firstName,
                                               @RequestParam(required = false) String surname,
                                               @RequestParam(required = false) Integer propertyId,
                                               @RequestParam(required = false) LocalDate date,
                                               @RequestParam(required = false) String timeslot,
                                               @RequestParam(required = false) Integer buyerId) {
        return this.service.updateAppointment(id, firstName, surname, propertyId, date, timeslot, buyerId);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeAppointment(@PathVariable Integer id) {
        return this.service.removeAppointment(id);
    }
}
