package com.inventory.hclinventory.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.inventory.hclinventory.dto.InventorySearchRequest;
import com.inventory.hclinventory.model.InventoryEntity;

import jakarta.persistence.criteria.Predicate;

public class InventorySpecification {

	public static Specification<InventoryEntity> build(InventorySearchRequest request) {

		return (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (request.getId() != null) {
				predicates.add(cb.equal(root.get("id"), request.getId()));
			}

			if (StringUtils.hasText(request.getName())) {
				predicates.add(cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
			}

			if (StringUtils.hasText(request.getCategory())) {
				predicates.add(cb.like(cb.lower(root.get("category")), "%"+request.getCategory().toLowerCase()+"%"));
			}

			if (StringUtils.hasText(request.getSubCategory())) {
				predicates.add(cb.like(cb.lower(root.get("subCategory")), "%"+ request.getSubCategory().toLowerCase()+"%"));
			}

			if (StringUtils.hasText(request.getModel())) {
				predicates.add(cb.like(cb.lower(root.get("model")), "%" + request.getModel().toLowerCase().toLowerCase() + "%"));
			}

			if (StringUtils.hasText(request.getSeller())) {
				predicates.add(cb.equal(root.get("seller"), request.getSeller()));
			}

			if (StringUtils.hasText(request.getLocation())) {
				predicates.add(cb.equal(root.get("location"), request.getLocation()));
			}

			// Manufacturing Date Range
			if (request.getManufacturingDateFrom() != null) {
				predicates.add(
						cb.greaterThanOrEqualTo(root.get("manufacturingDate"), request.getManufacturingDateFrom()));
			}

			if (request.getManufacturingDateTo() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("manufacturingDate"), request.getManufacturingDateTo()));
			}

			// Expiry Date Range
			if (request.getExpiryDateFrom() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("expiryDate"), request.getExpiryDateFrom()));
			}

			if (request.getExpiryDateTo() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("expiryDate"), request.getExpiryDateTo()));
			}

			// Price Range
			if (request.getMinPrice() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("price"), request.getMinPrice()));
			}

			if (request.getMaxPrice() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("price"), request.getMaxPrice()));
			}

			// Stock Range
			if (request.getMinStock() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("stock"), request.getMinStock()));
			}

			if (request.getMaxStock() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("stock"), request.getMaxStock()));
			}

			if (StringUtils.hasText(request.getSpecification())) {
				predicates.add(cb.like(cb.lower(root.get("specification")),
						"%" + request.getSpecification().toLowerCase() + "%"));
			}

			if (StringUtils.hasText(request.getStatus())) {
				predicates.add(cb.equal(root.get("status"), request.getStatus()));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

}
