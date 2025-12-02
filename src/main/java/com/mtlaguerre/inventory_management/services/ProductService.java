package com.mtlaguerre.inventory_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mtlaguerre.inventory_management.models.Product;
import com.mtlaguerre.inventory_management.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // constructor injection for our beans
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductByName(String name) throws IllegalArgumentException {
        Optional<List<Product>> products =  productRepository.findByName(name);

        if (products.isPresent()) {
            return products.get();      // returns object from the optional
        }
        else {
            throw new IllegalArgumentException("No product with that name.");
        }
    }
}
