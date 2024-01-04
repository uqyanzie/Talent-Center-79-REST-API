package com.tujuhsembilan.app.repository;

import java.util.List;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.Talent;
import com.tujuhsembilan.app.repository.specs.TalentSpecification;

@Repository
public interface TalentRepository extends JpaRepository<Talent, UUID>, JpaSpecificationExecutor<Talent> {
    
    @Query("SELECT DISTINCT t.experience FROM Talent t")
    List<Integer> findTalentExperiencesDistinctByExperience();

}
