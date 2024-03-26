package com.example.advquerying.services;

import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<String> getAllBySize(Size size);

    List<String> getAllBySizeOrLabel_IdOrderByPrice(Size size, Long labelId);

    List<String> getAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countAllByPriceLessThan(BigDecimal price);

    List<String> getAllByIngredientsName(List<String> names);

    List<String> getAllByIngredientCounts(Long ingredientCount);
}