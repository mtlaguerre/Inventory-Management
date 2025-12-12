package com.mtlaguerre.inventory_management.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouse_name")
public class WarehouseName {

    @Id
    @Column(name = "warehouse_name_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "warehouse_name_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "warehouse_name_loc_id")
    private WarehouseLocation warehouseLocation;

    @OneToMany(targetEntity = Warehouse.class, mappedBy = "warehouseName")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public WarehouseName() {
    }

    public WarehouseName(long id) {
        this.id = id;
    }

    public WarehouseName(long id, String name, WarehouseLocation warehouseLocation, List<Warehouse> warehouses) {
        this.id = id;
        this.name = name;
        this.warehouseLocation = warehouseLocation;
        this.warehouses = warehouses;
    }

    public WarehouseName(String name, WarehouseLocation warehouseLocation, List<Warehouse> warehouses) {
        this.name = name;
        this.warehouseLocation = warehouseLocation;
        this.warehouses = warehouses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WarehouseLocation getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(WarehouseLocation warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((warehouseLocation == null) ? 0 : warehouseLocation.hashCode());
        result = prime * result + ((warehouses == null) ? 0 : warehouses.hashCode());
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
        WarehouseName other = (WarehouseName) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (warehouseLocation == null) {
            if (other.warehouseLocation != null)
                return false;
        } else if (!warehouseLocation.equals(other.warehouseLocation))
            return false;
        if (warehouses == null) {
            if (other.warehouses != null)
                return false;
        } else if (!warehouses.equals(other.warehouses))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WarehouseName [id=" + id + ", name=" + name + ", warehouseLocation=" + warehouseLocation
                + ", warehouses=" + warehouses + "]";
    }
}
