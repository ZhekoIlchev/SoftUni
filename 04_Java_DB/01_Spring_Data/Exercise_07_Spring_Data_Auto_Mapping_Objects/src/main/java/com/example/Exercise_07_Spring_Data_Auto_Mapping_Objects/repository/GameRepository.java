package com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.repository;

import com.example.Exercise_07_Spring_Data_Auto_Mapping_Objects.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}