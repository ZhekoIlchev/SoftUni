package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<String> getAllBySize(Size size) {
        return this.shampooRepository.findAllBySizeOrderById(size)
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.",
                        shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBySizeOrLabel_IdOrderByPrice(Size size, Long labelId) {
        return this.shampooRepository.findAllBySizeOrLabel_IdOrderByPrice(size, labelId)
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.",
                        shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .stream()
                .map(shampoo -> String.format("%s %s %.2flv.",
                        shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public int countAllByPriceLessThan(BigDecimal price) {
        return this.shampooRepository.countAllByPriceLessThan(price);
    }

    @Override
    public List<String> getAllByIngredientsName(List<String> names) {
        return this.shampooRepository.findAllByIngredientsNames(names)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllByIngredientCounts(Long ingredientCount) {
        return this.shampooRepository.findAllByIngredientCounts(ingredientCount)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }
}