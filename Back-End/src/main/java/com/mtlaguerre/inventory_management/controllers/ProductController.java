package com.mtlaguerre.inventory_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.models.Product;
import com.mtlaguerre.inventory_management.services.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://127.0.0.1:5500")
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
            return new ResponseEntity<>(HttpStatus.CREATED);        // return 201 if successfully added product
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();   // return 400 if invalid request and display custom message
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise, return 500 and display message
        }
    }

    // PUT /produts/id/{id}
    @PutMapping("/id/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long productId, @RequestBody Product product) {

        try {
            productService.updateProduct(productId, product);
            return new ResponseEntity<>(HttpStatus.OK);                                             // return 200 if successfully updated product
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();           // return 400 if input is invalid, with message
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise return 500
        }
    }

    // DELETE /products/id/{id}
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long productId) {

        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);                                     // return 204 if successfully updated product
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();           // return 400 if input is invalid, with message
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise return 500
        }
    }
}
