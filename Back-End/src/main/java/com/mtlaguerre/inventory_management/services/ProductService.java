package com.mtlaguerre.inventory_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mtlaguerre.inventory_management.models.Product;
import com.mtlaguerre.inventory_management.models.Warehouse;
import com.mtlaguerre.inventory_management.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // constructor injection for our beans
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);         // add product to database
    }

    public Product updateProduct(long id, Product product) {

        Product oldProduct = findProductById(id);   // find current product values
        Product updatedProduct = oldProduct;        // create a copy of current product

        // if data isn't null & old value != new value, then update to new value
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

        productRepository.save(updatedProduct);     // overwrite ware current product with the updated values

        return updatedProduct;      // return product with changes
        
    }

    public Product findProductById(long id) throws IllegalArgumentException {

        Optional<Product> optProduct = productRepository.findById(id);      // try finding product with id

        // if product found
        if (optProduct.isPresent()) {
            return optProduct.get();    // return found product
        } else {
            // handle product not found
            throw new IllegalArgumentException("Product doesn't exist");
        }
    }
    
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(long id) {

        Product product = findProductById(id);      // try finding product with id
        productRepository.delete(product);          // remove product from database
    }

    public List<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findProductByRm(int rm) {
        return productRepository.findByRm(rm);
    }

    public List<Product> findProductByDescription(String description) {
        return productRepository.findByDescriptionContaining(description);
    }

    public Product transferProductToWarehouse(long pId, Warehouse warehouse) throws IllegalArgumentException {

        /**
         * NOTES:
         *      Required Functionality
         *          consider warehouse capacities during transfers
         */

        Product product = findProductById(pId);

        // if transfer would surpass warehouse max capacity
        if (product.getCap() + warehouse.getCap() > warehouse.getMaxCap()) {
            throw new IllegalArgumentException("Not enough room to transfer product.");
        }
        else {
            // if product's warehouse ids are not equal (not same warehouse)
            if (product.getWarehouse().getId() != warehouse.getId()) {
                Product updatedProduct = product;                           // copy product values
                product.getWarehouse().setCap(product.getWarehouse().getCap() - product.getCap());                  // remove item capacity from current warehouse
                updatedProduct.setWarehouse(warehouse);                     // update product's warehouse location
                updatedProduct =  updateProduct(product.getId(), updatedProduct);      // confirm changes
                updatedProduct.getWarehouse().setCap(updatedProduct.getWarehouse().getCap() + product.getCap());    // add item capacity to destination warehouse

                return updatedProduct;
            }
            else {
                throw new IllegalArgumentException("Warehouse destination equals current warehouse.");
            }
        }
    }

}
