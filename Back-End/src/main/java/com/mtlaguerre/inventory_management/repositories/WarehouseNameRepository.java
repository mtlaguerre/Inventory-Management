package com.mtlaguerre.inventory_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtlaguerre.inventory_management.models.WarehouseName;

@Repository
public interface WarehouseNameRepository extends JpaRepository<WarehouseName, Long>{

}
