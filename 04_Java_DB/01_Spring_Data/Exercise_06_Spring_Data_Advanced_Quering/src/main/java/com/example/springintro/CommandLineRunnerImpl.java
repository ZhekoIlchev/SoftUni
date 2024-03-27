package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        int exerciseNumber = chooseExerciseNumber();
        switch (exerciseNumber) {
            case 1 -> getBooksTitlesByAgeRestriction();
            case 2 -> getBooksByEditionTypeAndCopiesLessThan();
            case 3 -> getBooksByPriceLowerThanXAndHigherThanY();
            case 4 -> getBooksByReleaseDateNotInTheGivenYear();
            case 5 -> getBooksByReleaseDateBeforeDate();
            case 6 -> getAuthorsWhichFirstNameEndsWith();
            case 7 -> getBooksByTitleWhichContainsString();
            case 8 -> getBooksByAuthorWhichLastNameStartsWith();
            case 9 -> getBooksByTitleLengthWhichIsGreaterThan();
            case 10 -> getAllAuthorsOrderedByTheirBookCopies();
            case 11 -> getAllBooksByTitle();
            case 12 -> increaseBookCopiesAfterGivenDate();
            case 15 -> changeBookPrice();
            case 16 -> getAllAuthorsWhichAreNotDeleted();
        }
    }

    private void getAllAuthorsWhichAreNotDeleted() {
        this.authorService.findAllAuthorsWhichAreNotDeleted()
                .forEach(System.out::println);
    }

    private void increaseBookCopiesAfterGivenDate() throws IOException {
        System.out.println("Please, enter release date in format: dd MMM yyyy.");
        LocalDate date = LocalDate.parse(this.bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd MMM yyyy"));

        System.out.println("Please, enter number of copies.");
        int copies = Integer.parseInt(this.bufferedReader.readLine());

        System.out.println(this.bookService.increaseBookCopiesAfterGivenDate(copies, date));
    }

    private void changeBookPrice() throws IOException {
        System.out.println("Please enter book id.");
        Long bookId = Long.parseLong(this.bufferedReader.readLine());

        System.out.println(this.bookService.updateBookPrice(bookId));
    }

    private void getAllBooksByTitle() throws IOException {
        System.out.println("Please enter book title.");
        String bookTitle = this.bufferedReader.readLine();

        this.bookService.findAllBooksByGivenTitle(bookTitle)
                .forEach(System.out::println);
    }

    private void getAllAuthorsOrderedByTheirBookCopies() {
        this.authorService.findAllAuthorsOrderedByTheirBooksCopies()
                .forEach(System.out::println);
    }

    private void getBooksByTitleLengthWhichIsGreaterThan() throws IOException {
        System.out.println("Please enter title length.");
        int titleLength = Integer.parseInt(this.bufferedReader.readLine());

        System.out.println(this.bookService.findBooksByTitleLengthWhichIsGreaterThan(titleLength));
    }

    private void getBooksByAuthorWhichLastNameStartsWith() throws IOException {
        System.out.println("Please, enter starting string.");
        String startsWith = this.bufferedReader.readLine();

        this.bookService.findBooksByAuthorWhichLastNameStartsWith(startsWith)
                .forEach(System.out::println);
    }

    private void getBooksByTitleWhichContainsString() throws IOException {
        System.out.println("Please, enter contains string.");
        String containsString = this.bufferedReader.readLine();

        this.bookService.findBooksByTitleWhichContainsString(containsString)
                .forEach(System.out::println);
    }

    private void getAuthorsWhichFirstNameEndsWith() throws IOException {
        System.out.println("Please enter ending string.");
        String endsWith = this.bufferedReader.readLine();

        this.authorService.findAuthorsWhichFirstNameEndsWith(endsWith)
                .forEach(System.out::println);
    }

    private void getBooksByReleaseDateBeforeDate() throws IOException {
        System.out.println("Please enter date in format dd-MM-yyy.");
        LocalDate date = LocalDate.parse(this.bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyy"));

        this.bookService.findBooksByReleaseDateBefore(date)
                .forEach(System.out::println);
    }

    private void getBooksByReleaseDateNotInTheGivenYear() throws IOException {
        System.out.println("Please, enter year.");
        int year = Integer.parseInt(this.bufferedReader.readLine());

        this.bookService.findBooksByReleaseDateNotInTheGivenYear(year)
                .forEach(System.out::println);
    }

    private void getBooksByPriceLowerThanXAndHigherThanY() throws IOException {
        System.out.println("Please, select lower price.");
        BigDecimal lowerPrice = BigDecimal.valueOf(Long.parseLong(this.bufferedReader.readLine()));

        System.out.println("Please, select higher price.");
        BigDecimal higherPrice = BigDecimal.valueOf(Long.parseLong(this.bufferedReader.readLine()));

        this.bookService.findBooksByPrice(lowerPrice, higherPrice)
                .forEach(System.out::println);
    }

    private void getBooksByEditionTypeAndCopiesLessThan() throws IOException {
        System.out.println("Please, select edition type.");
        EditionType editionType = EditionType.valueOf(this.bufferedReader.readLine().toUpperCase());

        System.out.println("Please, select number of copies.");
        int numberOfCopies = Integer.parseInt(this.bufferedReader.readLine());

        this.bookService.findBooksByEditionTypeAndCopiesLessThan(editionType, numberOfCopies)
                .forEach(System.out::println);
    }

    private void getBooksTitlesByAgeRestriction() throws IOException {
        System.out.println("Please enter, age restriction.");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(this.bufferedReader.readLine().toUpperCase());

        this.bookService.findBooksByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private int chooseExerciseNumber() throws IOException {
        System.out.println("Please, select exercise number.");
        return Integer.parseInt(this.bufferedReader.readLine());
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}