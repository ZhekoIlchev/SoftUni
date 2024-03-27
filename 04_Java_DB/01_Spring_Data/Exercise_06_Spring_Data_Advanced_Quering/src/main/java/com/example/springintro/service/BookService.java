package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int numberOfCopies);

    List<String> findBooksByPrice(BigDecimal lowerPrice, BigDecimal higherPrice);

    List<String> findBooksByReleaseDateNotInTheGivenYear(int year);

    List<String> findBooksByReleaseDateBefore(LocalDate date);

    List<String> findBooksByTitleWhichContainsString(String containsString);

    List<String> findBooksByAuthorWhichLastNameStartsWith(String startsWith);

    int findBooksByTitleLengthWhichIsGreaterThan(int titleLength);

    List<String> findAllBooksByGivenTitle(String title);

    String updateBookPrice(Long bookId);

    int increaseBookCopiesAfterGivenDate(int copies, LocalDate date);
}