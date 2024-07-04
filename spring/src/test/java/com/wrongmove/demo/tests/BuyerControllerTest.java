package com.wrongmove.demo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrongmove.demo.entities.Buyer;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema.sql", "classpath:buyer-data.sql"})
public class BuyerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateBuyer() throws Exception {

        Buyer newBuyer = new Buyer("Jane", "Doe");
        String newBuyerAsJson = objectMapper.writeValueAsString(newBuyer);


        mockMvc.perform(MockMvcRequestBuilders.post("/buyer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newBuyerAsJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Doe"));
    }


}
