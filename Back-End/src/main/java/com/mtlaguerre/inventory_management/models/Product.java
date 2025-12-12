package com.mtlaguerre.inventory_management.models;

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

    @Id                                                     // tells jpa this is our primary key
    @Column(name = "products_id")                           // this is a database column        "name = " specifies this corresponds to the "products_id" column
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // tells jpa this is an auto-increment field
    private long id;

    @ManyToOne
    @JoinColumn(name = "products_rm")
    private ProductRm productRm;

    @Column(name = "products_capacity")
    private int capacity;

    @ManyToOne                                      // tells jpa this is the MANY side of the relationship
    @JoinColumn(name = "products_wh")               // tells jpa that there is a relationship to this other model
    private Warehouse warehouse;

    public Product() {
    }

    public Product(long id, ProductRm productRm, int capacity, Warehouse warehouse) {
        this.id = id;
        this.productRm = productRm;
        this.capacity = capacity;
        this.warehouse = warehouse;
    }

    public Product(ProductRm productRm, int capacity, Warehouse warehouse) {
        this.productRm = productRm;
        this.capacity = capacity;
        this.warehouse = warehouse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductRm getRm() {
        return productRm;
    }

    public void setRm(ProductRm productRm) {
        this.productRm = productRm;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
        result = prime * result + ((productRm == null) ? 0 : productRm.hashCode());
        result = prime * result + capacity;
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
        if (productRm == null) {
            if (other.productRm != null)
                return false;
        } else if (!productRm.equals(other.productRm))
            return false;
        if (capacity != other.capacity)
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
        return "Product [id=" + id + ", rm=" + productRm + ", capacity=" + capacity + ", warehouse=" + warehouse + "]";
    }
    
}
