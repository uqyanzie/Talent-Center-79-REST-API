package com.tujuhsembilan.app.dto.talent;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tujuhsembilan.app.dto.skillset.SkillsetResponse;
import com.tujuhsembilan.app.model.Talent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TalentResponse {

    private String talentId;
    private String talentPhotoUrl;
    private String talentName;
    private String talentStatus;
    private String employeStatus;
    private boolean talentAvailability;
    private Integer talentExperience;
    private String talentLevel;
    private List<PositionResponse> positions;
    private List<SkillsetResponse> skillsets;

    public static Page<TalentResponse> fromEntityPage(Page<Talent> talentPage) {
    return talentPage.map(talent -> TalentResponse.builder()
        .talentId(talent.getTalentId().toString())
        .talentPhotoUrl(talent.getTalentPhotoFilename())
        .talentName(talent.getTalentName())
        .talentStatus(talent.getTalentStatus().getTalentStatusName())
        .employeStatus(talent.getEmployeeStatus().getEmployeeStatusName())
        .talentAvailability(talent.getTalentAvailability())
        .talentExperience(talent.getExperience())
        .talentLevel(talent.getTalentLevel().getTalentLevelName())
        .positions(talent.getTalentPositions().stream().map(talentPosition ->
            PositionResponse.builder()
                .positionId(talentPosition.getPosition().getPositionId().toString())
                .positionName(talentPosition.getPosition().getPositionName())
                .build()
        ).toList())
        .skillsets(talent.getTalentSkillsets().stream().map(talentSkillset ->
            SkillsetResponse.builder()
                .skillSetId(talentSkillset.getSkillset().getSkillsetId().toString())
                .skillSetName(talentSkillset.getSkillset().getSkillsetName())
                .build()
        ).toList())
        .build());
    }
     
}
