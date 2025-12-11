package com.mtlaguerre.inventory_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtlaguerre.inventory_management.models.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    
    public Optional<Warehouse> findById(long id);

    public List<Warehouse> findByName(String name);
}
