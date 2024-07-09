package com.wrongmove.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema.sql", "classpath:seller-data.sql", "classpath:buyer-data.sql", "classpath:property-data.sql", "classpath:appointments-data.sql"})
public class PropertyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreateProperty() throws Exception {
        Seller seller = new Seller(1, "John", "Doe");
        Property newProperty = new Property(null, "Fourth Street", "London", 3, 2, "Yes", "http://example.com/image1.jpg", "For Sale", 400000, seller);


        String newPropertyAsJson = this.mapper.writeValueAsString(newProperty);


        RequestBuilder request = MockMvcRequestBuilders.post("/property/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newPropertyAsJson);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        ResultMatcher checkIdExists = MockMvcResultMatchers.jsonPath("$.id").exists();
        ResultMatcher checkStreet = MockMvcResultMatchers.jsonPath("$.street").value("Fourth Street");
        ResultMatcher checkTown = MockMvcResultMatchers.jsonPath("$.town").value("London");
        ResultMatcher checkBedrooms = MockMvcResultMatchers.jsonPath("$.bedrooms").value(3);
        ResultMatcher checkBathrooms = MockMvcResultMatchers.jsonPath("$.bathrooms").value(2);
        ResultMatcher checkGarden = MockMvcResultMatchers.jsonPath("$.garden").value("Yes");
        ResultMatcher checkImageUrl = MockMvcResultMatchers.jsonPath("$.imageUrl").value("http://example.com/image1.jpg");
        ResultMatcher checkState = MockMvcResultMatchers.jsonPath("$.state").value("For Sale");
        ResultMatcher checkPrice = MockMvcResultMatchers.jsonPath("$.price").value(400000);

        mvc.perform(request)
                .andExpect(checkStatus)
                .andExpect(checkIdExists)
                .andExpect(checkStreet)
                .andExpect(checkTown)
                .andExpect(checkBedrooms)
                .andExpect(checkBathrooms)
                .andExpect(checkGarden)
                .andExpect(checkImageUrl)
                .andExpect(checkState)
                .andExpect(checkPrice);
    }

    @Test
    void testGetAllProperties() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/property/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].street").value("First Street"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].town").value("Alloa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bedrooms").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bathrooms").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].garden").value("Yes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].imageUrl").value("http://example.com/image1.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].state").value("For Sale"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(100000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].street").value("Second Street"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].town").value("Edinburgh"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bedrooms").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bathrooms").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].garden").value("No"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].imageUrl").value("http://example.com/image2.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].state").value("For Sale"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(200000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].street").value("Third Street"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].town").value("Dunfermline"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bedrooms").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].bathrooms").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].garden").value("Yes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].imageUrl").value("http://example.com/image3.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].state").value("Withdrawn"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].price").value(300000));
    }

    @Test
    void testGetPropertyById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/property/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.street").value("Second Street"))
                .andExpect(jsonPath("$.town").value("Edinburgh"))
                .andExpect(jsonPath("$.bedrooms").value(4))
                .andExpect(jsonPath("$.bathrooms").value(3))
                .andExpect(jsonPath("$.garden").value("No"))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/image2.jpg"))
                .andExpect(jsonPath("$.state").value("For Sale"))
                .andExpect(jsonPath("$.price").value(200000));
    }

    @Test
    void testGetPropertyByIdDoesNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/property/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No Properties found with ID 4"));
    }

    @Test
    public void testUpdateProperty() throws Exception {

        String updateJson = "{"
                + "\"id\": 1,"
                + "\"street\": \"Fifth Street\","
                + "\"town\": \"Glasgow\","
                + "\"bedrooms\": 3,"
                + "\"bathrooms\": 2,"
                + "\"garden\": \"No\","
                + "\"state\": \"For Sale\","
                + "\"price\": 400000,"
                + "\"imageUrl\": \"http://example.com/new-image.jpg\""
                + "}";

        mvc.perform(MockMvcRequestBuilders.patch("/property/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Fifth Street"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.town").value("Glasgow"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bedrooms").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bathrooms").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.garden").value("No"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("For Sale"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(400000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("http://example.com/new-image.jpg"));

    }
}
