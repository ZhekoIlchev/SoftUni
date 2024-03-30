package com.example.Exercise_08_JSON_Processing.service.impl;

import com.example.Exercise_08_JSON_Processing.model.dto.ProductNameAndPriceDto;
import com.example.Exercise_08_JSON_Processing.model.dto.ProductSeedDto;
import com.example.Exercise_08_JSON_Processing.model.entity.Category;
import com.example.Exercise_08_JSON_Processing.model.entity.Product;
import com.example.Exercise_08_JSON_Processing.model.entity.User;
import com.example.Exercise_08_JSON_Processing.repository.ProductRepository;
import com.example.Exercise_08_JSON_Processing.service.CategoryService;
import com.example.Exercise_08_JSON_Processing.service.ProductService;
import com.example.Exercise_08_JSON_Processing.service.UserService;
import com.example.Exercise_08_JSON_Processing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Exercise_08_JSON_Processing.constatns.GlobalConstants.RESOURCE_FILE_PATH;
import static com.example.Exercise_08_JSON_Processing.constatns.GlobalConstants.PRODUCTS_FILE_NAME;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, UserService userService, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedProducts() throws IOException {
        if (this.productRepository.count() > 0) {
            return;
        }

        String productsAsJSON = Files.readString(Path.of(RESOURCE_FILE_PATH + PRODUCTS_FILE_NAME));
        ProductSeedDto[] products = gson.fromJson(productsAsJSON, ProductSeedDto[].class);

        Arrays
                .stream(products)
                .filter(validationUtil::isValid)
                .map(productDto -> {
                    Product product = this.modelMapper.map(productDto, Product.class);

                    User seller = this.userService.findRandomUser();
                    product.setSeller(seller);

                    if (product.getPrice().compareTo(BigDecimal.valueOf(650L)) < 0) {
                        User buyer = this.userService.findRandomUser();
                        product.setBuyer(buyer);
                    }

                    Set<Category> categories = this.categoryService.findRandomCategories();
                    product.setCategories(categories);

                    return product;
                })
                .forEach(this.productRepository::save);
    }

    @Override
    public List<ProductNameAndPriceDto> findAllProductsInRangeWithoutBuyer(BigDecimal lowerBound, BigDecimal upperBound) {
        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lowerBound, upperBound);

        return products
                .stream()
                .map(product -> this.modelMapper.map(product, ProductNameAndPriceDto.class))
                .collect(Collectors.toList());
    }
}