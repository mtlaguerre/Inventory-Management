package com.mtlaguerre.inventory_management.services;

import java.util.List;

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

    public Product addProduct(Product product) throws IllegalArgumentException {

        // handle unset rm
        if (product.getRm() == null) {
            throw new IllegalArgumentException("Product is missing RM info.");
        }

        // handle unset or 0 capacity
        else if (product.getCapacity() <= 0) {
            throw new IllegalArgumentException("Product capacity must be greater than 0.");
        }

        // handle unset warehouse
        else if (product.getWarehouse() == null) {
            throw new IllegalArgumentException("Product is missing warehouse info.");
        }
        
        else {
            return productRepository.save(product);
        }
    }
}
