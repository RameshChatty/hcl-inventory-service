package com.inventory.hclinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.inventory.hclinventory.model.InventoryEntity;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long>, JpaSpecificationExecutor<InventoryEntity> {

	//void getFilteredInventoryDetails(InventorySearchRequest request, Pageable pageable);
}