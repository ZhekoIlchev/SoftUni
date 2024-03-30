package com.example.Exercise_08_JSON_Processing.service;

import com.example.Exercise_08_JSON_Processing.model.dto.ProductNameAndPriceDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNameAndPriceDto> findAllProductsInRangeWithoutBuyer(BigDecimal lowerBound, BigDecimal upperBound);
}