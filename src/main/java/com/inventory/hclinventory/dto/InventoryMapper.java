package com.inventory.hclinventory.dto;

public interface InventoryMapper {

    InventorySearchResponse toDto(Inventory inventory);
}