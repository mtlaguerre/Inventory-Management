package com.mtlaguerre.inventory_management.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private List<WarehouseName> warehouseNames;

    public WarehouseLocation() {
    }

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + maxCapacity;
        result = prime * result + ((warehouseNames == null) ? 0 : warehouseNames.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WarehouseLocation other = (WarehouseLocation) obj;
        if (id != other.id)
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (maxCapacity != other.maxCapacity)
            return false;
        if (warehouseNames == null) {
            if (other.warehouseNames != null)
                return false;
        } else if (!warehouseNames.equals(other.warehouseNames))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WarehouseLocation [id=" + id + ", location=" + location + ", maxCapacity=" + maxCapacity
                + "]";
    }
}
