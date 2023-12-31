package com.tujuhsembilan.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.MostFrequentSkillset;
import com.tujuhsembilan.app.model.Skillset;

@Repository
public interface MostFrequentSkillSetRepository extends JpaRepository<MostFrequentSkillset, UUID>{
    
    Optional<List<MostFrequentSkillset>> findTop5FrequentSkillSetsByOrderByCounterDesc();

    Optional<MostFrequentSkillset> findBySkillset(Skillset skillset);
}
