package com.tujuhsembilan.app.dto.talent;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentListFilterDto {

    private List<String> tags; 
    private List<String> positions; 
    private List<String> talentLevels; 
    private List<Integer> experiences;
    private Integer pageSize; 
    private Integer pageNumber;  
    private String sort; 
    private String order;

}
