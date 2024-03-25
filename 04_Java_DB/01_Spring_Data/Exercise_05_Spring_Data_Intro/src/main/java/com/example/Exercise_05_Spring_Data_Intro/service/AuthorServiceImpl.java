package com.example.Exercise_05_Spring_Data_Intro.service;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.Author;
import com.example.Exercise_05_Spring_Data_Intro.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {

        if (this.authorRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .forEach(row -> {
                    String[] fullName = row.split("\\s+");
                    String firstName = fullName[0];
                    String lastName = fullName[1];

                    Author author = new Author(firstName, lastName);
                    this.authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        Long authorId = ThreadLocalRandom.current().nextLong(1, this.authorRepository.count() + 1);

        return this.authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public List<String> findAllAuthorsOrderedByTheirBooksCount() {
        return this.authorRepository.findAll()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getBooks().size(), a.getBooks().size()))
                .map(author -> String.format("%s %s %d",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()))
                .collect(Collectors.toList());
    }
}