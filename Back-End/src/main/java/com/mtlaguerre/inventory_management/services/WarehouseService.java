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

        return warehouseRepository.save(warehouse);     // add warehouse to database
    }

    public List<Warehouse> findAllWarehouses() {

        return warehouseRepository.findAll();
    }

    public Warehouse findById(long id) throws IllegalArgumentException {
        
        Optional<Warehouse> optWarehouse = warehouseRepository.findById(id);    // try finding warehouse with id

        // if warehouse found
        if (optWarehouse.isPresent()) {
            return optWarehouse.get();  // return found warehouse
        } else {
            // handle warehouse not found
            throw new IllegalArgumentException("Warehouse doesn't exist");
        }
    }

    public Warehouse updateWarehouse(long id, Warehouse warehouse) {
        
        Warehouse oldWarehouse = findById(id);          // store current warehouse
        Warehouse updatedWarehouse = oldWarehouse;      // copy current warehouse values

        // if data isn't null & old value != new value, then update to new value
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

        warehouseRepository.save(updatedWarehouse);     // overwrite ware current warehouse with the updated values

        return updatedWarehouse;    // return warehouse with changes
    }

    public void deleteWarehouse(long id) {

        Warehouse warehouse = findById(id);         // try to find warehouse with id
        warehouseRepository.delete(warehouse);      // remove warehouse from database
    }

    public List<Warehouse> findWarehouseByName(String name) {
        return warehouseRepository.findByName(name);
    }
}
