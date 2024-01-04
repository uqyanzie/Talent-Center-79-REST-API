package com.tujuhsembilan.app.dto.talent;

import java.util.List;
import java.util.Set;

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

    public static List<TalentResponse> fromEntityList(List<Talent> talents) {
        return talents.stream().map(talent -> {
            return TalentResponse.builder()
                .talentId(talent.getTalentId().toString())
                .talentPhotoUrl(talent.getTalentPhotoFilename())
                .talentName(talent.getTalentName())
                .talentStatus(talent.getTalentStatus().getTalentStatusName())
                .employeStatus(talent.getEmployeeStatus().getEmployeeStatusName())
                .talentAvailability(talent.isTalentAvailability())
                .talentExperience(talent.getExperience())
                .talentLevel(talent.getTalentLevel().getTalentLevelName())
                .positions(PositionResponse.fromEntityList(talent.getTalentPositions().stream().map(tp -> tp.getPosition()).toList()))
                .skillsets(SkillsetResponse.fromEntityList(talent.getTalentSkillsets().stream().map(ts -> ts.getSkillset()).toList()))
                .build();
        }).toList();
    } 
     
}
