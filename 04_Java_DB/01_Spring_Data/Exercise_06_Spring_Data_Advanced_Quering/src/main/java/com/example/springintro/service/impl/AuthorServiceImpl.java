package com.example.springintro.service.impl;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.repository.AuthorRepository;
import com.example.springintro.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .forEach(row -> {
                    String[] fullName = row.split("\\s+");
                    Author author = new Author(fullName[0], fullName[1]);

                    authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1,
                        authorRepository.count() + 1);

        return authorRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public List<String> findAuthorsWhichFirstNameEndsWith(String endsWith) {
        return this.authorRepository.findAllByFirstNameEndsWith(endsWith)
                .stream()
                .map(author -> String.format("%s %s",
                        author.getFirstName(),
                        author.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllAuthorsOrderedByTheirBooksCopies() {

        return this.authorRepository.findAll()
                .stream()
                .sorted((a, b) -> {

                    int bookA = a.getBooks()
                            .stream()
                            .mapToInt(Book::getCopies)
                            .sum();

                    int bookB = b.getBooks()
                            .stream()
                            .mapToInt(Book::getCopies)
                            .sum();

                    return Integer.compare(bookB, bookA);
                })
                .map(author -> String.format("%s %s - %d",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks()
                                .stream()
                                .mapToInt(Book::getCopies).sum()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllAuthorsWhichAreNotDeleted() {
        return this.authorRepository.findAllByDeletedOnIsNull()
                .stream()
                .map(author -> String.format("%s %s",
                        author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());
    }
}