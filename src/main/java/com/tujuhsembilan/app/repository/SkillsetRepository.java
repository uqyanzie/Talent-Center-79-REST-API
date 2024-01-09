package com.tujuhsembilan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.Skillset;
import com.tujuhsembilan.app.model.SkillsetType;

import java.util.List;
import java.util.UUID;


@Repository
public interface SkillsetRepository extends JpaRepository<Skillset, UUID>{
    
    public Skillset findBySkillsetId(UUID skillsetId);

    public List<Skillset> findBySkillsetNameStartsWithIgnoreCase(String skillsetName);
    
    public List<Skillset> findBySkillsetType(SkillsetType skillsetType);

    
}
