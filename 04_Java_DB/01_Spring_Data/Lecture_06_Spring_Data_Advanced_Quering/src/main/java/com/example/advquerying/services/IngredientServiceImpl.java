package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> getAllByNameStartingWith(String name) {
        return this.ingredientRepository.findAllByNameStartingWith(name)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllByNameIn(Collection<String> name) {
        return this.ingredientRepository.findAllByNameIn(name)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAllByName(String name) {
        this.ingredientRepository.deleteAllByName(name);
    }

    @Override
    @Transactional
    public int updatePrice() {
        return this.ingredientRepository.updatePrice();
    }

    @Override
    @Transactional
    public int updatePrice(BigDecimal priceChange, Collection<String> names) {
        return this.ingredientRepository.updatePrice(priceChange, names);
    }
}