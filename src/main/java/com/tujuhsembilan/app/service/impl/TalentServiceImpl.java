package com.tujuhsembilan.app.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.app.dto.talent.PositionResponse;
import com.tujuhsembilan.app.dto.talent.TalentLevelResponse;
import com.tujuhsembilan.app.dto.talent.TalentListFilterDto;
import com.tujuhsembilan.app.dto.talent.TalentResponse;
import com.tujuhsembilan.app.dto.talent.TalentYOEResponse;
import com.tujuhsembilan.app.model.Position;
import com.tujuhsembilan.app.model.Skillset;
import com.tujuhsembilan.app.model.Talent;
import com.tujuhsembilan.app.model.TalentLevel;
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

@Service
public class TalentServiceImpl implements TalentService {
    
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TalentLevelRepository talentLevelRepository;

    @Autowired
    private TalentRepository talentRepository;

    @Autowired
    private TalentSkillsetRepository talentSkillsetRepository;

    @Autowired
    private TalentPositionRepository talentPositionRepository;

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

    public List<TalentResponse> getTalentList(List<String> tagNames, List<String> positionNames, List<String> talentLevelNames, List<Integer> talentYOE, Integer page, Integer size, String sort, String order){
        TalentSpecification talentSpecification = new TalentSpecification();

        // Set<Skillset> skillsets = skillRepository.findBySkillsetNameIn(tagNames);

        // List<TalentSkillset> talentSkillsets= talentSkillsetRepository.findBySkillsets(skillsets);

        talentSpecification.add(new SearchCriteria("experience", 1, SearchOperation.EQUAL));
        talentSpecification.add(new SearchCriteria("skillsetName", tagNames, SearchOperation.IN));
        //talentSpecification.add(new SearchCriteria("talentPositions", positions, SearchOperation.IN));

        Pageable pageSettings = PageRequest.of(0, 10, Sort.by("experience").descending());

        Page<Talent> talentList = talentRepository.findAll(talentSpecification, pageSettings);

        return TalentResponse.fromEntityList(talentList.toList());
    }
    
}
