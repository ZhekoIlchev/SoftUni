package com.example.Exercise_09_XML_Processing;

import com.example.Exercise_09_XML_Processing.model.dto.CategorySeedRootDto;
import com.example.Exercise_09_XML_Processing.model.dto.ProductSeedRootDto;
import com.example.Exercise_09_XML_Processing.model.dto.ProductWithoutBuyerViewRootDto;
import com.example.Exercise_09_XML_Processing.model.dto.UserSeedRootDto;
import com.example.Exercise_09_XML_Processing.model.dto.UserWithSoldProductViewRootDto;
import com.example.Exercise_09_XML_Processing.service.CategoryService;
import com.example.Exercise_09_XML_Processing.service.ProductService;
import com.example.Exercise_09_XML_Processing.service.UserService;
import com.example.Exercise_09_XML_Processing.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import static com.example.Exercise_09_XML_Processing.constants.GlobalConstants.*;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;
    private final XmlParser xmlParser;
    private final BufferedReader bufferedReader;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, ProductService productService, UserService userService, XmlParser xmlParser) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.xmlParser = xmlParser;
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

    private void getAllUsersWhoHaveAtLeastOneSoldProduct() throws JAXBException {
        UserWithSoldProductViewRootDto usersWithSoldProducts = this.userService.findAllUsersWhoHaveAtLeastOneSoldProduct();

        this.xmlParser.writeToFile(OUTPUT_FILE_PATH + USERS_WITH_SOLD_PRODUCTS, usersWithSoldProducts);
    }

    private void getAllProductsInRangeWithoutBuyer() throws IOException, JAXBException {
        System.out.println("Please enter lower bound of price");
        BigDecimal lowerBound = new BigDecimal(this.bufferedReader.readLine());

        System.out.println("Please enter upper bound of price");
        BigDecimal upperBound = new BigDecimal(this.bufferedReader.readLine());

        ProductWithoutBuyerViewRootDto allProductsInRangeWithoutBuyer = this.productService.findAllProductsInRangeWithoutBuyer(lowerBound, upperBound);
        this.xmlParser.writeToFile(OUTPUT_FILE_PATH + PRODUCTS_IN_RANGE_FILE_NAME, allProductsInRangeWithoutBuyer);
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        if (this.categoryService.getEntityCount() == 0) {
            CategorySeedRootDto categorySeedRootDto = this.xmlParser.readFromFile(RESOURCE_FILE_PATH + CATEGORIES_FILE_NAME, CategorySeedRootDto.class);
            this.categoryService.seedCategories(categorySeedRootDto.getCategories());
        }

        if (this.userService.getEntityCount() == 0) {
            UserSeedRootDto userSeedRootDto = this.xmlParser.readFromFile(RESOURCE_FILE_PATH + USERS_FILE_NAME, UserSeedRootDto.class);
            this.userService.seedUsers(userSeedRootDto.getUsers());
        }

        if (this.productService.getEntityCount() == 0) {
            ProductSeedRootDto productSeedRootDto = this.xmlParser.readFromFile(RESOURCE_FILE_PATH + PRODUCTS_FILE_NAME, ProductSeedRootDto.class);
            this.productService.seedProducts(productSeedRootDto.getProducts());

        }
    }
}