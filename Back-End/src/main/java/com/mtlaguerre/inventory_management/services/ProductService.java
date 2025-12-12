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

    public Product findProductById(long productId) throws IllegalArgumentException {
        Optional<Product> optProduct = productRepository.findById(productId);   // attempt to find product by id

        // if product found
        if (optProduct.isPresent()) {
            return optProduct.get();                        // return found product
        } else {
            throw new IllegalArgumentException("Product was not found.");   // custom exception message
        }
    }

    public Product updateProduct(long productId, Product product) {
        Product oldProduct = findProductById(productId);    // soon-to-be-changed (old) values of product

        Product updatedProduct = oldProduct;        // copy old values

        // handle changed rm
        if (product.getRm() != null && product.getRm() != oldProduct.getRm()) {
            updatedProduct.setRm(product.getRm());
        }

        // handle changed capacity
        if (product.getCapacity() != oldProduct.getCapacity()) {
            updatedProduct.setCapacity(product.getCapacity());
        }

        // handle changed warehouse
        if (product.getWarehouse() != null && product.getWarehouse() != oldProduct.getWarehouse()) {
            updatedProduct.setWarehouse(product.getWarehouse());
        }

        return productRepository.save(updatedProduct);     // confirm changes
    }
}
