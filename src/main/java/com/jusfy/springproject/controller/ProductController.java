package com.jusfy.springproject.controller;

import com.jusfy.springproject.model.ProductResponse;
import com.jusfy.springproject.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/sample")
    public ProductResponse sampleProduct() {
        return productService.sampleProduct();
    }
}
