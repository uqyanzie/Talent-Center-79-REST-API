package com.tujuhsembilan.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.Talent;

@Repository
public interface TalentRepository extends JpaRepository<Talent, UUID>{
    
    @Query("SELECT DISTINCT t.experience FROM Talent t")
    List<Integer> findTalentExperiencesDistinctByExperience();
}
