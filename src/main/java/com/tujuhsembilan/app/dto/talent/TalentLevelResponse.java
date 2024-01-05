package com.tujuhsembilan.app.dto.talent;

import java.util.List;

import com.tujuhsembilan.app.model.TalentLevel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TalentLevelResponse {
    
    private String talentLevelId;
    private String talentLevelName;

    public static List<TalentLevelResponse> fromEntityList(List<TalentLevel> talentLevels) {
        return talentLevels.stream().map(talentLevel ->  
            TalentLevelResponse.builder()
            .talentLevelId(talentLevel.getTalentLevelId().toString())
            .talentLevelName(talentLevel.getTalentLevelName())
            .build()
        ).toList();
    }
}
