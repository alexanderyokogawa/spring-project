package com.jusfy.springproject.service;

import com.jusfy.springproject.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ProductService {

    public ProductResponse sampleProduct() {
        BigDecimal price = new BigDecimal("149.90").setScale(2, RoundingMode.HALF_UP);

        String category = classifyByPrice(price);

        return new ProductResponse(
            1L,
            "TasteTrip Burger",
            price,
            category
        );
    }

    String classifyByPrice(BigDecimal price) {
        if (price == null) throw new IllegalArgumentException("price cannot be null");

        return switch (price) {
            case BigDecimal p when p.compareTo(BigDecimal.ZERO) == 0 -> "FREE";
            case BigDecimal p when p.compareTo(new BigDecimal("50.00")) < 0 -> "CHEAP";
            case BigDecimal p when p.compareTo(new BigDecimal("150.00")) < 0 -> "STANDARD";
            case BigDecimal p when p.compareTo(new BigDecimal("300.00")) < 0 -> "PREMIUM";
            default -> "LUXURY";
        };
    }
}
