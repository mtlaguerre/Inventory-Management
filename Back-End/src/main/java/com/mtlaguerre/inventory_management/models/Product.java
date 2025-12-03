package com.mtlaguerre.inventory_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity                             // tells Spring JPA this is a database table AND A BEAN
@Table(name = "products")           // only needed if your db table is a different name than your class
public class Product {

    @Id                                                     // tells JPA this is our primary key
    @Column(name = "prod_id")                               // this is a database column
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // tells JPA this is an auto-increment field
    private long id;

    @Column(name = "prod_rm")                              // specifies that this corresponds to the "prod_num" column
    private int rm;

    @Column(name = "prod_name")                             // specifies that this corresponds to the "prod_name" column
    private String name;

    @Column(name = "prod_desc")                             // specifies that this corresponds to the "prod_desc" column
    private String description;

    @Column(name = "prod_cap")                              // specifies that this corresponds to the "prod_cap" column
    private float cap;

    @Column(name = "prod_max_cap")                          // specifies that this corresponds to the "prod_max_cap" column
    private float maxCap;

    @JoinColumn(name = "wh_id")                             // tells JPA that there is a relationship to this other model
    @ManyToOne                                              // tells JPA that this is the MANY side of the relationship
    @JsonIgnore
    private Warehouse warehouse;

    public Product() {
    }

    public Product(int rm, String name, String description, float cap, float maxCap, Warehouse warehouse) {
        this.rm = rm;
        this.name = name;
        this.description = description;
        this.cap = cap;
        this.maxCap = maxCap;
        this.warehouse = warehouse;
    }

    public Product(long id, int rm, String name, String description, float cap, float maxCap, Warehouse warehouse) {
        this.id = id;
        this.rm = rm;
        this.name = name;
        this.description = description;
        this.cap = cap;
        this.maxCap = maxCap;
        this.warehouse = warehouse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRm() {
        return rm;
    }

    public void setRm(int rm) {
        this.rm = rm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCap() {
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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + rm;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + Float.floatToIntBits(cap);
        result = prime * result + Float.floatToIntBits(maxCap);
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
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
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        if (rm != other.rm)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (Float.floatToIntBits(cap) != Float.floatToIntBits(other.cap))
            return false;
        if (Float.floatToIntBits(maxCap) != Float.floatToIntBits(other.maxCap))
            return false;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", rm=" + rm + ", name=" + name + ", description=" + description + ", cap=" + cap
                + ", maxCap=" + maxCap + "]";
    }
}
