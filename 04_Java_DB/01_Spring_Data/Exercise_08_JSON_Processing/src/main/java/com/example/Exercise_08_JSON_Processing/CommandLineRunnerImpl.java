package com.example.Exercise_08_JSON_Processing;

import com.example.Exercise_08_JSON_Processing.model.dto.ProductNameAndPriceDto;
import com.example.Exercise_08_JSON_Processing.model.dto.UserWithSoldProductDto;
import com.example.Exercise_08_JSON_Processing.service.CategoryService;
import com.example.Exercise_08_JSON_Processing.service.ProductService;
import com.example.Exercise_08_JSON_Processing.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static com.example.Exercise_08_JSON_Processing.constatns.GlobalConstants.*;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;
    private final BufferedReader bufferedReader;
    private final Gson gson;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, ProductService productService, UserService userService, Gson gson) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.gson = gson;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println("Please, enter exercise number");
        int exerciseNumber = Integer.parseInt(this.bufferedReader.readLine());

        switch (exerciseNumber) {
            case 1 -> getAllProductsInRangeWithoutBuyer();
            case 2 -> getAllUsersWhoHaveAtLeastOneSoldProduct();
        }
    }

    private void getAllUsersWhoHaveAtLeastOneSoldProduct() throws IOException {
        List<UserWithSoldProductDto> users = this.userService.findAllUsersWhoHaveAtLeastOneSoldProduct();

        String usersAsJSON = this.gson.toJson(users);
        this.writeToFile(OUTPUT_FILE_PATH + USERS_WITH_SOLD_PRODUCTS, usersAsJSON);
    }

    private void getAllProductsInRangeWithoutBuyer() throws IOException {
        System.out.println("Please enter lower bound of price");
        BigDecimal lowerBound = new BigDecimal(this.bufferedReader.readLine());

        System.out.println("Please enter upper bound of price");
        BigDecimal upperBound = new BigDecimal(this.bufferedReader.readLine());

        List<ProductNameAndPriceDto> products = this.productService.findAllProductsInRangeWithoutBuyer(lowerBound, upperBound);

        String productsAsJSON = this.gson.toJson(products);
        this.writeToFile(OUTPUT_FILE_PATH + PRODUCTS_IN_RANGE_FILE_NAME, productsAsJSON);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.userService.seedUsers();
        this.productService.seedProducts();
    }
}