package com.tujuhsembilan.app.dto.skillset;

import java.util.List;

import com.tujuhsembilan.app.model.MostFrequentSkillset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateTagsCounterRequest {
    
    private List<TagDto> tags;

}
