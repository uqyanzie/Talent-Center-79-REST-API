package com.tujuhsembilan.app.dto.skillset;

import java.util.List;

import com.tujuhsembilan.app.model.Skillset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillsetResponse {
    
    private String skillSetId;

    private String skillSetName;

    public static List<SkillsetResponse> fromEntityList(List<Skillset> skillsets) {
        return skillsets.stream().map(skillset -> {
            return SkillsetResponse.builder()
                .skillSetId(skillset.getSkillsetId().toString())
                .skillSetName(skillset.getSkillsetName())
                .build();
        }).toList();
    }
}
