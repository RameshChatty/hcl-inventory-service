# HC Inventory

## Overview

`hclinventory` is a Spring Boot inventory search service for electronic goods. It provides dynamic filtering, pagination, and in-memory data seeding for a demo-ready experience.

## Features

- Search inventory items
- Filter by multiple fields

- H2 in-memory database with startup seed data
- Spring Data JPA with JPA Specification queries
- OpenAPI / Swagger UI support

## Architecture

### Layers

- `controller`
  - `InventorySearchController`
- `service`
  - `InventorySearchService`
  - `InventorySearchServiceImpl`
- `repository`
  - `InventoryRepository`
  - `InventorySpecification`
- `model`
  - `InventoryEntity`
- `dto`
  - `InventorySearchRequest`
  - `InventorySearchResponse`

### Design

- REST API powered by Spring Boot
- Entity mapping to `inventory` table
- Dynamic query building using `InventorySpecification`
- Repository extends `JpaSpecificationExecutor` for flexible filtering
- Service layer converts JPA entities to DTO responses

## Technology Stack

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- H2 in-memory database
- Springdoc OpenAPI / Swagger UI
- Lombok
- JavaFaker

## Getting Started

### Prerequisites

- Java 17
- Maven

### Run Locally

From the project root:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

## Application Behavior

- Seeded inventory covers electronics products with random names, sellers, prices, stocks, and dates.
- Data is persisted in an H2 in-memory database for demo and testing.

## API Endpoints

> Note: The controller currently declares the API base path directly on `@RestController`, so endpoints are registered at `/search` and `/search/by-params`.

### Get all inventory

`GET /search`

Response:

- `inventories`: list of inventory items

### Search inventory by parameters

`GET /search/by-params`

Supported query parameters:

- `id`
- `name`
- `category`
- `subCategory`
- `model`
- `seller`
- `location`
- `manufacturingDateFrom` (ISO `yyyy-MM-dd`)
- `manufacturingDateTo` (ISO `yyyy-MM-dd`)
- `expiryDateFrom` (ISO `yyyy-MM-dd`)
- `expiryDateTo` (ISO `yyyy-MM-dd`)
- `minPrice`
- `maxPrice`
- `minStock`
- `maxStock`
- `specification`
- `status`
- pageable params: `page`, `size`, `sort`

Example:

```bash
curl "http://localhost:8080/search/by-params?category=Electronics&minPrice=1000&maxPrice=5000&page=0&size=20"
```

```bash
curl --location --request GET 'http://localhost:8080/search/by-params?manufacturingDateFrom=2026-01-06&manufacturingDateTo=2026-02-06&page=0&size=2&sort=name' \
--header 'accept: */*'
```

## Response Model

### `InventorySearchResponse`

- `inventories`: array of `Inventory`
- `totalCount`: total matching records
- `pageNumber`
- `pageSize`

### `Inventory` fields

- `id`
- `name`
- `category`
- `subCategory`
- `manufacturingDate`
- `expiryDate`
- `specification`
- `price`
- `stock`
- `model`
- `seller`
- `location`
- `createdDate`
- `updatedDate`

## Swagger / OpenAPI

Swagger UI should be available after startup at:

- `http://localhost:8080/swagger-ui.html`
- or `http://localhost:8080/swagger-ui/index.html`



## Notes

- `InventorySpecification.build(...)` applies criteria only when request values are present.
- Many string filters use case-insensitive `LIKE` matching.
- `seller` and `location` are matched by exact value.
- The controller validates some string query values with minimum length constraints.
- For a proper controller base path, `@RequestMapping("/api/inventory")` is recommended instead of using the path argument on `@RestController`.


