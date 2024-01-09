package com.tujuhsembilan.app.dto.skillset;

import java.util.List;
import java.util.Objects;

import com.tujuhsembilan.app.dto.UserResponse;
import com.tujuhsembilan.app.model.MostFrequentSkillset;
import com.tujuhsembilan.app.model.User;

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
public class PopularTagsResponse {
    
    private String tagsId;

    private String tagsName;

    public static List<PopularTagsResponse> fromEntityList(List<MostFrequentSkillset> tags) {
        
        return tags.stream().map(tag -> {
            return PopularTagsResponse.builder()
                .tagsId(tag.getSkillset().getSkillsetId().toString())
                .tagsName(Objects.isNull(tag.getSkillset())? null : tag.getSkillset().getSkillsetName())
                .build();
        }).toList();
    }

}
