package com.tujuhsembilan.app.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tujuhsembilan.app.model.Position;
import com.tujuhsembilan.app.model.TalentPosition;

public interface TalentPositionRepository extends JpaRepository<TalentPosition, UUID>{
    
    Set<TalentPosition> findByPositionIn(Set<Position> positions);

}
