package com.tujuhsembilan.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.SkillsetType;


@Repository
public interface SkillsetTypeRepository extends JpaRepository<SkillsetType, UUID>{
    
    SkillsetType findBySkillsetTypeName(String skillsetTypeName);
}
