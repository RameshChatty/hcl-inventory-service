package com.inventory.hclinventory.service;

import org.springframework.data.domain.Pageable;

import com.inventory.hclinventory.dto.InventorySearchRequest;
import com.inventory.hclinventory.dto.InventorySearchResponse;

public interface InventorySearchService {

	InventorySearchResponse searchInventoryDetails(InventorySearchRequest request,Pageable pageable);

	InventorySearchResponse getInventories();

}
