package com.inventory.hclinventory.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class InventorySearchRequest {

    private Long id;

    private String name;

    private String category;

    private String subCategory;

    private String model;

    private String seller;

    private String location;

    private LocalDate manufacturingDateFrom;
    private LocalDate manufacturingDateTo;

    private LocalDate expiryDateFrom;
    private LocalDate expiryDateTo;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    private Integer minStock;
    private Integer maxStock;

    private String specification;

    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getManufacturingDateFrom() {
		return manufacturingDateFrom;
	}

	public void setManufacturingDateFrom(LocalDate manufacturingDateFrom) {
		this.manufacturingDateFrom = manufacturingDateFrom;
	}

	public LocalDate getManufacturingDateTo() {
		return manufacturingDateTo;
	}

	public void setManufacturingDateTo(LocalDate manufacturingDateTo) {
		this.manufacturingDateTo = manufacturingDateTo;
	}

	public LocalDate getExpiryDateFrom() {
		return expiryDateFrom;
	}

	public void setExpiryDateFrom(LocalDate expiryDateFrom) {
		this.expiryDateFrom = expiryDateFrom;
	}

	public LocalDate getExpiryDateTo() {
		return expiryDateTo;
	}

	public void setExpiryDateTo(LocalDate expiryDateTo) {
		this.expiryDateTo = expiryDateTo;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getMinStock() {
		return minStock;
	}

	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}

	public Integer getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(Integer maxStock) {
		this.maxStock = maxStock;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
    
}