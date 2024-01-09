package com.tujuhsembilan.app.dto.skillset;

import java.util.List;
import java.util.Objects;

import com.tujuhsembilan.app.model.Skillset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTagsResponse {
    
    private String tagsId;
    private String tagsName;

    public static List<SearchTagsResponse> fromEntityList(List<Skillset> tags) {
        
        return tags.stream().map(tag -> {
            return SearchTagsResponse.builder()
                .tagsId(tag.getSkillsetId().toString())
                .tagsName(Objects.isNull(tag.getSkillsetName())? null : tag.getSkillsetName())
                .build();
        }).toList();
    }
}
