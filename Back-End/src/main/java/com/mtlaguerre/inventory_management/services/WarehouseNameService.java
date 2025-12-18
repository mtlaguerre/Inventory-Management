package com.mtlaguerre.inventory_management.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mtlaguerre.inventory_management.models.WarehouseName;
import com.mtlaguerre.inventory_management.repositories.WarehouseNameRepository;

@Service
public class WarehouseNameService {

    private final WarehouseNameRepository warehouseNameRepository;

    public WarehouseNameService(WarehouseNameRepository warehouseNameRepository) {
        this.warehouseNameRepository = warehouseNameRepository;
    }

    public WarehouseName createWarehouseName(WarehouseName warehouseName) throws IllegalArgumentException {

        // handle missing name
        if (warehouseName.getName() == null) {
            throw new IllegalArgumentException("Must assign a value to 'name'");
        }

        // handle missing location
        else if (warehouseName.getWarehouseLocation() == null) {
            throw new IllegalArgumentException("Must assign a value to 'location'");
        }

        // handle entered warehouses (should never be predetermined)
        else if (warehouseName.getWarehouses() != null) {
            throw new IllegalArgumentException("Cannot attach warehouses during name creation");
        }

        else {
            return warehouseNameRepository.save(warehouseName);
        }
    }

    public WarehouseName findWarehouseNameById(long id) throws IllegalArgumentException {

        Optional<WarehouseName> optWarehouseName = warehouseNameRepository.findById(id);    // attempts to find warehouse name by id

        // if found
        if (optWarehouseName.isPresent()) {
            return optWarehouseName.get();
        }
        else {
            throw new IllegalArgumentException("Warehouse name not found");
        }
    }

    public void deleteWarehouseNameById(long id) {

        WarehouseName warehouseName = findWarehouseNameById(id);

        warehouseNameRepository.delete(warehouseName);
    }
}
