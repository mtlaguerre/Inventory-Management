package com.mtlaguerre.inventory_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mtlaguerre.inventory_management.models.Warehouse;
import com.mtlaguerre.inventory_management.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        // verify maxCap & cap <= 0 && maxCap <= cap
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> findAllWarehouses() {

        return warehouseRepository.findAll();
    }

    public Warehouse findById(long id) throws IllegalArgumentException {
        
        Optional<Warehouse> optWarehouse = warehouseRepository.findById(id);

        if (optWarehouse.isPresent()) {
            return optWarehouse.get();
        } else {
            throw new IllegalArgumentException("Warehouse doesn't exist");
        }
    }

    public Warehouse updateWarehouse(long id, Warehouse warehouse) {
        
        Warehouse oldWarehouse = findById(id);
        Warehouse updatedWarehouse = oldWarehouse;

        if (updatedWarehouse != null && !warehouse.getName().equals(updatedWarehouse.getName())) {
            updatedWarehouse.setName(warehouse.getName());
        }
        if (updatedWarehouse != null && !warehouse.getLocation().equals(updatedWarehouse.getLocation())) {
            updatedWarehouse.setLocation(warehouse.getLocation());
        }
        if (updatedWarehouse != null && warehouse.getCap() != updatedWarehouse.getCap()) {
            updatedWarehouse.setCap(warehouse.getCap());
        }
        if (updatedWarehouse != null && warehouse.getMaxCap() != updatedWarehouse.getMaxCap()) {
            updatedWarehouse.setMaxCap(warehouse.getMaxCap());
        }
        if (updatedWarehouse != null && warehouse.getProducts() != updatedWarehouse.getProducts()) {
            updatedWarehouse.setProducts(warehouse.getProducts());
        }

        warehouseRepository.save(updatedWarehouse);

        return updatedWarehouse;
    }

    public void deleteWarehouse(long id) {

        warehouseRepository.deleteById((int)id);
    }
}
