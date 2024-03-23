package com.example.Exercise_05_Spring_Data_Intro.repository;

import com.example.Exercise_05_Spring_Data_Intro.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);
}