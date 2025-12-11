package com.mtlaguerre.inventory_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // GET /products/product?name=
    @GetMapping("product")
    public ResponseEntity<List<Product>> findProductByName(@RequestParam(name = "name", required = false) String productName) {
        List<Product> products = productService.findProductByName(productName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long productId, @RequestBody Product product) {

        Product updatedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long productId) {

        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("mesage", e.getMessage()).build();
        }
    }
}
