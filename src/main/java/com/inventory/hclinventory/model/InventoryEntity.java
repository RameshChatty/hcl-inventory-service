package com.inventory.hclinventory.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "inventory")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@ToString
public class InventoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 200)
	private String name;

	@Column(nullable = false, length = 100)
	private String category;

	@Column(length = 100)
	private String subCategory;

	private LocalDate manufacturingDate;

	private LocalDate expiryDate;

	@Column(length = 2000)
	private String specification;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal price;

	@Column(nullable = false)
	private Integer stock;

	@Column(length = 100)
	private String model;

	@Column(length = 200)
	private String seller;

	@Column(length = 255)
	private String location;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@Column(nullable = false)
	private LocalDateTime updatedDate;

	public InventoryEntity(Builder builder) {
		
	
		this.id = builder.id;
		this.name = builder.name;
		this.category = builder.category;
		this.subCategory = builder.subCategory;
		this.manufacturingDate = builder.manufacturingDate;
		this.expiryDate = builder.expiryDate;
		this.specification = builder.specification;
		this.price = builder.price;
		this.stock = builder.stock;
		this.model = builder.model;
		this.seller = builder.seller;
		this.location = builder.location;
		this.createdDate = builder.createdDate;
		this.updatedDate = builder.updatedDate;
	}

	public InventoryEntity() {
		
	}



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

	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	// builder pattern for to load the data:

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private Long id;

		private String name;

		private String category;

		private String subCategory;

		private LocalDate manufacturingDate;

		private LocalDate expiryDate;

		private String specification;

		private BigDecimal price;

		private Integer stock;

		private String model;

		private String seller;

		private String location;

		private LocalDateTime createdDate;

		private LocalDateTime updatedDate;
		

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder category(String category) {
			this.category = category;
			return this;
		}

		public Builder subCategory(String subCategory) {
			this.subCategory = subCategory;
			return this;
		}

		public Builder manufacturingDate(LocalDate manufacturingDate) {
			this.manufacturingDate = manufacturingDate;
			return this;
		}

		public Builder expiryDate(LocalDate expiryDate) {
			this.expiryDate = expiryDate;
			return this;
		}

		public Builder specification(String specification) {
			this.specification = specification;
			return this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder stock(Integer stock) {
			this.stock = stock;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		public Builder seller(String seller) {
			this.seller = seller;
			return this;
		}

		public Builder location(String location) {
			this.location = location;
			return this;
		}

		public Builder createdDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Builder updatedDate(LocalDateTime updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}
		
		
		public InventoryEntity build() {
			return new InventoryEntity(this);
			}
		
		
		

	}

}