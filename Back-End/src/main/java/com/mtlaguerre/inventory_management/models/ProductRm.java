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
@Table(name = "product_rm")
public class ProductRm {

    @Id
    @Column(name = "product_rm_rm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rm;

    @Column(name = "product_rm_name")
    private String name;

    @Column(name = "product_rm_description")
    private String description;

    @Column(name = "product_rm_max_capacity")
    private int maxCapacity;

    @OneToMany(targetEntity = Product.class, mappedBy = "productRm")
    private List<Product> products;

    public ProductRm(int rm, String name, String description, int maxCapacity, List<Product> products) {
        this.rm = rm;
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
    public String toString() {
        return "ProductRm [rm=" + rm + ", name=" + name + ", description=" + description + ", maxCapacity="
                + maxCapacity + ", products=" + products + "]";
    }

    
}
