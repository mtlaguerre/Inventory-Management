package com.mtlaguerre.inventory_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.models.Warehouse;
import com.mtlaguerre.inventory_management.services.WarehouseService;


@RestController
@RequestMapping("/warehouses")
@CrossOrigin("http://127.0.0.1:5500")
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

    // PUT /warehouses/id/{id}
    @PutMapping("/id/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") long warehouseId, @RequestBody Warehouse warehouse) {

        try {
            warehouseService.updateWarehouse(warehouseId, warehouse);
            return new ResponseEntity<>(HttpStatus.OK);                                             // return 200 if successfully updated warehouse
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();           // return 400 if input is invalid, with message
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise return 500
        }
    }

    // DELETE /warehouses/id/{id}
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable("id") long warehouseId) {
        
        try {
            warehouseService.deleteWarehouse(warehouseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);                                     // return 204 if successfully deleted warehouse
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();  // otherwise return 500
        }
    }
    

}
