package com.apiservice.product.dto;

import java.math.BigDecimal;

public class ProductVariantResponse {

    private Long id;
    private Long productMasterId;
    private String skuCode;
    private String mpn;
    private String upcEan;
    private String displayTitle;
    private BigDecimal costPrice;
    private BigDecimal minPrice;
    private BigDecimal marketPrice;
    private Integer quantity;
    private Integer minStockLevel;
    private Boolean active;

    public ProductVariantResponse() {
    }

    public ProductVariantResponse(Long id, Long productMasterId, String skuCode, String mpn, String upcEan,
                                  String displayTitle, BigDecimal costPrice, BigDecimal minPrice,
                                  BigDecimal marketPrice, Integer quantity, Integer minStockLevel,
                                  Boolean active) {
        this.id = id;
        this.productMasterId = productMasterId;
        this.skuCode = skuCode;
        this.mpn = mpn;
        this.upcEan = upcEan;
        this.displayTitle = displayTitle;
        this.costPrice = costPrice;
        this.minPrice = minPrice;
        this.marketPrice = marketPrice;
        this.quantity = quantity;
        this.minStockLevel = minStockLevel;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductMasterId() {
        return productMasterId;
    }

    public void setProductMasterId(Long productMasterId) {
        this.productMasterId = productMasterId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getUpcEan() {
        return upcEan;
    }

    public void setUpcEan(String upcEan) {
        this.upcEan = upcEan;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(Integer minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
