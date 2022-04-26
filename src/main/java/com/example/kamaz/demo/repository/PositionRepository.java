package com.example.kamaz.demo.repository;

import com.example.kamaz.demo.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    PositionEntity findByTitle(String title);
}
