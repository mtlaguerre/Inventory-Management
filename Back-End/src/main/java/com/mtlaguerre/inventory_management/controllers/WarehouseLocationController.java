package com.mtlaguerre.inventory_management.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.services.WarehouseLocationService;

@RestController
@RequestMapping("/warehouses/locations")
public class WarehouseLocationController {

    private final WarehouseLocationService warehouseLocationService;

    public WarehouseLocationController(WarehouseLocationService warehouseLocationService) {
        this.warehouseLocationService = warehouseLocationService;
    }

}
