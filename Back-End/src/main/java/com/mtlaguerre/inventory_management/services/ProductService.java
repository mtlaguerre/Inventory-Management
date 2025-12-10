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

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(long id, Product product) {

        Product oldProduct = findProductById(id);
        Product updatedProduct = oldProduct;

        if (updatedProduct != null && product.getRm() != (updatedProduct.getRm())) {
            updatedProduct.setRm(product.getRm());
        }
        if (updatedProduct != null && !product.getName().equals(updatedProduct.getName())) {
            updatedProduct.setName(product.getName());
        }
        if (updatedProduct != null && !product.getDescription().equals(updatedProduct.getDescription())) {
            updatedProduct.setDescription(product.getDescription());
        }
        if (updatedProduct != null && product.getCap() != updatedProduct.getCap()) {
            updatedProduct.setCap(product.getCap());
        }
        if (updatedProduct != null && product.getMaxCap() != updatedProduct.getMaxCap()) {
            updatedProduct.setMaxCap(product.getMaxCap());
        }
        if (updatedProduct != null && product.getWarehouse() != updatedProduct.getWarehouse()) {
            updatedProduct.setWarehouse(product.getWarehouse());
        }

        productRepository.save(updatedProduct);

        return updatedProduct;
        
    }

    public Product findProductById(long id) {
        return productRepository.findById(id);
    }
    
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

}
