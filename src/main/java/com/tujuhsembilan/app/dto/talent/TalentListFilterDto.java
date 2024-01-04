package com.tujuhsembilan.app.dto.talent;

import java.util.List;

import com.tujuhsembilan.app.model.Skillset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentListFilterDto {

    private Integer talentExperience;    

    private String talentLevel;

    private List<String> tags;

    private List<Skillset> skillsets;
}
