package com.mtlaguerre.inventory_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.models.Warehouse;
import com.mtlaguerre.inventory_management.services.WarehouseService;



@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.findAllWarehouses();

        try {
            return new ResponseEntity<>(warehouses, HttpStatus.OK);       // return products and 200 if everything succeeds
        } catch (Exception e) {
            
            // return a 500 if any sort of exception occurs
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<Warehouse> addNewWarehouse(@RequestBody Warehouse warehouse) {
        try {
            return new ResponseEntity<>(warehouseService.createWarehouse(warehouse), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
    
    
}
