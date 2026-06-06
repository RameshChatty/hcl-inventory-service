package com.inventory.hclinventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.inventory.hclinventory.dto.Inventory;
import com.inventory.hclinventory.dto.InventorySearchRequest;
import com.inventory.hclinventory.dto.InventorySearchResponse;
import com.inventory.hclinventory.model.InventoryEntity;
import com.inventory.hclinventory.repository.InventoryRepository;
import com.inventory.hclinventory.repository.InventorySpecification;

@Service
public class InventorySearchServiceImpl implements InventorySearchService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Override
	public InventorySearchResponse getInventories() {
		List<InventoryEntity> all = inventoryRepository.findAll();
		InventorySearchResponse response = new InventorySearchResponse();

		List<Inventory> list = all.stream().map(this::convertToDto).toList();
		response.setInventories(list);
		return response;
	}

	@Override
	public InventorySearchResponse searchInventoryDetails(InventorySearchRequest request, Pageable pageable) {

		Specification<InventoryEntity> spec = InventorySpecification.build(request);

		Page<InventoryEntity> pageData = inventoryRepository.findAll(spec, pageable);

		List<Inventory> inventories = pageData.getContent().stream().map(this::convertToDto).toList();

		InventorySearchResponse response = new InventorySearchResponse();

		response.setInventories(inventories);
		response.setPageNumber(pageData.getPageable().getPageNumber());
		response.setPageSize(pageData.getSize());
		response.setTotalCount(pageData.getTotalElements());

		return response;

	}

	private Inventory convertToDto(InventoryEntity inventory) {

		Inventory inv = new Inventory();

		inv.setId(inventory.getId());
		inv.setName(inventory.getName());
		inv.setCategory(inventory.getCategory());

		inv.setSubCategory(inventory.getSubCategory());
		inv.setManufacturingDate(inventory.getManufacturingDate());
		inv.setExpiryDate(inventory.getExpiryDate());
		inv.setSpecification(inventory.getSpecification());
		inv.setPrice(inventory.getPrice());
		inv.setStock(inventory.getStock());
		inv.setModel(inventory.getModel());

		inv.setSeller(inventory.getSeller());
		inv.setLocation(inventory.getLocation());
		inv.setCreatedDate(inventory.getCreatedDate());
		inv.setUpdatedDate(inventory.getUpdatedDate());

		return inv;
	}

}
