package com.jusfy.springproject.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest {

    private final ProductService service = new ProductService();

    @Test
    void classifyByPrice_shouldReturnStandard() {
        String category = service.classifyByPrice(new BigDecimal("149.90"));
        assertEquals("STANDARD", category);
    }

    @Test
    void classifyByPrice_shouldReturnFree() {
        assertEquals("FREE", service.classifyByPrice(BigDecimal.ZERO));
    }

    @Test
    void classifyByPrice_nullShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> service.classifyByPrice(null));
    }

    @Test
    void sampleProduct_shouldReturnExpectedPayload() {
        var resp = service.sampleProduct();
        assertEquals(1L, resp.id());
        assertEquals("TasteTrip Burger", resp.name());
        assertEquals(new BigDecimal("149.90"), resp.price());
        assertEquals("STANDARD", resp.category());
    }
}
