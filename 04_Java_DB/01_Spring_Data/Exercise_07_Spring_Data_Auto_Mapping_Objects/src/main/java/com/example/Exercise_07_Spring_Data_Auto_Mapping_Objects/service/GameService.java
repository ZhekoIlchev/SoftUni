package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.service;

import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.dto.GameAddDto;

public interface GameService {
    void addGame(GameAddDto gameAddDto);
}