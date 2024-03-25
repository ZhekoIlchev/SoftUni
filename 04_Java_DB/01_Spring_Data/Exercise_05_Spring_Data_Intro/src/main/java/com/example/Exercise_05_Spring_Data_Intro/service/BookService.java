package com.example.Exercise_05_Spring_Data_Intro.service;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int releaseDate);

    List<String> findAllAuthorsWithBooksBeforeYear(int releaseDate);

    List<String> findAllBooksByAuthor(String firstName, String lastName);
}