package com.example.Exercise_09_XML_Processing.service;

import com.example.Exercise_09_XML_Processing.model.dto.ProductSeedDto;
import com.example.Exercise_09_XML_Processing.model.dto.ProductWithoutBuyerViewRootDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Long getEntityCount();

    void seedProducts(List<ProductSeedDto> products);

    ProductWithoutBuyerViewRootDto findAllProductsInRangeWithoutBuyer(BigDecimal lowerBound, BigDecimal upperBound);
}