Generate Spring Boot code for APIService.

## Tech stack:
Spring Boot
Spring Data JPA
PostgreSQL
java version "11.0.28" 2025-07-15 LTS

Use existing database schema (db_v2.sql), do not modify this schema.

## Generate:
- Entity classes
- Repository
- Service
- Controller

## API:

GET /products
GET /products/{id}
GET /products/{id}/variants
GET /products/category/{id}

Support pageable queries.

# GIT WORKFLOW (MANDATORY)

Follow Git flow:

* Main branch: main
* Development branch: develop

---

## Branch rules:

* All development must branch from: develop
* Naming:

feature/{feature-name}
bugfix/{bug-name}

---

## Commit rules:

Use clear commit message:

feat: add product search API
fix: resolve duplicate product issue
refactor: optimize specification query

---

## Merge rules:

* Create Merge Request (MR) into develop
* MUST have code review before merge
* No direct commit to develop or main
* Squash commits before merge

---

