package com.inventory.hclinventory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.inventory.hclinventory.dto.Inventory;
import com.inventory.hclinventory.dto.InventorySearchRequest;
import com.inventory.hclinventory.dto.InventorySearchResponse;
import com.inventory.hclinventory.service.InventorySearchService;

@ExtendWith(MockitoExtension.class)
class InventorySearchControllerTest {

    @Mock
    private InventorySearchService inventorySearchService;

    @InjectMocks
    private InventorySearchController inventorySearchController;

    @Test
    void searchInventories_returnsOkWithBody() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setName("Test Item");

        InventorySearchResponse response = new InventorySearchResponse();
        response.setInventories(List.of(inventory));

        when(inventorySearchService.getInventories()).thenReturn(response);

        ResponseEntity<InventorySearchResponse> result = inventorySearchController.searchInventories();

        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isSameAs(response);

        verify(inventorySearchService).getInventories();
    }

    @Test
    void searchByParams_buildsRequestAndCallsService() {
        InventorySearchResponse response = new InventorySearchResponse();
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setName("Test Item");
        response.setInventories(List.of(inventory));

        Pageable pageable = PageRequest.of(0, 20);

        when(inventorySearchService.searchInventoryDetails(any(InventorySearchRequest.class), eq(pageable)))
                .thenReturn(response);

        ResponseEntity<InventorySearchResponse> result = inventorySearchController.searchByParams(
                5L,
                "Test Item",
                "CategoryX",
                "SubX",
                "ModelX",
                "SellerX",
                "LocationX",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 31),
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31),
                new BigDecimal("10.00"),
                new BigDecimal("100.00"),
                1,
                50,
                "Specs",
                "ACTIVE",
                pageable);

        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isSameAs(response);

        ArgumentCaptor<InventorySearchRequest> requestCaptor = ArgumentCaptor.forClass(InventorySearchRequest.class);
        verify(inventorySearchService).searchInventoryDetails(requestCaptor.capture(), eq(pageable));

        InventorySearchRequest captured = requestCaptor.getValue();
        assertThat(captured.getId()).isEqualTo(5L);
        assertThat(captured.getName()).isEqualTo("Test Item");
    }
}