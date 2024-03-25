package com.example.Exercise_05_Spring_Data_Intro.service;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.AgeRestriction;
import com.example.Exercise_05_Spring_Data_Intro.model.entity.Author;
import com.example.Exercise_05_Spring_Data_Intro.model.entity.Book;
import com.example.Exercise_05_Spring_Data_Intro.model.entity.Category;
import com.example.Exercise_05_Spring_Data_Intro.model.entity.EditionType;
import com.example.Exercise_05_Spring_Data_Intro.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .stream()
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");
                    Book book = createBook(bookInfo);
                    this.bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int releaseDate) {
        return this.bookRepository.findAllByReleaseDateAfter(LocalDate.of(releaseDate, 12, 12));
    }

    @Override
    public List<String> findAllAuthorsWithBooksBeforeYear(int releaseDate) {
        return this.bookRepository.findAllByReleaseDateBefore(LocalDate.of(releaseDate, 1, 1))
                .stream()
                .map(book -> String.format("%s %s",
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthor(String firstName, String lastName) {
        return this.bookRepository.findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    private Book createBook(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);

        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = this.authorService.getRandomAuthor();
        Set<Category> categories = this.categoryService.getRandomCategories();

        return new Book(title, null, editionType, price, copies, releaseDate, ageRestriction, author, categories);
    }
}