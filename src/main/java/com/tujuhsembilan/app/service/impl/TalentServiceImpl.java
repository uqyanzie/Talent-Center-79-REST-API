package com.tujuhsembilan.app.service.impl;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tujuhsembilan.app.dto.talent.PositionResponse;
import com.tujuhsembilan.app.dto.talent.TalentLevelResponse;
import com.tujuhsembilan.app.dto.talent.TalentListFilterDto;
import com.tujuhsembilan.app.dto.talent.TalentResponse;
import com.tujuhsembilan.app.dto.talent.TalentYOEResponse;
import com.tujuhsembilan.app.model.Position;
import com.tujuhsembilan.app.model.Skillset;
import com.tujuhsembilan.app.model.Talent;
import com.tujuhsembilan.app.model.TalentLevel;
import com.tujuhsembilan.app.model.TalentPosition;
import com.tujuhsembilan.app.model.TalentSkillset;
import com.tujuhsembilan.app.repository.PositionRepository;
import com.tujuhsembilan.app.repository.TalentLevelRepository;
import com.tujuhsembilan.app.repository.TalentPositionRepository;
import com.tujuhsembilan.app.repository.TalentRepository;
import com.tujuhsembilan.app.repository.TalentSkillsetRepository;
import com.tujuhsembilan.app.repository.specs.SearchCriteria;
import com.tujuhsembilan.app.repository.specs.SearchOperation;
import com.tujuhsembilan.app.repository.specs.TalentSpecification;
import com.tujuhsembilan.app.service.TalentService;

import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TalentServiceImpl implements TalentService {
    
    private final PositionRepository positionRepository;

    private final TalentLevelRepository talentLevelRepository;

    private final TalentRepository talentRepository;

    private final TalentSkillsetRepository talentSkillsetRepository;

    private final TalentPositionRepository talentPositionRepository;

    public List<PositionResponse> getPositionList(){
        
        List<Position> positions = positionRepository.findAll();
        
        return PositionResponse.fromEntityList(positions);
    }

    public List<TalentLevelResponse> getTalentLevelList(){
        
        List<TalentLevel> talentLevels = talentLevelRepository.findAll();

        return TalentLevelResponse.fromEntityList(talentLevels);
    }

    public List<TalentYOEResponse> getTalentYOEList(){
        List<Integer> talentYOEList = talentRepository.findTalentExperiencesDistinctByExperience();

        return TalentYOEResponse.fromEntityList(talentYOEList);
    }

    public Page<TalentResponse> getTalentPage(TalentListFilterDto filterDto){
        
        Specification<Talent> skillFilter = Specification.where(null);
        Specification<Talent> positionFilter = Specification.where(null);
        Specification<Talent> talentLevelFilter = Specification.where(null);
        Specification<Talent> talentYOEFilter = Specification.where(null);

        if(!ObjectUtils.isEmpty(filterDto.getTags())) {
            skillFilter = (r, q, cb) -> {
                Join<Talent, TalentSkillset> tsJoins = r.join("talentSkillsets");
                Join<TalentSkillset, Skillset> ssJoin = tsJoins.join("skillset");

                return ssJoin.get("skillsetName").in(Arrays.asList(filterDto.getTags().toArray()));
            };
        }

        if(!ObjectUtils.isEmpty(filterDto.getPositions())) {
            positionFilter = (r, q, cb) -> {
                Join<Talent, TalentPosition> tpJoins = r.join("talentPositions");
                Join<TalentPosition, Position> pJoin = tpJoins.join("position");

                return pJoin.get("positionName").in(Arrays.asList(filterDto.getPositions().toArray()));
            };
        }

        if(!ObjectUtils.isEmpty(filterDto.getTalentLevels())) {
            talentLevelFilter = (r, q, cb) -> {
                Join<Talent, TalentLevel> tlJoins = r.join("talentLevel");

                return tlJoins.get("talentLevelName").in(Arrays.asList(filterDto.getTalentLevels().toArray()));
            };
        }

        if(!ObjectUtils.isEmpty(filterDto.getExperiences())) {
            talentYOEFilter = (r, q, cb) -> {
                return r.get("experience").in(Arrays.asList(filterDto.getExperiences().toArray()));
            };
        }
        
        if(filterDto.getSort() == null) {
            filterDto.setSort("experience"); 
        }

        if(filterDto.getOrder() == null) {
            filterDto.setOrder("desc");
        }

        Sort sortSettings = Sort.by(filterDto.getSort());
        if (filterDto.getOrder().equals("asc")) {
            sortSettings = sortSettings.ascending();
        } else if (filterDto.getOrder().equals("desc")) {
            sortSettings = sortSettings.descending();
        } else {
            throw new IllegalArgumentException("Invalid sort order");
        }

        Pageable pageSettings = PageRequest.of(filterDto.getPageNumber(), filterDto.getPageSize(), sortSettings);

        Page<Talent> talentList = talentRepository.findAll(
            Specification.where(skillFilter).and(positionFilter).and(talentLevelFilter).and(talentYOEFilter),
            pageSettings
        );

        return TalentResponse.fromEntityPage(talentList);
    }
    
}
