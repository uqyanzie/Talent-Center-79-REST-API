package com.tujuhsembilan.app.dto.client;

import java.util.List;
import java.util.UUID;

import com.tujuhsembilan.app.dto.UserResponse;
import com.tujuhsembilan.app.model.ClientPosition;
import com.tujuhsembilan.app.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientPositionResponse {
    
    public UUID clientPositionId;
    public String clientPositionName;
    
     public static List<ClientPositionResponse> fromEntityList(List<ClientPosition> clientPositions) {
        
        return clientPositions.stream().map(clientPosition -> {
            return ClientPositionResponse.builder()
                .clientPositionId(clientPosition.getClientPositionId())
                .clientPositionName(clientPosition.getClientPositionName())
                .build();
        }).toList();
    }    
}
