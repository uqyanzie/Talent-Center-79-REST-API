package com.tujuhsembilan.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.app.dto.talent.PositionResponse;
import com.tujuhsembilan.app.dto.talent.TalentLevelResponse;
import com.tujuhsembilan.app.dto.talent.TalentYOEResponse;
import com.tujuhsembilan.app.model.Position;
import com.tujuhsembilan.app.model.TalentLevel;
import com.tujuhsembilan.app.repository.PositionRepository;
import com.tujuhsembilan.app.repository.TalentLevelRepository;
import com.tujuhsembilan.app.repository.TalentRepository;
import com.tujuhsembilan.app.service.TalentService;

@Service
public class TalentServiceImpl implements TalentService {
    
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TalentLevelRepository talentLevelRepository;

    @Autowired
    private TalentRepository talentRepository;

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
    
}
