package com.mtlaguerre.inventory_management.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.models.WarehouseName;
import com.mtlaguerre.inventory_management.services.WarehouseNameService;

@RestController
@RequestMapping("/warehouses/names")
@CrossOrigin("http://127.0.0.1:5500")
public class WarehouseNameController {

    private final WarehouseNameService warehouseNameService;

    public WarehouseNameController(WarehouseNameService warehouseNameService) {
        this.warehouseNameService = warehouseNameService;
    }

    @PostMapping
    public ResponseEntity<WarehouseName> createWarehouseName(@RequestBody WarehouseName warehouseName) {
        
        try {
            WarehouseName newName = warehouseNameService.createWarehouseName(warehouseName);
            return new ResponseEntity<>(newName, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
