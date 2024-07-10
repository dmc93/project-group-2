package com.wrongmove.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrongmove.demo.dtos.AppointmentsDto;
import com.wrongmove.demo.entities.Appointments;
import com.wrongmove.demo.entities.Buyer;
import com.wrongmove.demo.entities.Property;
import com.wrongmove.demo.entities.Seller;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema.sql", "classpath:appointments-data.sql","classpath:buyer-data.sql","classpath:seller-data.sql", "classpath:property-data.sql" })
public class AppointmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private void validateAppointment(Appointments appointment) {
        if (appointment.getTimeSlot() == null) {
            throw new IllegalArgumentException("Timeslot cannot be null");
        }
    }

    @Test
    void testCreateAppointment() throws Exception {
        String dateStr = "2024-07-19";
        LocalDate date = LocalDate.parse(dateStr);
        Seller seller = new Seller(1, "John", "Doe");
        Buyer buyer = new Buyer(1,"Roger","Rabbit");
        Property property = new Property(1,"Third Street", "Dunfermline", 2, 1, "Yes", "http://example.com/image3.jpg", "Withdrawn", 300000, seller);
        Appointments newAppointment = new Appointments(1,"Roger", "Rabbit",  date,"11:00-12:00", buyer, property);

        String newAppointmentAsJson = this.mapper.writeValueAsString(newAppointment);

        mvc.perform(MockMvcRequestBuilders.post("/appointments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newAppointmentAsJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Roger"))
                .andExpect(jsonPath("$.surname").value("Rabbit"))
                .andExpect(jsonPath("$.date").value("2024-07-19"))
                .andExpect(jsonPath("$.timeSlot").value("11:00-12:00"));
    }

    @Test
    void testRemoveAppointment() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/appointments/remove/3"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testRemoveAppointmentDoesNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/appointments/remove/10"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAllAppointments() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/appointments/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].firstName").value("Roger"))
                .andExpect(jsonPath("$[0].surname").value("Rabbit"))
                .andExpect(jsonPath("$[0].date").value("2024-07-19"))
                .andExpect(jsonPath("$[0].timeSlot").value("11:00-12:00"))
                .andExpect(jsonPath("$[1].firstName").value("Buggs"))
                .andExpect(jsonPath("$[1].surname").value("Bunny"))
                .andExpect(jsonPath("$[1].date").value("2024-08-15"))
                .andExpect(jsonPath("$[1].timeSlot").value("14:00-15:00"))
                .andExpect(jsonPath("$[2].firstName").value("Daffy"))
                .andExpect(jsonPath("$[2].surname").value("Duck"))
                .andExpect(jsonPath("$[2].date").value("2024-07-30"))
                .andExpect(jsonPath("$[2].timeSlot").value("16:00-17:00"));
    }

    @Test
    void testGetAppointmentById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/appointments/get/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Roger"))
                .andExpect(jsonPath("$.surname").value("Rabbit"))
                .andExpect(jsonPath("$.date").value("2024-07-19"))
                .andExpect(jsonPath("$.timeSlot").value("11:00-12:00"));
    }

    @Test
    void testGetAppointmentByIdDoesNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/appointments/get/10"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testValidateAppointmentThrowsExceptionWhenTimeSlotIsNull() {
        Appointments appointment = new Appointments();
        appointment.setTimeSlot(null);  // setting the timeSlot to null

        assertThrows(IllegalArgumentException.class, () -> {
            validateAppointment(appointment);
        });
    }
}
