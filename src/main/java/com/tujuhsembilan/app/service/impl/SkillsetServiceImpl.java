package com.tujuhsembilan.app.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.app.dto.skillset.PopularTagsResponse;
import com.tujuhsembilan.app.dto.skillset.SearchTagsResponse;
import com.tujuhsembilan.app.dto.skillset.SkillsetResponse;
import com.tujuhsembilan.app.dto.skillset.TagDto;
import com.tujuhsembilan.app.dto.skillset.UpdateTagsCounterRequest;
import com.tujuhsembilan.app.model.MostFrequentSkillset;
import com.tujuhsembilan.app.model.Skillset;
import com.tujuhsembilan.app.model.SkillsetType;
import com.tujuhsembilan.app.repository.MostFrequentSkillSetRepository;
import com.tujuhsembilan.app.repository.SkillsetRepository;
import com.tujuhsembilan.app.repository.SkillsetTypeRepository;
import com.tujuhsembilan.app.service.SkillsetService;

import jakarta.transaction.Transactional;

@Service
public class SkillsetServiceImpl implements SkillsetService{
    
    @Autowired
    private MostFrequentSkillSetRepository mostFrequentSkillSetRepository;

    @Autowired
    private SkillsetRepository skillsetRepository;

    @Autowired
    private SkillsetTypeRepository skillsetTypeRepository;
    
    @Transactional
    public List<PopularTagsResponse> getTop5PopularTags(){

        List<MostFrequentSkillset> mostFrequentSkillSets = mostFrequentSkillSetRepository.findTop5FrequentSkillSetsByOrderByCounterDesc().orElseGet(null);

        return PopularTagsResponse.fromEntityList(mostFrequentSkillSets);

    }

    @Transactional
    public List<SearchTagsResponse> searchAutoCompleteTagsByName(String skillsetName){

        List<Skillset> skillSets = skillsetRepository.findBySkillsetNameStartsWithIgnoreCase(skillsetName);
        
        return SearchTagsResponse.fromEntityList(skillSets);
    }

    @Transactional
    public void updateTagsCounter(UpdateTagsCounterRequest request) throws NullPointerException{
            
        for(TagDto tag : request.getTags()){
            MostFrequentSkillset tagItem= mostFrequentSkillSetRepository.findByMostFrequentSkillsetId(UUID.fromString(tag.getTagsId())).orElseThrow( () -> new NullPointerException("Tag not found"));

            tagItem.setCounter(tagItem.getCounter() + 1);
            mostFrequentSkillSetRepository.save(tagItem);
        }
    }

    @Transactional
    public List<SkillsetResponse> searchSkillsetBySkillsetTypeName(String skillsetTypeName){

        SkillsetType skillsetType = skillsetTypeRepository.findBySkillsetTypeName(skillsetTypeName);

        List<Skillset> skillSets = skillsetRepository.findBySkillsetType(skillsetType);

        return SkillsetResponse.fromEntityList(skillSets);
    }

}
