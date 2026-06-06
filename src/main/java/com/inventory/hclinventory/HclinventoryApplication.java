package com.inventory.hclinventory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.inventory.hclinventory.model.InventoryEntity;
import com.inventory.hclinventory.repository.InventoryRepository;

@SpringBootApplication
public class HclinventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HclinventoryApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner loadData(InventoryRepository repository) {
	    return args -> {
	        Faker faker = new Faker();

			List<String> sellers = List.of("Amazon", "Flipkart", "Snapdeal", "Croma", "Reliance Digital",
					"Vijay Sales");

			List<String> model = List.of("Samsung", "sony", "kodak");

			List<String> subcat = List.of("camera", "mobile");
			List<String> location = List.of("Hyderabad", "Mumbai", "Chennai", "Bengalore", "Delhi");

	        for (int i = 1; i <= 200; i++) {
	        	
	        	
	            repository.save(
	                InventoryEntity.builder()
	                    .name(faker.commerce().productName())
	                    .category("Electronics")
	                    .subCategory(subcat.get(i % subcat.size()))
	                    .price(BigDecimal.valueOf(
	                        faker.number().numberBetween(1000, 100000)))
	                    .stock(faker.number().numberBetween(1, 500))
	                    .seller(sellers.get(i % sellers.size()))
	                    .location(location.get(i % location.size()))
	                    .manufacturingDate(LocalDate.now().minusMonths(i % 12))
	                    .expiryDate(LocalDate.now().plusMonths(i%12))
	                    .model(model.get(i % model.size()))
	                    .createdDate(LocalDateTime.now())
	                    .updatedDate(LocalDateTime.now())
	                    .build()
	            );
	        }
	    };
	}

}
