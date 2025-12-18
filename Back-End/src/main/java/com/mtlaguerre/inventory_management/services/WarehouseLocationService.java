package com.mtlaguerre.inventory_management.services;

import org.springframework.stereotype.Service;

import com.mtlaguerre.inventory_management.models.WarehouseLocation;
import com.mtlaguerre.inventory_management.repositories.WarehouseLocationRepository;

@Service
public class WarehouseLocationService {

    private final WarehouseLocationRepository warehouseLocationRepository;

    public WarehouseLocationService(WarehouseLocationRepository warehouseLocationRepository) {
        this.warehouseLocationRepository = warehouseLocationRepository;
    }

    public WarehouseLocation createWarehouseLocation(WarehouseLocation warehouseLocation) throws IllegalArgumentException {

        // handle unset location
        if (warehouseLocation.getLocation() == null) {
            throw new IllegalArgumentException("Product is missing RM info.");
        }

        // handle unset max capacity
        else if (warehouseLocation.getMaxCapacity() <= 0) {
            throw new IllegalArgumentException("Product capacity must be greater than 0.");
        }
        
        else {
            return warehouseLocationRepository.save(warehouseLocation);
        }
    }
}
