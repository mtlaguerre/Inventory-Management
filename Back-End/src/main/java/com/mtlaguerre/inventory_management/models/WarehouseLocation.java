package com.mtlaguerre.inventory_management.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouse_location")
public class WarehouseLocation {

    @Id
    @Column(name = "warehouse_location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "warehouse_location_location")
    private String location;

    @Column(name = "warehouse_location_max_capacity")
    private int maxCapacity;

    @OneToMany(targetEntity = WarehouseName.class, mappedBy = "warehouseLocation")
    private List<WarehouseName> warehouseNames;

    public WarehouseLocation(long id, String location, int maxCapacity, List<WarehouseName> warehouseNames) {
        this.id = id;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.warehouseNames = warehouseNames;
    }

    public WarehouseLocation(String location, int maxCapacity, List<WarehouseName> warehouseNames) {
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.warehouseNames = warehouseNames;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<WarehouseName> getWarehouseNames() {
        return warehouseNames;
    }

    public void setWarehouseNames(List<WarehouseName> warehouseNames) {
        this.warehouseNames = warehouseNames;
    }

    @Override
    public String toString() {
        return "WarehouseLocation [id=" + id + ", location=" + location + ", maxCapacity=" + maxCapacity
                + "]";
    }
}
