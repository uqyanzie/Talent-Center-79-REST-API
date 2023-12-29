package com.tujuhsembilan.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, UUID>{
    
}
