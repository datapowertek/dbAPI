package com.apiservice.product.dto;

public class ProductSummaryResponse {

    private Long id;
    private String modelName;
    private String slug;
    private String marketingDescription;
    private Long brandId;
    private Long templateId;
    private Long categoryId;

    public ProductSummaryResponse() {
    }

    public ProductSummaryResponse(Long id, String modelName, String slug, String marketingDescription,
                                  Long brandId, Long templateId, Long categoryId) {
        this.id = id;
        this.modelName = modelName;
        this.slug = slug;
        this.marketingDescription = marketingDescription;
        this.brandId = brandId;
        this.templateId = templateId;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMarketingDescription() {
        return marketingDescription;
    }

    public void setMarketingDescription(String marketingDescription) {
        this.marketingDescription = marketingDescription;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
