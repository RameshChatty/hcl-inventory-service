/**
 * 
 */
package com.inventory.hclinventory.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inventory.hclinventory.dto.InventorySearchRequest;
import com.inventory.hclinventory.dto.InventorySearchResponse;
import com.inventory.hclinventory.service.InventorySearchService;

import jakarta.validation.constraints.Size;

@RestController("/api/inventory")
@RestControllerAdvice
@Validated
public class InventorySearchController {

	@Autowired
	InventorySearchService inventorySearchService;

	@GetMapping("/search")
	public ResponseEntity<InventorySearchResponse> searchInventories() {

		InventorySearchResponse inventories = inventorySearchService.getInventories();
		return ResponseEntity.ok(inventories);
	}

	@GetMapping("/search/by-params")
	public ResponseEntity<InventorySearchResponse> searchByParams(

			@RequestParam(required = false) Long id,
			@RequestParam(required = false) @Size(min = 3, message = "name must be at least 3 characters") String name,
			@RequestParam(required = false)  @Size(min = 3, message = "Category must be at least 3 characters") String category, 
			@RequestParam(required = false)  @Size(min = 3, message = "Sub Category must be at least 3 characters") String subCategory,
			@RequestParam(required = false)  @Size(min = 3, message = "model must be at least 3 characters") String model, 
			@RequestParam(required = false)   @Size(min = 3, message = "seller must be at least 3 characters") String seller,
			@RequestParam(required = false)  @Size(min = 3, message = "location must be at least 3 characters") String location,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate manufacturingDateFrom,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate manufacturingDateTo,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expiryDateFrom,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expiryDateTo,

			@RequestParam(required = false) BigDecimal minPrice, @RequestParam(required = false) BigDecimal maxPrice,

			@RequestParam(required = false) Integer minStock, @RequestParam(required = false) Integer maxStock,

			@RequestParam(required = false) String specification, @RequestParam(required = false) String status,

			Pageable pageable) {

		InventorySearchRequest request = new InventorySearchRequest();

		request.setId(id);
		request.setName(name);
		request.setCategory(category);
		request.setSubCategory(subCategory);
		request.setModel(model);
		request.setSeller(seller);
		request.setLocation(location);

		request.setManufacturingDateFrom(manufacturingDateFrom);
		request.setManufacturingDateTo(manufacturingDateTo);
		request.setExpiryDateFrom(expiryDateFrom);

		request.setExpiryDateTo(expiryDateTo);

		request.setMinPrice(minPrice);
		request.setMaxPrice(maxPrice);

		request.setSpecification(specification);
		request.setStatus(status);

		InventorySearchResponse searchInventoryDetails = inventorySearchService.searchInventoryDetails(request,
				pageable);

		return ResponseEntity.ok(searchInventoryDetails);
	}
}
