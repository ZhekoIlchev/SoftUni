package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.configuration;

import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.GameAddDto;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.entity.Game;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper configureModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> localDateConverter = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                return LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }
        };

        modelMapper
                .typeMap(GameAddDto.class, Game.class)
                .addMappings(mapper -> mapper.map(GameAddDto::getThumbnailURL, Game::setImageThumbnail))
                .addMappings(mapper -> mapper.using(localDateConverter).map(GameAddDto::getReleaseDate, Game::setReleaseDate));

//        modelMapper.addConverter(localDateConverter);

        return modelMapper;
    }
}