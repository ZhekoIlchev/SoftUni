package com.example.springintro.repository;

import com.example.springintro.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<EntityType extends BaseEntity> extends JpaRepository<EntityType, Long> {
    List<EntityType> findAllByDeletedOnIsNull();
}