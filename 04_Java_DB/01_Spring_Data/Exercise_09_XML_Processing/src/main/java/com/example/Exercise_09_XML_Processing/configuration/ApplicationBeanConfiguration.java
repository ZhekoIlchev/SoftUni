package com.example.Exercise_09_XML_Processing.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper configureModelMapper() {
        return new ModelMapper();
    }
}