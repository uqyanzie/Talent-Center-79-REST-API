package com.tujuhsembilan.app.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.TalentLevel;

@Repository
public interface TalentLevelRepository extends JpaRepository<TalentLevel, UUID>{
    
    Set<TalentLevel> findByTalentLevelNameIn(List<String> levelName);
}
