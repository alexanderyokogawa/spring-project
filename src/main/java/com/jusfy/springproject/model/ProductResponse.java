package com.jusfy.springproject.model;

import java.math.BigDecimal;

public record ProductResponse(
    long id,
    String name,
    BigDecimal price,
    String category) {
}
