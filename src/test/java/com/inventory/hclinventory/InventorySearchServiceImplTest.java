package com.inventory.hclinventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.inventory.hclinventory.dto.InventorySearchRequest;
import com.inventory.hclinventory.dto.InventorySearchResponse;
import com.inventory.hclinventory.model.InventoryEntity;
import com.inventory.hclinventory.repository.InventoryRepository;
import com.inventory.hclinventory.service.InventorySearchService;

@ExtendWith(MockitoExtension.class)
class InventorySearchServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventorySearchService inventorySearchService;

    @Test
    void getInventories_returnsMappedInventoryList() {
        InventoryEntity entity = new InventoryEntity();
        entity.setId(1L);
        entity.setName("Test Item");
        entity.setCategory("Category A");
        entity.setSubCategory("Sub A");
        entity.setModel("M1");
        entity.setSeller("Seller X");
        entity.setLocation("Location Y");
        entity.setManufacturingDate(LocalDate.of(2024, 1, 1));
        entity.setExpiryDate(LocalDate.of(2025, 1, 1));
        entity.setSpecification("Specs");
        entity.setPrice(new BigDecimal("100.00"));
        entity.setStock(5);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setUpdatedDate(LocalDateTime.now());

        when(inventoryRepository.findAll()).thenReturn(List.of(entity));

        InventorySearchResponse response = inventorySearchService.getInventories();

        assertThat(response).isNotNull();
        assertThat(response.getInventories()).hasSize(1);
        assertThat(response.getInventories().get(0).getName()).isEqualTo("Test Item");
        assertThat(response.getInventories().get(0).getCategory()).isEqualTo("Category A");

        verify(inventoryRepository).findAll();
    }

    @Test
    void searchInventoryDetails_returnsPagedResponse() {
        InventorySearchRequest request = new InventorySearchRequest();
        request.setName("Test");

        InventoryEntity entity = new InventoryEntity();
        entity.setId(2L);
        entity.setName("Test Item 2");
        entity.setCategory("Category B");
        entity.setSubCategory("Sub B");
        entity.setModel("M2");
        entity.setSeller("Seller Y");
        entity.setLocation("Location Z");
        entity.setManufacturingDate(LocalDate.of(2024, 2, 2));
        entity.setExpiryDate(LocalDate.of(2025, 2, 2));
        entity.setSpecification("Specs2");
        entity.setPrice(new BigDecimal("200.00"));
        entity.setStock(15);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setUpdatedDate(LocalDateTime.now());

        Pageable pageable = PageRequest.of(0, 10);
        Page<InventoryEntity> page = new PageImpl<>(List.of(entity), pageable, 1);

        when(inventoryRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);

        InventorySearchResponse response = inventorySearchService.searchInventoryDetails(request, pageable);

        assertThat(response).isNotNull();
        assertThat(response.getInventories()).hasSize(1);
        assertThat(response.getPageNumber()).isEqualTo(0);
        assertThat(response.getPageSize()).isEqualTo(10);
        assertThat(response.getTotalCount()).isEqualTo(1L);
        assertThat(response.getInventories().get(0).getName()).isEqualTo("Test Item 2");

        verify(inventoryRepository).findAll(any(Specification.class), eq(pageable));
    }
}