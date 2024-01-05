package com.tujuhsembilan.app.service;

import java.util.List;


import org.springframework.data.domain.Page;


import com.tujuhsembilan.app.dto.talent.PositionResponse;
import com.tujuhsembilan.app.dto.talent.TalentLevelResponse;
import com.tujuhsembilan.app.dto.talent.TalentListFilterDto;
import com.tujuhsembilan.app.dto.talent.TalentResponse;
import com.tujuhsembilan.app.dto.talent.TalentYOEResponse;

public interface TalentService {

    List<PositionResponse> getPositionList();

    List<TalentLevelResponse> getTalentLevelList();

    List<TalentYOEResponse> getTalentYOEList();

    Page<TalentResponse> getTalentPage(TalentListFilterDto filterDto);
} 
