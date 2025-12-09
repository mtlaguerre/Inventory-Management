package com.mtlaguerre.inventory_management.services;

import java.util.List;

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
}
