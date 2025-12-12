package com.mtlaguerre.inventory_management.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.services.WarehouseNameService;

@RestController
@RequestMapping("/warehouses/names")
public class WarehouseNameController {

    private final WarehouseNameService warehouseNameService;

    public WarehouseNameController(WarehouseNameService warehouseNameService) {
        this.warehouseNameService = warehouseNameService;
    }
}
