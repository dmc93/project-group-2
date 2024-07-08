package com.wrongmove.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrongmove.demo.entities.Seller;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema.sql", "classpath:seller-data.sql", "classpath:buyer-data.sql"})
public class SellerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreateSeller() throws Exception {

        Seller newSeller = new Seller("Daisy", "Duck");
        String newSellerAsJson = this.mapper.writeValueAsString(newSeller);

        RequestBuilder mockRequest = MockMvcRequestBuilders.post("/seller/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newSellerAsJson);


        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();

        mvc.perform(mockRequest)
                .andExpect(checkStatus)
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstname").value("Daisy"))
                .andExpect(jsonPath("$.surname").value("Duck"));
    }

    @Test
    void testDeleteSeller() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/seller/remove/3"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Seller with id 3 has been deleted."));
    }

    @Test
    void testDeleteSellerDoesNotExist() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/seller/remove/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No seller found with id 4"));
    }

    @Test
    void testGetAllSellers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/seller/get/all")

                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].firstname").value("Daffy"))
                .andExpect(jsonPath("$[0].surname").value("Duck"))
                .andExpect(jsonPath("$[1].firstname").value("Mickey"))
                .andExpect(jsonPath("$[1].surname").value("Mouse"))
                .andExpect(jsonPath("$[2].firstname").value("Minnie"))
                .andExpect(jsonPath("$[2].surname").value("Mouse"));
    }

    @Test
    void testGetSellerById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/seller/get/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.firstname").value("Mickey"))
                .andExpect(jsonPath("$.surname").value("Mouse"));
    }

    @Test
    void testGetSellerByIdSellerDoesNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/seller/get/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No seller found with id 4"));
    }

}
