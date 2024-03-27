package com.example.springintro.service.impl;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.Category;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<String> findBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int numberOfCopies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, numberOfCopies)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByPrice(BigDecimal lowerPrice, BigDecimal higherPrice) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerPrice, higherPrice)
                .stream()
                .map(book -> String.format("%s - $%.2f",
                        book.getTitle(),
                        book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByReleaseDateNotInTheGivenYear(int year) {
        LocalDate lower = LocalDate.of(year, 1, 1);
        LocalDate upper = LocalDate.of(year, 12, 31);

        return this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(lower, upper)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByReleaseDateBefore(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateBefore(date)
                .stream()
                .map(book -> String.format("%s %s %.2f",
                        book.getTitle(),
                        book.getEditionType().name(),
                        book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByTitleWhichContainsString(String containsString) {
        return this.bookRepository.findAllByTitleContains(containsString)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByAuthorWhichLastNameStartsWith(String startsWith) {
        return this.bookRepository.findAllByAuthor_LastNameStartsWith(startsWith)
                .stream()
                .map(book -> String.format("%s (%s %s)",
                        book.getTitle(),
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public int findBooksByTitleLengthWhichIsGreaterThan(int titleLength) {
        return this.bookRepository.findCountOfBooksWithTitleLengthMoreThan(titleLength);
    }

    @Override
    public List<String> findAllBooksByGivenTitle(String title) {
        return this.bookRepository.findAllByTitle(title)
                .stream()
                .map(book -> String.format("%s %s %s %.2f",
                        book.getTitle(),
                        book.getEditionType().name(),
                        book.getAgeRestriction().name(),
                        book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public String updateBookPrice(Long bookId) {
        Optional<Book> book = this.bookRepository.findById(bookId);
        BigDecimal bookPrice = book.get().getPrice();

        this.bookRepository.updateBookPrice(bookId);

        Optional<Book> updatedBook = this.bookRepository.findById(bookId);
        BigDecimal updatedBookPrice = updatedBook.get().getPrice();

        return String.format("Price of book with id: %d is updated from %.2f to %.2f.",
                bookId, bookPrice, updatedBookPrice);
    }

    @Override
    @Transactional
    public int increaseBookCopiesAfterGivenDate(int copies, LocalDate date) {
        int affectedRows = this.bookRepository.updateBookCopiesByReleaseDate(copies, date);

        return affectedRows * copies;
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }
}