package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;
    private final Scanner scanner;

    @Autowired
    public CommandLineRunnerImpl(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        sendWelcomeMessage();
        int taskNumber = Integer.parseInt(this.scanner.nextLine());

        switch (taskNumber) {
            case 1 -> {
                Size size = chooseSize();
                getAllShampoosBySize(size);
            }
            case 2 -> {
                Size size = chooseSize();
                System.out.println("Please enter label id.");
                Long labelId = Long.parseLong(this.scanner.nextLine());
                getAllShampoosBySizeOrLabelId(size, labelId);
            }
            case 3 -> {
                System.out.println("Please enter price.");
                BigDecimal price = new BigDecimal(this.scanner.nextLine());
                getAllShampoosByPrice(price);
            }
            case 4 -> {
                System.out.println("Please enter starting string.");
                String startingLetters = this.scanner.nextLine();
                getAllIngredientsByNameStartingWith(startingLetters);
            }
            case 5 -> {
                System.out.println("Please enter ingredient names");
                List<String> ingredientNames = Arrays.asList(this.scanner.nextLine().split("\\s+"));
                getAllIngredientsByNameIn(ingredientNames);
            }
            case 6 -> {
                System.out.println("Please enter price.");
                BigDecimal price = new BigDecimal(this.scanner.nextLine());
                countAllShampoosByPriceLessThan(price);
            }
            case 7 -> {
                System.out.println("Please enter ingredient names");
                List<String> ingredientNames = Arrays.asList(this.scanner.nextLine().split("\\s+"));
                getAllShampoosByIngredientsName(ingredientNames);
            }
            case 8 -> {
                System.out.println("Please enter ingredient count.");
                Long ingredientsCount = Long.parseLong(this.scanner.nextLine());
                getAllShampoosByIngredientCounts(ingredientsCount);
            }
            case 9 -> {
                System.out.println("Enter ingredient name");
                String ingredientName = this.scanner.nextLine();
                deleteAllIngredientsByName(ingredientName);
            }
            case 10 -> updateIngredientsPrice();
            case 11 -> {
                System.out.println("Please enter price.");
                BigDecimal priceFactor = new BigDecimal(this.scanner.nextLine());
                System.out.println("Please enter ingredients.");
                List<String> ingredientNames = Arrays.asList(this.scanner.nextLine().split("\\s+"));
                updatePrice(priceFactor, ingredientNames);
            }
        }

    }

    private void updatePrice(BigDecimal priceFactor, List<String> ingredientNames) {
        System.out.println(this.ingredientService.updatePrice(priceFactor, ingredientNames));
    }

    private void updateIngredientsPrice() {
        System.out.println(this.ingredientService.updatePrice());
    }

    private void deleteAllIngredientsByName(String ingredientName) {
        this.ingredientService.deleteAllByName(ingredientName);
    }

    private void getAllShampoosByIngredientCounts(Long ingredientsCount) {
        this.shampooService.getAllByIngredientCounts(ingredientsCount)
                .forEach(System.out::println);
    }

    private void getAllShampoosByIngredientsName(List<String> ingredientNames) {
        this.shampooService.getAllByIngredientsName(ingredientNames)
                .forEach(System.out::println);
    }

    private void countAllShampoosByPriceLessThan(BigDecimal price) {
        System.out.println(this.shampooService.countAllByPriceLessThan(price));
    }

    private void getAllIngredientsByNameIn(List<String> ingredientNames) {
        this.ingredientService.getAllByNameIn(ingredientNames)
                .forEach(System.out::println);
    }

    private void getAllIngredientsByNameStartingWith(String startingLetters) {
        this.ingredientService.getAllByNameStartingWith(startingLetters)
                .forEach(System.out::println);
    }

    private void getAllShampoosByPrice(BigDecimal price) {
        this.shampooService.getAllByPriceGreaterThanOrderByPriceDesc(price)
                .forEach(System.out::println);
    }

    private void getAllShampoosBySizeOrLabelId(Size size, Long labelId) {
        this.shampooService.getAllBySizeOrLabel_IdOrderByPrice(size, labelId)
                .forEach(System.out::println);
    }

    private void getAllShampoosBySize(Size size) {
        this.shampooService.getAllBySize(size)
                .forEach(System.out::println);
    }

    private Size chooseSize() {
        System.out.println("Please choose shampoo size [SMALL, MEDIUM, LARGE].");
        return Size.valueOf(this.scanner.nextLine());
    }

    private static void sendWelcomeMessage() {
        StringBuilder welcomeMessage = new StringBuilder("Please, choose task number from 1 - 10.");
        welcomeMessage.append(System.lineSeparator())
                .append("Task 1: Select Shampoos by Size;")
                .append(System.lineSeparator())
                .append("Task 2: Select Shampoos by Size or Label;")
                .append(System.lineSeparator())
                .append("Task 3: Select Shampoos by Price;")
                .append(System.lineSeparator())
                .append("Task 4: Select Ingredients by Name;")
                .append(System.lineSeparator())
                .append("Task 5: Select Ingredients by Names;")
                .append(System.lineSeparator())
                .append("Task 6: Count Shampoos by Price;")
                .append(System.lineSeparator())
                .append("Task 7: Select Shampoos by Ingredients;")
                .append(System.lineSeparator())
                .append("Task 8: Select Shampoos by Ingredients Count;")
                .append(System.lineSeparator())
                .append("Task 9: Delete Ingredients by Name;")
                .append(System.lineSeparator())
                .append("Task 10: Update Ingredients by Price;")
                .append(System.lineSeparator())
                .append("Task 11: Update Ingredients by Names.");

        System.out.println(welcomeMessage);
    }
}