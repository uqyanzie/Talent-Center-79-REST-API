package com.tujuhsembilan.app.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tujuhsembilan.app.model.Skillset;
import com.tujuhsembilan.app.model.TalentSkillset;

public interface TalentSkillsetRepository extends JpaRepository<TalentSkillset, UUID>{
    
    Set<TalentSkillset> findBySkillsetIn(Set<Skillset> skillsets);
    
}
