package com.tujuhsembilan.app.service.impl;

import java.sql.Timestamp;
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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SkillsetServiceImpl implements SkillsetService{
    
    private final MostFrequentSkillSetRepository mostFrequentSkillSetRepository;

    private final SkillsetRepository skillsetRepository;

    private final SkillsetTypeRepository skillsetTypeRepository;
    
    @Transactional
    public List<PopularTagsResponse> getTop5PopularTags(){

        List<MostFrequentSkillset> mostFrequentSkillSets = mostFrequentSkillSetRepository.findTop5FrequentSkillSetsByOrderByCounterDesc().orElseGet(() -> null);

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

            Skillset skillset = skillsetRepository.findById(UUID.fromString(tag.getTagsId())).orElseThrow( () -> new NullPointerException("Tag not found"));

            MostFrequentSkillset tagItem = mostFrequentSkillSetRepository.findBySkillset(skillset).orElseGet(() -> {
                MostFrequentSkillset newTagItem = new MostFrequentSkillset();
                newTagItem.setMostFrequentSkillsetId(UUID.randomUUID());
                newTagItem.setCreatedBy("system");
                newTagItem.setLastModifiedBy("system");
                newTagItem.setCreatedTime(Timestamp.valueOf(java.time.LocalDateTime.now()));
                newTagItem.setLastModifiedTime(Timestamp.valueOf(java.time.LocalDateTime.now()));
                newTagItem.setSkillset(skillset);
                newTagItem.setCounter(0);
                return newTagItem;
            });

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
