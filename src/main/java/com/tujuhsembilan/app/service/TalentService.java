package com.tujuhsembilan.app.service;

import java.util.List;
import java.util.Map;

import com.tujuhsembilan.app.dto.talent.PositionResponse;
import com.tujuhsembilan.app.dto.talent.TalentLevelResponse;
import com.tujuhsembilan.app.dto.talent.TalentResponse;
import com.tujuhsembilan.app.dto.talent.TalentYOEResponse;

public interface TalentService {

    List<PositionResponse> getPositionList();

    List<TalentLevelResponse> getTalentLevelList();

    List<TalentYOEResponse> getTalentYOEList();

    List<TalentResponse> getTalentList(List<String> tags, List<String> positions, List<String> talentLevels, List<Integer> talentYOE, Integer page, Integer size, String sort, String order);
} 
