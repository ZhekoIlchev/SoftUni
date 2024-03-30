package com.example.Exercise_08_JSON_Processing.configuration;

import com.example.Exercise_08_JSON_Processing.model.dto.ProductNameAndPriceDto;
import com.example.Exercise_08_JSON_Processing.model.entity.Product;
import com.example.Exercise_08_JSON_Processing.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper configureModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<User, String> nameConverter = new Converter<User, String>() {
            @Override
            public String convert(MappingContext<User, String> mappingContext) {
                return String.format("%s %s",
                        mappingContext.getSource().getFirstName(),
                        mappingContext.getSource().getLastName());
            }
        };

        modelMapper.addConverter(nameConverter);

        modelMapper
                .createTypeMap(Product.class, ProductNameAndPriceDto.class)
                .addMappings(mapper -> mapper.map(Product::getSeller, ProductNameAndPriceDto::setSeller));

        return modelMapper;
    }

    @Bean
    public Gson configureGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
}