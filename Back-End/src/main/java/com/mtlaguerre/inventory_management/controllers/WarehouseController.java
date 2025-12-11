package com.mtlaguerre.inventory_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // GET /warehouses
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

    // GET /warehouses/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable("id") long warehouseId) {

        try {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            return new ResponseEntity<>(warehouse, HttpStatus.OK);       // returns 200 if everything succeeds
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);            // returns 400 if id doesn't exist
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // returns 500 if anything else goes wrong
        }
    }

    // GET /warehouses/warehouse?name=
    @GetMapping("warehouse")
    public ResponseEntity<List<Warehouse>> findWarehouseByName(@RequestParam(name = "name", required = false) String warehouseName) {
        List<Warehouse> warehouses = warehouseService.findWarehouseByName(warehouseName);
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }
    
    // POST /warehouses
    @PostMapping
    public ResponseEntity<Warehouse> addNewWarehouse(@RequestBody Warehouse warehouse) {

        try {
            return new ResponseEntity<>(warehouseService.createWarehouse(warehouse), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
    
    // PUT /warehouses/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") long warehouseId, @RequestBody Warehouse warehouse) {
        
        try {
            Warehouse updatedWarehouse = warehouseService.updateWarehouse(warehouseId, warehouse);
            return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);       // returns 200 if everything succeeds
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);            // returns 400 if id doesn't exist
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // returns 500 if anything else goes wrong
        }
    }

    // DELETE /warehouses/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable("id") long warehouseId) {

        try {
            warehouseService.deleteWarehouse(warehouseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("mesage", e.getMessage()).build();
        }
    }
    
}
