package com.jusfy.springproject.controller;

import com.jusfy.springproject.model.ProductResponse;
import com.jusfy.springproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService service;

    @Test
    void getSample_shouldReturnJson() throws Exception {
        given(service.sampleProduct()).willReturn(
            new ProductResponse(1L, "TasteTrip Burger", new BigDecimal("149.90"), "STANDARD")
        );

        mockMvc.perform(get("/products/sample"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("TasteTrip Burger"))
            .andExpect(jsonPath("$.price").value(149.90))
            .andExpect(jsonPath("$.category").value("STANDARD"));
    }
}
