package com.feirapp.fairService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feirapp.fairService.entity.Fair;
import com.feirapp.fairService.repository.FairRepository;
import com.feirapp.fairService.service.FairService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FairController.class)
class FairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FairService fairService;

    @Mock
    private FairRepository fairRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Fair fair;

    List<Fair> fairs;

    @BeforeEach
    void setUp(){
        fair = new Fair(100, "test", "test", "test", "test", 32.53, 43.43);
        fairs = Arrays.asList(
                new Fair(100, "test", "test", "test", "test", 32.53, 43.43),
                new Fair(100, "test", "test", "test", "test", 32.53, 43.43));
    }

    @Test
    void shouldAddFairAndReturnOk() throws Exception {
        mockMvc.perform(post("/addFair")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fair)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFairs() throws Exception {

        Mockito.when(fairService.getFairs()).thenReturn(fairs);
        mockMvc.perform(MockMvcRequestBuilders.get("/fairs"))
                .andExpect(status().isOk());
    }

    @Test
    //@Disabled
    void deleteFair() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFairsByWeekday() throws Exception {

        Mockito.when(fairService.getFairByWeekDay("Domingo")).thenReturn(fairs);
        mockMvc.perform(MockMvcRequestBuilders.get("/fairs/{weekday}", "Domingo"))
                .andExpect(status().isOk());
    }

}