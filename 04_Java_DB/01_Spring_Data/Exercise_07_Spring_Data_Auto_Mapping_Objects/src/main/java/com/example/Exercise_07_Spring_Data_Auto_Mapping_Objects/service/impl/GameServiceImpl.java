package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.service.impl;

import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.GameAddDto;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.entity.Game;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.repository.GameRepository;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.service.GameService;
import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        Set<ConstraintViolation<GameAddDto>> violations = this.validationUtil.violation(gameAddDto);

        if (!violations.isEmpty()) {
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        Game game = this.modelMapper.map(gameAddDto, Game.class);
        this.gameRepository.save(game);
    }
}