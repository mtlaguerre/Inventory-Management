package com.mtlaguerre.inventory_management.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.models.WarehouseLocation;
import com.mtlaguerre.inventory_management.services.WarehouseLocationService;

@RestController
@RequestMapping("/warehouses/locations")
@CrossOrigin("http://127.0.0.1:5500")
public class WarehouseLocationController {

    private final WarehouseLocationService warehouseLocationService;

    public WarehouseLocationController(WarehouseLocationService warehouseLocationService) {
        this.warehouseLocationService = warehouseLocationService;
    }

    // POST /warehouses/locations
    @PostMapping
    public ResponseEntity<WarehouseLocation> createWarehouseLocation(@RequestBody WarehouseLocation warehouseLocation) {

        try {
            WarehouseLocation newLocation = warehouseLocationService.createWarehouseLocation(warehouseLocation);
            return new ResponseEntity<>(newLocation, HttpStatus.CREATED);        // return 201 if successfully added product
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();   // return 400 if invalid request and display custom message
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise, return 500 and display message
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<WarehouseLocation> deleteWarehouseLocation(@PathVariable("id") long warehouseLocationId) {
        
        try {
            warehouseLocationService.deleteWarehouseLocationById(warehouseLocationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

}
