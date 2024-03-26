package com.example.advquerying.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface IngredientService {
    List<String> getAllByNameStartingWith(String name);

    List<String> getAllByNameIn(Collection<String> name);

    void deleteAllByName(String name);

    int updatePrice();

    int updatePrice(BigDecimal priceChange, Collection<String> names);
}