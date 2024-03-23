package com.example.Exercise_05_Spring_Data_Intro;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.Book;
import com.example.Exercise_05_Spring_Data_Intro.service.AuthorService;
import com.example.Exercise_05_Spring_Data_Intro.service.BookService;
import com.example.Exercise_05_Spring_Data_Intro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        sendWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        int option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1 -> getAllBooksAfterYear(2000);
            case 2 -> getAllAuthorsWithBooksBeforeYear(1990);
            case 3 -> getAllAuthorsOrderedByTheirBooksCount();
            case 4 -> getAllBookFromAuthor("George", "Powell");
        }
    }

    private void getAllBookFromAuthor(String firstName, String lastName) {
        this.bookService.findAllBooksByAuthor(firstName, lastName)
                .forEach(System.out::println);
    }

    private void getAllAuthorsOrderedByTheirBooksCount() {
        this.authorService.findAllAuthorsOrderedByTheirBooksCount()
                .forEach(System.out::println);
    }

    private void getAllAuthorsWithBooksBeforeYear(int year) {
        this.bookService.findAllAuthorsWithBooksBeforeYear(year)
                .forEach(System.out::println);
    }

    private void getAllBooksAfterYear(int year) {
        this.bookService.findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void sendWelcomeMessage() {
        StringBuilder welcomeMessage = new StringBuilder("Please, select option from 1 to 4.");
        welcomeMessage.append(System.lineSeparator())
                .append("Option 1:")
                .append(System.lineSeparator())
                .append(" /**\n" +
                        "  * Get all books after the year 2000.\n" +
                        "  * Print only their titles.\n" +
                        "  */")
                .append(System.lineSeparator())
                .append("Option 2:")
                .append(System.lineSeparator())
                .append("/**\n" +
                        " * Get all authors with at least on book with release date before 1990.\n" +
                        " * Print first name and last name.\n" +
                        " */")
                .append(System.lineSeparator())
                .append("Option 3:")
                .append(System.lineSeparator())
                .append("/**\n" +
                        " * Get all authors, ordered by the number of their books (descending).\n" +
                        " * Print their first name, last name and book count.\n" +
                        " */")
                .append(System.lineSeparator())
                .append("Option 4:")
                .append(System.lineSeparator())
                .append("/**\n" +
                        " * Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending)\n" +
                        " * Print the book's title, release date and copies.\n" +
                        " */");

        System.out.println(welcomeMessage);
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}