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

    public Page<TalentResponse> getTalentPage(List<String> tagNames, List<String> positionNames, List<String> talentLevelNames, List<Integer> talentYOE, Integer pageNumber, Integer pageSize, String sort, String order){
        
        Specification<Talent> skillFilter = Specification.where(null);
        Specification<Talent> positionFilter = Specification.where(null);
        Specification<Talent> talentLevelFilter = Specification.where(null);
        Specification<Talent> talentYOEFilter = Specification.where(null);

        if(!ObjectUtils.isEmpty(tagNames)) {
            skillFilter = (r, q, cb) -> {
                Join<Talent, TalentSkillset> tsJoins = r.join("talentSkillsets");
                Join<TalentSkillset, Skillset> ssJoin = tsJoins.join("skillset");

                return ssJoin.get("skillsetName").in(Arrays.asList(tagNames.toArray()));
            };
        }

        if(!ObjectUtils.isEmpty(positionNames)) {
            positionFilter = (r, q, cb) -> {
                Join<Talent, TalentPosition> tpJoins = r.join("talentPositions");
                Join<TalentPosition, Position> pJoin = tpJoins.join("position");

                return pJoin.get("positionName").in(Arrays.asList(positionNames.toArray()));
            };
        }

        if(!ObjectUtils.isEmpty(talentLevelNames)) {
            talentLevelFilter = (r, q, cb) -> {
                Join<Talent, TalentLevel> tlJoins = r.join("talentLevel");

                return tlJoins.get("talentLevelName").in(Arrays.asList(talentLevelNames.toArray()));
            };
        }

        if(!ObjectUtils.isEmpty(talentYOE)) {
            talentYOEFilter = (r, q, cb) -> {
                return r.get("experience").in(Arrays.asList(talentYOE.toArray()));
            };
        }
        
        if(sort == null) {
            sort = "experience";
        }

        if(order == null) {
            order = "desc";
        }

        Sort sortSettings = Sort.by(sort);
        if (order.equals("asc")) {
            sortSettings = sortSettings.ascending();
        } else if (order.equals("desc")) {
            sortSettings = sortSettings.descending();
        } else {
            throw new IllegalArgumentException("Invalid sort order");
        }

        Pageable pageSettings = PageRequest.of(pageNumber, pageSize, sortSettings);

        Page<Talent> talentList = talentRepository.findAll(
            Specification.where(skillFilter).and(positionFilter).and(talentLevelFilter).and(talentYOEFilter),
            pageSettings
        );

        return TalentResponse.fromEntityPage(talentList);
    }
    
}
