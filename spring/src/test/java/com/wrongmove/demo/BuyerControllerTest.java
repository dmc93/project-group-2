package com.wrongmove.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrongmove.demo.entities.Buyer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema.sql", "classpath:buyer-data.sql", "classpath:seller-data.sql"})
public class BuyerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreateBuyer() throws Exception {

        Buyer newBuyer = new Buyer("Jane", "Doe");
        String newBuyerAsJson = this.mapper.writeValueAsString(newBuyer);

        RequestBuilder mockRequest = MockMvcRequestBuilders.post("/buyer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBuyerAsJson);


        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();

        mvc.perform(mockRequest)
                .andExpect(checkStatus)
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstname").value("Jane"))
                .andExpect(jsonPath("$.surname").value("Doe"));
    }

    @Test
    void testDeleteBuyer() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/buyer/remove/3"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Buyer with id 3 has been deleted."));
    }

    @Test
    void testDeleteBuyerDoesNotExist() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/buyer/remove/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No buyer found with id 4"));
    }

    @Test
    void testGetAllBuyers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/buyer/get/all")

                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[0].surname").value("Doe"))
                .andExpect(jsonPath("$[1].firstname").value("Jane"))
                .andExpect(jsonPath("$[1].surname").value("Doe"))
                .andExpect(jsonPath("$[2].firstname").value("Keir"))
                .andExpect(jsonPath("$[2].surname").value("Starmer"));
    }

    @Test
    void testGetBuyerById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/buyer/get/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.firstname").value("Jane"))
                .andExpect(jsonPath("$.surname").value("Doe"));
    }

    @Test
    void testGetBuyerByIdBuyerDoesNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/buyer/get/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No buyer found with id 4"));
    }

}
