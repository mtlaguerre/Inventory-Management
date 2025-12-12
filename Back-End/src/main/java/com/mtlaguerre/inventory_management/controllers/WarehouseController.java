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

    // constructor injection
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // GET /warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses() {
        
        try {
            List<Warehouse> warehouses = warehouseService.findAllWarehouses();
            return new ResponseEntity<>(warehouses, HttpStatus.OK);                                 // return 200 if successful
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise return 500
        }
    }

    // POST /warehouses
    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
        
        try {
            warehouseService.addWarehouse(warehouse);
            return new ResponseEntity<>(HttpStatus.CREATED);                                        // return 201 if successfully created warehouse
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();           // return 400 if input is invalid, with message
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise return 500
        }
    }
    

}
