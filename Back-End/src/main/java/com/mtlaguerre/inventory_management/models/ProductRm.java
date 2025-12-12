package com.mtlaguerre.inventory_management.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_rm")
public class ProductRm {

    @Id
    @Column(name = "product_rm_rm")
    private int rm;

    @Column(name = "product_rm_name")
    private String name;

    @Column(name = "product_rm_description")
    private String description;

    @Column(name = "product_rm_max_capacity")
    private int maxCapacity;

    @OneToMany(targetEntity = Product.class, mappedBy = "productRm")
    @JsonIgnore
    private List<Product> products;

    public ProductRm() {
    }

    public ProductRm(int rm) {
        this.rm = rm;
    }

    public ProductRm(int rm, String name, String description, int maxCapacity, List<Product> products) {
        this.rm = rm;
        this.name = name;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.products = products;
    }

    public ProductRm(String name, String description, int maxCapacity, List<Product> products) {
        this.name = name;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.products = products;
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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
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
        result = prime * result + rm;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + maxCapacity;
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
        ProductRm other = (ProductRm) obj;
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
        if (maxCapacity != other.maxCapacity)
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
        return "ProductRm [rm=" + rm + ", name=" + name + ", description=" + description + ", maxCapacity="
                + maxCapacity + ", products=" + products + "]";
    }

    
}
