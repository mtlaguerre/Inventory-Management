package com.mtlaguerre.inventory_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mtlaguerre.inventory_management.models.Warehouse;
import com.mtlaguerre.inventory_management.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    // constructor injection
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse addWarehouse(Warehouse warehouse) throws IllegalArgumentException {
        
        // handle missing name
        if (warehouse.getWarehouseName() == null) {
            throw new IllegalArgumentException("Warehouse is missing name info.");              // throw exception with custom message
        }

        // handle entered capacity (should never be predetermined)
        else if (warehouse.getCapacity() > 0) {
            throw new IllegalArgumentException("Warehouse capacity cannot be set.");
        }

        // handle enterd products (should never be predetermined)
        else if (warehouse.getProducts() != null) {
            throw new IllegalArgumentException("Products can only be added after Warehouse is created.");
        }

        else return warehouseRepository.save(warehouse);
    }

    public Warehouse findWarehouseById(long warehouseId) throws IllegalArgumentException {
        Optional<Warehouse> optWarehouse = warehouseRepository.findById(warehouseId);   // attempt to find warehouse by id

        // if warehouse found
        if (optWarehouse.isPresent()) {
            return optWarehouse.get();      // return found warehouse
        }
        else {
            throw new IllegalArgumentException("Warehouse not found");      // define custom message with exception
        }
    }

    public Warehouse updateWarehouse(long warehouseId, Warehouse warehouse) throws IllegalArgumentException {

        // warehouse with soon-to-be-changed values
        Warehouse oldWarehouse = findWarehouseById(warehouseId);

        // copy current warehouse values
        Warehouse updatedWarehouse = oldWarehouse;

        // handle changed name
        if (warehouse.getWarehouseName() != null && !warehouse.getWarehouseName().equals(oldWarehouse.getWarehouseName())) {
            updatedWarehouse.setWarehouseName(warehouse.getWarehouseName());    // update name to new value
        } else {
            throw new IllegalArgumentException("No changes were found");        // throw exception with custom message
        }

        // handle entered capacity
        if (warehouse.getCapacity() > 0) {
            throw new IllegalArgumentException("Warehouse capacity cannot be set.");
        }

        updatedWarehouse = warehouseRepository.save(updatedWarehouse);

        return updatedWarehouse;

    }
}
