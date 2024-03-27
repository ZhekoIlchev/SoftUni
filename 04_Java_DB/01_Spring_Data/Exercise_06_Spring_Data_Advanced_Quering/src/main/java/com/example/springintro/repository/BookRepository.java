package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate lower, LocalDate upper);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByTitleContains(String containsString);

    List<Book> findAllByAuthor_LastNameStartsWith(String startsWith);

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :length")
    int findCountOfBooksWithTitleLengthMoreThan(@Param(value = "length") int length);

    List<Book> findAllByTitle(String title);

    /**
     * Трябва да сме си създали процедура, преди да я извикаме.
     *
     * DELIMITER &&
     * CREATE PROCEDURE change_book_price_by_id(book_id BIGINT)
     * BEGIN
     * 	UPDATE `books` b
     *     SET b.`price` = b.`price` + 100
     *     WHERE b.`id` = book_id;
     * END&&
     */
    @Procedure("change_book_price_by_id")
    void updateBookPrice(Long book_id);

    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int updateBookCopiesByReleaseDate(@Param(value = "copies") int copies,
                                      @Param(value = "date") LocalDate date);
}