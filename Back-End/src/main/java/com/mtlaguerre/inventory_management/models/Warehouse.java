package com.mtlaguerre.inventory_management.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity                             // tells Spring JPA this is a database table AND A BEAN
@Table(name = "warehouses")           // only needed if your db table is a different name than your class
public class Warehouse {

    @Id                                                     // tells jpa this is our primary key
    @Column(name = "products_id")                           // this is a database column        "name = " specifies this corresponds to the "products_id" column
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // tells jpa this is an auto-increment field
    private long id;

    @ManyToOne
    @JoinColumn(name = "warehouses_name_id")
    private WarehouseName warehouseName;

    @Column(name = "warehouses_capacity")
    private int capacity;

    // NO column annotation... because it isn't a column in our table
    // Warehouse is the ONE side of the relationship, mappedBy is the name of the JAVA PROPERTY in the other class
    @OneToMany(targetEntity = Product.class, mappedBy = "warehouse")
    private List<Product> products;

    public Warehouse() {
    }

    public Warehouse(long id, WarehouseName warehouseName, int capacity, List<Product> products) {
        this.id = id;
        this.warehouseName = warehouseName;
        this.capacity = capacity;
        this.products = products;
    }

    public Warehouse(WarehouseName warehouseName, int capacity, List<Product> products) {
        this.warehouseName = warehouseName;
        this.capacity = capacity;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WarehouseName getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(WarehouseName warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
        result = prime * result + ((warehouseName == null) ? 0 : warehouseName.hashCode());
        result = prime * result + capacity;
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
        if (warehouseName == null) {
            if (other.warehouseName != null)
                return false;
        } else if (!warehouseName.equals(other.warehouseName))
            return false;
        if (capacity != other.capacity)
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
        return "Warehouse [id=" + id + ", warehouseName=" + warehouseName + ", capacity=" + capacity + "]";
    }

    
}
