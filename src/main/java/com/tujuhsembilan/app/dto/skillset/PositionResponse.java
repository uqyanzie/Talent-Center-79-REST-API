package com.tujuhsembilan.app.dto.skillset;

import java.util.List;

import com.tujuhsembilan.app.model.Position;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionResponse {

    private String positionId;
    
    private String positionName;

    public static List<PositionResponse> fromEntityList(List<Position> positions) {
        return positions.stream().map(position -> {
            return PositionResponse.builder()
                .positionId(position.getPositionId().toString())
                .positionName(position.getPositionName())
                .build();
        }).toList();
    }
}
