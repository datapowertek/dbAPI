package com.apiservice.product.service;

import com.apiservice.common.exception.ResourceNotFoundException;
import com.apiservice.product.dto.ProductDetailResponse;
import com.apiservice.product.dto.ProductSummaryResponse;
import com.apiservice.product.dto.ProductVariantResponse;
import com.apiservice.product.entity.ProductMaster;
import com.apiservice.product.entity.ProductVariant;
import com.apiservice.product.repository.ProductMasterRepository;
import com.apiservice.product.repository.ProductVariantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductMasterRepository productMasterRepository;
    private final ProductVariantRepository productVariantRepository;

    public ProductServiceImpl(ProductMasterRepository productMasterRepository,
                              ProductVariantRepository productVariantRepository) {
        this.productMasterRepository = productMasterRepository;
        this.productVariantRepository = productVariantRepository;
    }

    @Override
    public Page<ProductSummaryResponse> getProducts(Pageable pageable) {
        return productMasterRepository.findAll(pageable).map(this::toSummaryResponse);
    }

    @Override
    public ProductDetailResponse getProductById(Long id) {
        ProductMaster productMaster = productMasterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return toDetailResponse(productMaster);
    }

    @Override
    public Page<ProductVariantResponse> getProductVariants(Long productId, Pageable pageable) {
        if (!productMasterRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        return productVariantRepository.findByProductMasterId(productId, pageable).map(this::toVariantResponse);
    }

    @Override
    public Page<ProductSummaryResponse> getProductsByCategoryId(Long categoryId, Pageable pageable) {
        return productMasterRepository.findByTemplateCategoryId(categoryId, pageable)
                .map(this::toSummaryResponse);
    }

    private ProductSummaryResponse toSummaryResponse(ProductMaster productMaster) {
        return new ProductSummaryResponse(
                productMaster.getId(),
                productMaster.getModelName(),
                productMaster.getSlug(),
                productMaster.getMarketingDescription(),
                productMaster.getBrandId(),
                productMaster.getTemplate().getId(),
                productMaster.getTemplate().getCategory().getId()
        );
    }

    private ProductDetailResponse toDetailResponse(ProductMaster productMaster) {
        return new ProductDetailResponse(
                productMaster.getId(),
                productMaster.getModelName(),
                productMaster.getSlug(),
                productMaster.getMarketingDescription(),
                productMaster.getBrandId(),
                productMaster.getTemplate().getId(),
                productMaster.getTemplate().getTemplateName(),
                productMaster.getTemplate().getCategory().getId(),
                productMaster.getTemplate().getCategory().getName()
        );
    }

    private ProductVariantResponse toVariantResponse(ProductVariant variant) {
        return new ProductVariantResponse(
                variant.getId(),
                variant.getProductMaster().getId(),
                variant.getSkuCode(),
                variant.getMpn(),
                variant.getUpcEan(),
                variant.getDisplayTitle(),
                variant.getCostPrice(),
                variant.getMinPrice(),
                variant.getMarketPrice(),
                variant.getQuantity(),
                variant.getMinStockLevel(),
                variant.getActive()
        );
    }
}
