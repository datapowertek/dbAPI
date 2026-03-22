package com.apiservice.product.repository;

import com.apiservice.product.entity.ProductVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    Page<ProductVariant> findByProductMasterId(Long productMasterId, Pageable pageable);
}
