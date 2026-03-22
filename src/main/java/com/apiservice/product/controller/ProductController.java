package com.apiservice.product.controller;

import com.apiservice.product.dto.ProductDetailResponse;
import com.apiservice.product.dto.ProductSummaryResponse;
import com.apiservice.product.dto.ProductVariantResponse;
import com.apiservice.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductSummaryResponse> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductDetailResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/variants")
    public Page<ProductVariantResponse> getProductVariants(@PathVariable Long id, Pageable pageable) {
        return productService.getProductVariants(id, pageable);
    }

    @GetMapping("/category/{id}")
    public Page<ProductSummaryResponse> getProductsByCategory(@PathVariable Long id, Pageable pageable) {
        return productService.getProductsByCategoryId(id, pageable);
    }
}
