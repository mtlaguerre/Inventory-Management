package com.mtlaguerre.inventory_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.models.Product;
import com.mtlaguerre.inventory_management.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.findAllProducts();

        try {
            return new ResponseEntity<>(products, HttpStatus.OK);       // return products and 200 if everything succeeds
        } catch (Exception e) {
            
            // return a 500 if any sort of exception occurs
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }
}
