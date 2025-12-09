package com.mtlaguerre.inventory_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtlaguerre.inventory_management.models.Product;


/**
 * REPOSITORIES
 *      talk to your database (executes queries)
 *      no business logic
 *          only method declarations, no method bodies since its an interface
 * 
 *      JpaRepository needs two data types: one for the model and another for the PK of that model
 * 
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Optional<List<Product>> findByName(String name);
}
