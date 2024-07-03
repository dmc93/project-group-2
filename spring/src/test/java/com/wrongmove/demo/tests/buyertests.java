//package com.wrongmove.demo.tests;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.wrongmove.demo.entities.Buyer;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.bind.annotation.RestController;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//
//
//
//
//public class buyertests {
//    @Autowired  //in testing, this replaces constructors
//    private MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    @Test
//    public void newBuyerOkTest() {
//        Buyer toCreate = new Buyer(null,"John", "Smith");
//        String newAsJSON = this.mapper.writeValueAsString(toCreate);
//        System.out.println("Request = " + newAsJSON);
//        RequestBuilder req = MockMvcRequestBuilders.post("buyer/add").contentType(MediaType.APPLICATION_JSON).content(newAsJSON);
//        ResultMatcher checkStatus = MockMvcResultMatchers().status().isOk();
//        Buyer created = new Buyer (1, "John", "Smith");
//        String createdBuyer = this.mapper.writeValueAsString(created);
//        System.out.println("Response = " + createdBuyer);
//        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdBuyer);
//        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
//
//    }
//
//
//}
