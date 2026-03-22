package com.apiservice.product.repository;

import com.apiservice.product.entity.ProductMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long> {

    Page<ProductMaster> findByTemplateCategoryId(Long categoryId, Pageable pageable);
}
