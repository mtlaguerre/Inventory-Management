package com.mtlaguerre.inventory_management.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.services.WarehouseLocationService;

@RestController
@RequestMapping("/warehouses/locations")
@CrossOrigin("http://127.0.0.1:5500")
public class WarehouseLocationController {

    private final WarehouseLocationService warehouseLocationService;

    public WarehouseLocationController(WarehouseLocationService warehouseLocationService) {
        this.warehouseLocationService = warehouseLocationService;
    }

}
