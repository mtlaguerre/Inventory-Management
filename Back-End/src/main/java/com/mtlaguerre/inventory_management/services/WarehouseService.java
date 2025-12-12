package com.mtlaguerre.inventory_management.services;

import java.util.List;

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
            throw new IllegalArgumentException("Warehouse is missing name info.");
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
}
