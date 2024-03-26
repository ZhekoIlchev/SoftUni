package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabel_IdOrderByPrice(Size size, Long labelId);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countAllByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE i.name IN :names")
    List<Shampoo> findAllByIngredientsNames(List<String> names);

    /**
     * Last two methods do same thing.
     */
    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i GROUP BY s.id HAVING COUNT(i) < :ingredientCount")
    List<Shampoo> findAllByIngredientCounts(Long ingredientCount);

    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :ingredientCount")
    List<Shampoo> findAllByIngredientCount(int ingredientCount);
}