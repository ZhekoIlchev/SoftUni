package com.example.Exercise_09_XML_Processing.service.impl;

import com.example.Exercise_09_XML_Processing.model.dto.ProductSeedDto;
import com.example.Exercise_09_XML_Processing.model.dto.ProductWithoutBuyerViewDto;
import com.example.Exercise_09_XML_Processing.model.dto.ProductWithoutBuyerViewRootDto;
import com.example.Exercise_09_XML_Processing.model.entity.Product;
import com.example.Exercise_09_XML_Processing.repository.ProductRepository;
import com.example.Exercise_09_XML_Processing.service.CategoryService;
import com.example.Exercise_09_XML_Processing.service.ProductService;
import com.example.Exercise_09_XML_Processing.service.UserService;
import com.example.Exercise_09_XML_Processing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, UserService userService, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long getEntityCount() {
        return this.productRepository.count();
    }

    @Override
    public void seedProducts(List<ProductSeedDto> products) {
        products
                .stream()
                .filter(this.validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = this.modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(this.userService.findRandomUser());

                    if (product.getPrice().compareTo(BigDecimal.valueOf(650L)) < 0) {
                        product.setBuyer(this.userService.findRandomUser());
                    }

                    product.setCategories(this.categoryService.findRandomCategories());

                    return product;
                })
                .forEach(this.productRepository::save);
    }

    @Override
    public ProductWithoutBuyerViewRootDto findAllProductsInRangeWithoutBuyer(BigDecimal lowerBound, BigDecimal upperBound) {
        ProductWithoutBuyerViewRootDto productRootDto = new ProductWithoutBuyerViewRootDto();

        productRootDto.setProducts(
                this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lowerBound, upperBound)
                        .stream()
                        .map(product -> {
                            ProductWithoutBuyerViewDto productDto = this.modelMapper.map(product, ProductWithoutBuyerViewDto.class);
                            productDto.setSeller(String.format("%s %s",
                                    product.getSeller().getFirstName(), product.getSeller().getLastName()));

                            return productDto;
                        })
                        .collect(Collectors.toList()));

        return productRootDto;
    }
}