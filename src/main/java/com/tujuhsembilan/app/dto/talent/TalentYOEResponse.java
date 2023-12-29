package com.tujuhsembilan.app.dto.talent;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TalentYOEResponse {
    
    private Integer experience;

    public static List<TalentYOEResponse> fromEntityList(List<Integer> experiences) {
        return experiences.stream().map(experience -> {
            return TalentYOEResponse.builder()
                .experience(experience)
                .build();
        }).toList();
    }
}
