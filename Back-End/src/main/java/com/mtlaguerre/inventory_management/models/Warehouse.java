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
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @Column(name = "warehouse_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "warehouse_name")
    private String name;

    @Column(name = "warehouse_location")
    private String location;

    @Column(name = "warehouse_capacity")
    private float cap;

    @Column(name = "warehouse_max_capacity")
    private float maxCap;

    // Warehouse is the ONE side of the relationship, mappedBy is the name of the JAVA PROPERTY in the other class
    @OneToMany(targetEntity = Product.class, mappedBy = "warehouse")
    private List<Product> products;

    public Warehouse() {
    }

    public Warehouse(String name, String location, float maxCap, List<Product> products) {
        this.name = name;
        this.location = location;
        this.cap = 0;
        this.maxCap = maxCap;
        this.products = products;
    }

    public Warehouse(long id, String name, String location, float maxCap, List<Product> products) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cap = 0;
        this.maxCap = maxCap;
        this.products = products;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getCap() {
        // dynamically set return capacity by products list
        if (!products.isEmpty()) {
            for (Product product : products) {
                cap += product.getCap();
            }
        }
        
        return cap;
    }

    public void setCap(float cap) {
        this.cap = cap;
    }

    public float getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(float maxCap) {
        this.maxCap = maxCap;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + Float.floatToIntBits(cap);
        result = prime * result + Float.floatToIntBits(maxCap);
        result = prime * result + ((products == null) ? 0 : products.hashCode());
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
        Warehouse other = (Warehouse) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (Float.floatToIntBits(cap) != Float.floatToIntBits(other.cap))
            return false;
        if (Float.floatToIntBits(maxCap) != Float.floatToIntBits(other.maxCap))
            return false;
        if (products == null) {
            if (other.products != null)
                return false;
        } else if (!products.equals(other.products))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", name=" + name + ", location=" + location + ", cap=" + cap + ", maxCap="
                + maxCap + ", products=" + products + "]";
    }
}
