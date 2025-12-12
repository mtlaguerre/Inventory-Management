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

    // GET /products
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        
        // attempt to find all products in product table
        try {
            List<Product> products = productService.findAllProducts();  // list of products
            return new ResponseEntity<>(products, HttpStatus.OK);       // return 200 if successful
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise, return 500 and display message
        }
    }

    // POST /products
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        try {
            productService.addProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);        // return 204 if successfully added product
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();   // return 400 if invalid request and display custom message
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise, return 500 and display message
        }
    }
}
