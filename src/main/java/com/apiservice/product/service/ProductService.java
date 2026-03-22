package com.apiservice.product.service;

import com.apiservice.product.dto.ProductDetailResponse;
import com.apiservice.product.dto.ProductSummaryResponse;
import com.apiservice.product.dto.ProductVariantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductSummaryResponse> getProducts(Pageable pageable);

    ProductDetailResponse getProductById(Long id);

    Page<ProductVariantResponse> getProductVariants(Long productId, Pageable pageable);

    Page<ProductSummaryResponse> getProductsByCategoryId(Long categoryId, Pageable pageable);
}
