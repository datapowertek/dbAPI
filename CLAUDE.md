Generate Spring Boot code for APIService.

Tech stack:
Spring Boot
Spring Data JPA
PostgreSQL
java version "11.0.28" 2025-07-15 LTS

Use existing database schema (db_v2.sql)

Generate:
- Entity classes
- Repository
- Service
- Controller

API:

GET /products
GET /products/{id}
GET /products/{id}/variants
GET /products/category/{id}

Support pageable queries.